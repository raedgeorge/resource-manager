package com.atech.mongodbapp.entity.products;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Setter
@Getter
@Document
public class Fire {

    private String id = UUID.randomUUID().toString();
    private int quantity;
    private String description;
    private String tybeNumber;
    private Brands brands;
}
