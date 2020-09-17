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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hillert.s1.plants.config.PlantsConfig;
import com.hillert.s1.plants.dao.ImageRepository;
import com.hillert.s1.plants.dao.PlantRepository;
import com.hillert.s1.plants.model.Image;
import com.hillert.s1.plants.model.Plant;
import com.hillert.s1.plants.service.Importer;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

/**
 * Implementation of the {@link Importer} that will
 * import demo data from a CSV source.
 *
 * @author Gunnar Hillert
 *
 * @see PlantsConfig
 *
 */
@Service
public class CsvImporter implements Importer {

	@Autowired
	private PlantRepository plantRepository;

	@Autowired
	private PlantsConfig plantsConfig;

	@Autowired
	private ImageRepository imageRepository;

	@Override
	public void importPlantData() {

		switch (plantsConfig.getDemoDataImportMode()) {
			case DELETE_AND_IMPORT:
				this.imageRepository.deleteAllInBatch();
				this.plantRepository.deleteAllInBatch();
				break;
			case IMPORT_ONCE:
				if (this.plantRepository.count() > 0) {
					return;
				}
				break;
			case DO_NOTHING:
				return;
		}

		final BeanListProcessor<CsvRow> rowProcessor = new BeanListProcessor<CsvRow>(CsvRow.class);
		final CsvParserSettings settings = new CsvParserSettings();
		settings.setHeaderExtractionEnabled(true);
		settings.getFormat().setLineSeparator("\n");
		settings.setProcessor(rowProcessor);

		final CsvParser parser = new CsvParser(settings);
		parser.parseAll(getReader());
		final List<CsvRow> rows = rowProcessor.getBeans();

		final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

		for (CsvRow csvRow : rows) {
			System.out.println(csvRow);
			System.out.println(csvRow.getImagePath());
			final Plant plant = new Plant(csvRow.getGenus(), csvRow.getSpecies());

			plant.setPlantSignMissing(csvRow.getPlantSignMissing());
			plant.setLocation(geometryFactory.createPoint(new Coordinate(csvRow.getLongitude(), csvRow.getLatitude())));
			final Plant savedPlant = this.plantRepository.save(plant);

			if (StringUtils.hasText(csvRow.getImagePath())) {
				final Resource imageResourcePath;
				try {
					imageResourcePath = this.plantsConfig.getDemoDataImages().createRelative(csvRow.getImagePath());
				}
				catch (IOException e) {
					throw new IllegalStateException("Unable to load image resource.",e);
				}
				final Image image = new Image(savedPlant.getId() + ".jpg", imageResourcePath, savedPlant);
				this.imageRepository.save(image);
			}
		}
	}

	public Reader getReader() {
		InputStream is = null;
		try {
			is = plantsConfig.getDemoDataFile().getInputStream();
		}
		catch (IOException e) {
			throw new IllegalStateException(e);
		}
		try {
			return new InputStreamReader(is, "UTF-8");
		}
		catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e );
		}
	}
}
