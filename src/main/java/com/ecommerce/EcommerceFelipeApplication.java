package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ecommerce") // Ajusta según tu estructura de paquetes
public class EcommerceFelipeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcommerceFelipeApplication.class, args);
	}

}
