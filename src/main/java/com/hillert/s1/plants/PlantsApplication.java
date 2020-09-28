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

package com.hillert.s1.plants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.bedatadriven.jackson.datatype.jts.JtsModule;
import com.hillert.s1.plants.service.Importer;
import com.hillert.s1.plants.service.impl.CsvImporter;

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
@EnableCaching
public class PlantsApplication {

	public static void main(String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(PlantsApplication.class, args);
		final Importer seedDataService = context.getBean(CsvImporter.class);
		seedDataService.importPlantData();
	}

	@Bean
	public JtsModule jtsModule() {
		return new JtsModule();
	}

}
