package com.productmicroservice.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ProductMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductMicroServiceApplication.class, args);
    }

}
