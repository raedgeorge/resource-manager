package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.pojo.IdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<Employee> findAll() {

        List<Employee> employeeList = new ArrayList<>();
        employeeRepository.findAll().forEach(employeeList::add);
        return employeeList;
    }

    @Override
    public Employee findById(String id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public Employee save(Employee employee, String id) {

        log.debug("ID PASSED: " + id);

        if (id.equals("")) {
            employee.setId(IdGenerators.STRING_ID_GENERATOR.generate());
            return employeeRepository.save(employee);
        }
        employee.setId(id);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void delete(Employee employee) {
        employeeRepository.delete(employee);

    }
}
