package com.atech.mongodbapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class Skill {

    @Id
    private String id;
    private String description;

    @DBRef
    private List<Employee> employeeList = new ArrayList<>();

}
