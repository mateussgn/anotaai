package com.mateussgn.anota_ai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AnotaAiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnotaAiApplication.class, args);
	}

}
