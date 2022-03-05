package com.atech.mongodbapp.service;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService{

    private final EmployeeRepository employeeRepository;

    public ImageServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override

    public void saveImageFile(String empId, MultipartFile file) throws IOException {

        Employee employee = employeeRepository.findById(empId).orElse(null);

        Byte[] imageBytes = new Byte[file.getBytes().length];
        int i = 0;
        for ( byte imgByte : file.getBytes()){
            imageBytes[i++] = imgByte;
        }

        employee.setImage(imageBytes);
        employeeRepository.save(employee);
    }
}
