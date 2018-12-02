package com.project.millatinventory.controller;


import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile; 

public class ValidateUpload {
   public static void validateOfficeData(MultipartFile file){
        if(!file.getContentType().equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
            throw new MultipartException("Only excel files accepted!");
    }
} 