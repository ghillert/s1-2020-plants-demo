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

import java.io.IOException;
import java.io.InputStream;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

/**
 * @author Gunnar Hillert
 */
@Entity
@Table(name="IMAGES")
public class Image extends BaseModelObject {

	private String name;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	private byte[] image;

	@ManyToOne
	private Plant plant;

	public Image(String name, Resource data, Plant plant) {
		super();
		this.name = name;
		this.plant = plant;

		InputStream is = null;
		try {
			is = data.getInputStream();
			this.image = StreamUtils.copyToByteArray(is);
			is.close();
		} catch (IOException e) {
			throw new IllegalStateException("Unable to process image resource.", e);
		}
		finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
			}
		}
	}

	public Image() {
		super();
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the image
	 */
	public byte[] getImage() {
		return image;
	}
	/**
	 * @param image the image to set
	 */
	public void setImage(byte[] image) {
		this.image = image;
	}

	/**
	 * @return the plant
	 */
	public Plant getPlant() {
		return plant;
	}
	/**
	 * @param plant the plant to set
	 */
	public void setPlant(Plant plant) {
		this.plant = plant;
	}

}
