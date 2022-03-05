package com.atech.mongodbapp.repository;

import com.atech.mongodbapp.entity.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
