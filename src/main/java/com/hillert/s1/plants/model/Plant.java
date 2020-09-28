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
package com.hillert.s1.plants.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.locationtech.jts.geom.Point;

@Entity
@Table(name="PLANTS")
public class Plant extends BaseModelObject implements Serializable {

	private static final long serialVersionUID = 3396664133771741373L;

	private String genus;
	private String species;
	private Boolean plantSignMissing;

	@Column(columnDefinition="geometry")
	private Point location;

	@OneToMany(mappedBy = "plant", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Image> images;

	public Plant() {
		super();
	}

	public Plant(String genus, String species) {
		super();
		this.genus = genus;
		this.species = species;
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

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Boolean getPlantSignMissing() {
		return plantSignMissing;
	}

	public void setPlantSignMissing(Boolean plantSignMissing) {
		this.plantSignMissing = plantSignMissing;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

}
