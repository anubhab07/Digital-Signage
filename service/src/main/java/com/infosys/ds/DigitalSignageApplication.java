package com.infosys.ds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:db.properties")
@PropertySource("classpath:ds.properties")
public class DigitalSignageApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalSignageApplication.class, args);
	}
}
