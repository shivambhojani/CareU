package com.group6.careu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@Configuration
public class CareuApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(CareuApplication.class, args);
	}
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Register resource handler for images
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/static/","classpath:/images/","classpath:/doctorPrescription/images/");
	}
}
