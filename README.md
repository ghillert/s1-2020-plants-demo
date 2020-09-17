# SpringOne 2020 Plants Demo

## Overview

This demo is part of the presentation "High-Precision GPS Positioning for Spring Developers" given at [SpringOne 2020](https://springone.io/2020/sessions/high-precision-gps-positioning-for-spring-developers).

The presentation slides are available at:

https://www.slideshare.net/hillert/high-precision-gps-positioning-for-spring-developers

Used technologies:

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data](https://spring.io/projects/spring-data)
- [Flyway](https://flywaydb.org/)
- [Hibernate Spatial](https://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#spatial)
- [PostGIS](https://postgis.net/)
- [Univocity Parsers](https://www.univocity.com/pages/univocity_parsers_tutorial)
- [GeoJSON](https://geojson.org/)
- [Angular](https://angular.io/) 10
- [Clarity Design System](https://clarity.design/)

## Requirements

Please make sure that you have the following installed:

[Java 11](https://openjdk.java.net/projects/jdk/11/)
[Docker](https://www.docker.com/products/docker-desktop)

## How to run

Run the following commands:

```bash
mvnw clean package
docker-compose up
```

The relevant docker images will be download and booted up. Once running, you can access the application at:

http://localhost:9080
