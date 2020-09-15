package com.techgentsia.ecomexample.productms.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public @Data class Product extends DBTimestamps implements  Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Description can't be empty")
    private String description;
    private String img;
    private BigDecimal price = BigDecimal.valueOf(0);

}
