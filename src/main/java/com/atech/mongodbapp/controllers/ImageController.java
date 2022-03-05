package com.atech.mongodbapp.controllers;

import com.atech.mongodbapp.entity.Employee;
import com.atech.mongodbapp.service.EmployeeService;
import com.atech.mongodbapp.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/images")
public class ImageController {

    private final EmployeeService employeeService;
    private final ImageService imageService;

    public ImageController(EmployeeService employeeService, ImageService imageService) {
        this.employeeService = employeeService;
        this.imageService = imageService;
    }

    @GetMapping("/{empId}/empImage")
    public void readImageFromDB(@PathVariable String empId, HttpServletResponse response) throws IOException {

        Employee employee = employeeService.findById(empId);
        byte[] bytes = new byte[0];

        try {
             bytes = new byte[employee.getImage().length];

            int i = 0;
            for (byte imgByte : employee.getImage()) {
                bytes[i++] = imgByte;
            }
        }
        catch (Exception exception){

        }

        response.setContentType("image/jpg");
        InputStream inputStream = new ByteArrayInputStream(bytes);
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/{empId}/update")
    public String updateImageForm(@PathVariable String empId){

        return "employees/add-ImageForm";
    }

    @PostMapping("/{empId}/save")
    public String saveImage(@PathVariable String empId,
                            @RequestParam("imageFile") MultipartFile file) throws IOException {

        imageService.saveImageFile(empId, file);

        return "redirect:/employees/" + empId + "/details";
    }
}
