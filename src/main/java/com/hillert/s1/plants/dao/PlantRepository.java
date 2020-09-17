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

package com.hillert.s1.plants.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hillert.s1.plants.model.Plant;
import org.locationtech.jts.geom.Point;

/**
 * @author Gunnar Hillert
 * @since 1.0
 */
@Transactional
@Repository
public interface PlantRepository extends JpaRepository<Plant, Long> {

	@Query("SELECT p from Plant p WHERE st_dwithin(p.location, :location, :radius, true) = true")
	List<Plant> getPlantsWithinRadius(@Param ("location") Point location, @Param ("radius") double radius);
}
