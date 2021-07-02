package com.jzcccc.springrediscachedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringRedisCacheDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRedisCacheDemoApplication.class, args);
	}

}
