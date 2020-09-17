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

import javax.validation.constraints.NotBlank;

import com.univocity.parsers.annotations.Parsed;
import com.univocity.parsers.annotations.Trim;

public class CsvRow {

	@Trim
	@Parsed(index = 0)
	private Double latitude;

	@Trim
	@Parsed(index = 1)
	private Double longitude;

	@Trim
	@Parsed(index = 3)
	private String comments;

	@Trim
	@NotBlank
	@Parsed(index = 4)
	private String genus;

	@Trim
	@NotBlank
	@Parsed(index = 5)
	private String species;

	@Trim
	@Parsed(index = 6)
	private String imagePath;

	@Parsed(index = 8)
	private Boolean plantSignMissing;

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

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Boolean getPlantSignMissing() {
		return plantSignMissing != null ? plantSignMissing : false;
	}

	public void setPlantSignMissing(Boolean plantSignMissing) {
		this.plantSignMissing = plantSignMissing;
	}

	@Override
	public String toString() {
		return "CsvRow [Genus=" + genus + ", species=" + species +"]";
	}

}
