package com.springBoot.curdOperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CurdOperationApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurdOperationApplication.class, args);
	}

}
