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
package com.hillert.s1.plants.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

/**
 * Plants-application-specific configuration properties.
 *
 * @author Gunnar Hillert
 *
 */
@ConfigurationProperties(prefix = "plants")
public class PlantsConfig {

	private Resource demoDataFile;
	private Resource demoDataImages;

	private DemoDataImportMode demoDataImportMode;

	public Resource getDemoDataFile() {
		return demoDataFile;
	}

	public void setDemoDataFile(Resource demoDataFile) {
		this.demoDataFile = demoDataFile;
	}

	public DemoDataImportMode getDemoDataImportMode() {
		return demoDataImportMode;
	}

	public void setDemoDataImportMode(DemoDataImportMode demoDataImportMode) {
		this.demoDataImportMode = demoDataImportMode;
	}

	public Resource getDemoDataImages() {
		return demoDataImages;
	}

	public void setDemoDataImages(Resource demoDataImages) {
		this.demoDataImages = demoDataImages;
	}

	public enum DemoDataImportMode {
		DO_NOTHING,
		DELETE_AND_IMPORT,
		IMPORT_ONCE
	}
}
