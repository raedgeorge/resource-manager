package com.atech.mongodbapp.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {

    void saveImageFile(String empId, MultipartFile file) throws IOException;
}
