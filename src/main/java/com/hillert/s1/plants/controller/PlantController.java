/*
 * Copyright 2020 Gunnar Hillert
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hillert.s1.plants.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.geojson.Feature;
import org.geojson.FeatureCollection;
import org.geojson.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hillert.s1.plants.controller.problems.PlantNotFoundProblem;
import com.hillert.s1.plants.dao.PlantRepository;
import com.hillert.s1.plants.dto.PlantDto;
import com.hillert.s1.plants.dto.PlantDtoDistanceComparator;
import com.hillert.s1.plants.model.Image;
import com.hillert.s1.plants.model.Plant;
import com.hillert.s1.plants.support.DistanceUtils;

/**
 * Explicit controller for retrieving plants.
 *
 * @author Gunnar Hillert
 *
 */
@RestController
@RequestMapping(path="/api/plants")
@Transactional
public class PlantController {

	@Autowired
	private PlantRepository plantRepository;

	@GetMapping("/geojson")
	public FeatureCollection geojson(Pageable pageable) {
		FeatureCollection featureCollection = new FeatureCollection();

		for (Plant p : plantRepository.findAll()) {
			Feature feature = new Feature();
			feature.setGeometry(new Point(p.getLocation().getX(), p.getLocation().getY()));
			feature.getProperties().put("name", p.getGenus() + " " + p.getSpecies());
			featureCollection.add(feature);
		}

		return featureCollection;
	}

	@GetMapping
	public Page<PlantDto> getPlants(Pageable pageable) {
		final Page<PlantDto> page = plantRepository.findAll(pageable).map(new Function<Plant, PlantDto>() {
			@Override
			public PlantDto apply(Plant entity) {
				final PlantDto dto = new PlantDto(entity.getId(), entity.getGenus(), entity.getSpecies());
				dto.setPlantSignMissing(entity.getPlantSignMissing());
				return dto;
			}
		});
		return page;
	}

	@GetMapping("/{plantId}")
	public PlantDto getSinglePlant(@PathVariable Long plantId, @RequestParam(defaultValue = "10") double radius) {
		final Plant plantFromDb = plantRepository.findById(plantId).orElseGet(() -> {
			throw new PlantNotFoundProblem(plantId);
		});

		final List<Plant> plantsNearby = plantRepository.getPlantsWithinRadius(plantFromDb.getLocation(), radius);

		final PlantDto plantDtoToReturn = new PlantDto(plantId, plantFromDb.getGenus(), plantFromDb.getSpecies());
		plantDtoToReturn.setPlantSignMissing(plantFromDb.getPlantSignMissing());
		plantDtoToReturn.setPlantsNearby(
			plantsNearby
				.stream()
				.filter(plant -> !plant.getId().equals(plantFromDb.getId()))
				.map(plant -> {
					final PlantDto plantNearby = new PlantDto(plant.getId(), plant.getGenus(), plant.getSpecies());
					final double distance = DistanceUtils.calculateSimpleDistance(plant.getLocation(), plantFromDb.getLocation());
					final double distanceHaversine =
						DistanceUtils.calculateDistanceUsingHaversine(
							plant.getLocation().getY(),
							plant.getLocation().getX(),
							plantFromDb.getLocation().getY(),
							plantFromDb.getLocation().getX());

					plantNearby.setDistance(distance);
					plantNearby.setDistanceHaversine(distanceHaversine);
					return plantNearby;
				})
				.sorted(new PlantDtoDistanceComparator()).collect(Collectors.toList()));

		final List<Long> imageIds = plantFromDb.getImages().stream().map(Image::getId).collect(Collectors.toList());
		plantDtoToReturn.setImageIds(imageIds);
		plantDtoToReturn.setLatitude(plantFromDb.getLocation().getY());
		plantDtoToReturn.setLongitude(plantFromDb.getLocation().getX());
		plantDtoToReturn.setLocation(plantFromDb.getLocation());
		return plantDtoToReturn;
	}



}
