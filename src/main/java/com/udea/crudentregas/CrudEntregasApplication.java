package com.udea.crudentregas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = { "com.udea.crudentregas", "controller", "service", "exception", "config" })
@EntityScan(basePackages = "domain")
@EnableJpaRepositories(basePackages = "repository")
public class CrudEntregasApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudEntregasApplication.class, args);
	}

}
