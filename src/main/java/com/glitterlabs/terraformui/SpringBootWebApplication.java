package com.glitterlabs.terraformui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringBootWebApplication {

	public static void main(final String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}
}