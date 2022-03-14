package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {



}
