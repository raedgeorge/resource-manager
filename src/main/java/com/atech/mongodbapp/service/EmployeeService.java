package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(String id);

    Employee save(Employee employee, String id);

    void deleteById(String id);

    void delete(Employee employee);

}
