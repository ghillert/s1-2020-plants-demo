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

package com.hillert.s1.plants.dto;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Point;

/**
 *
 * @author Gunnar Hillert
 *
 */
public class PlantDto {

	private Long id;
	private String genus;
	private String species;
	private Boolean plantSignMissing;
	private List<Long> imageIds = new ArrayList<>();
	private double distance;
	private double distanceHaversine;
	private Double latitude;
	private Double longitude;
	private Point location;

	public PlantDto(Long id, String genus, String species) {
		super();
		this.id = id;
		this.genus = genus;
		this.species = species;
	}

	private List<PlantDto> plantsNearby;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGenus() {
		return genus;
	}

	public void setGenus(String genus) {
		this.genus = genus;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public List<PlantDto> getPlantsNearby() {
		return plantsNearby;
	}

	public void setPlantsNearby(List<PlantDto> plantsNearby) {
		this.plantsNearby = plantsNearby;
	}

	public Boolean getPlantSignMissing() {
		return plantSignMissing;
	}

	public void setPlantSignMissing(Boolean plantSignMissing) {
		this.plantSignMissing = plantSignMissing;
	}

	public List<Long> getImageIds() {
		return imageIds;
	}

	public void setImageIds(List<Long> imageIds) {
		this.imageIds = imageIds;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getDistanceHaversine() {
		return distanceHaversine;
	}

	public void setDistanceHaversine(double distanceHaversine) {
		this.distanceHaversine = distanceHaversine;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

}
