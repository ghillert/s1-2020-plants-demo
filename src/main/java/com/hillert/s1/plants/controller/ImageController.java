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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hillert.s1.plants.controller.problems.ImageNotFoundProblem;
import com.hillert.s1.plants.dao.ImageRepository;
import com.hillert.s1.plants.model.Image;

/**
 * Explicit controller for retrieving Plant images.
 *
 * @author Gunnar Hillert
 *
 */
@RestController
@RequestMapping(path="/api/images")
public class ImageController {

	@Autowired
	private ImageRepository imageRepository;

	/**
	 * Get a specific image for the given image id.
	 *
	 * @param imageId
	 * @return The image (Currently JPG only)
	 * @throws ImageNotFoundProblem in case no image was found
	 */
	@GetMapping(path="/{imageId}")
	public ResponseEntity<byte[]> getImage(@PathVariable Long imageId) {

		final Image imageFromDb = imageRepository.findById(imageId).orElseGet(() -> {
			throw new ImageNotFoundProblem(imageId);
		});

		return ResponseEntity
			.ok()
			.contentType(MediaType.IMAGE_JPEG)
			.body(imageFromDb.getImage());
	}
}
