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
package com.hillert.s1.plants.service.impl;

import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hillert.s1.plants.dao.PlantRepository;
import com.hillert.s1.plants.model.Plant;
import com.hillert.s1.plants.service.PlantService;

/**
*
* @author Gunnar Hillert
*
*/
@Transactional
@Service
@CacheConfig(cacheNames = "jcache-partitioned-plants")
public class DefaultPlantService implements PlantService{

	@Autowired
	private PlantRepository plantRepository;

	@Cacheable
	@Override
	public Optional<Plant> getSinglePlant(Long id) {
		return this.plantRepository.getSinglePlantWithImages(id);
	}

	@Cacheable
	@Override
	public List<Plant> getPlantsWithinRadius(Point location, double radius) {
		return this.plantRepository.getPlantsWithinRadius(location, radius);
	}

	@Cacheable
	@Override
	public List<Plant> getAllPlants() {
		return this.plantRepository.findAll();
	}

	@Cacheable
	@Override
	public Page<Plant> getAllPlants(Pageable pageable) {
		return this.plantRepository.findAll(pageable);
	}

}
