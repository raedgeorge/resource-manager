package com.atech.mongodbapp.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document
public class Employee {

    @Id
    private String id;

    @NotBlank(message = "required field first name")
    private String firstName;

    @NotBlank(message = "required field last name")
    private String lastName;

    private String email;

    private Byte[] image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @DBRef
    private List<Skill> skillsList = new ArrayList<>();

    @DBRef
    private List<Holiday> holidaysList = new ArrayList<>();

    private Position position;
}
