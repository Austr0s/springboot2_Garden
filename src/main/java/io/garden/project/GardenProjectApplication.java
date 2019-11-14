package io.garden.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "io.garden.project.repository")
@EntityScan(basePackages = "io.garden.project.model.entity")
public class GardenProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(GardenProjectApplication.class, args);
	}

}
