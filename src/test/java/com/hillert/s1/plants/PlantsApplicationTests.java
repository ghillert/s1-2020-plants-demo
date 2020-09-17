package com.hillert.s1.plants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfSystemProperty(named = "ENABLE_POSTGIS", matches = "true")
class PlantsApplicationTests {

	@Test
	void contextLoads() {
	}

}
