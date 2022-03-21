package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.codecs.pojo.IdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;
    private Sort descending;

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

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {

        Sort ascending = Sort.by(sortField).ascending();
        Sort descending = Sort.by(sortField).descending();

        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? ascending : descending;

        Pageable pageable = PageRequest.of(pageNo -1, pageSize, sort);

        return employeeRepository.findAll(pageable);
    }
}
