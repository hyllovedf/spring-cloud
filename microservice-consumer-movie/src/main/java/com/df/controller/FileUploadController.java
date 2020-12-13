package com.df.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author hanyli
 * @date 2020/12/13
 */
@RestController
public class FileUploadController {
    @PostMapping("upload")
    public void upload(MultipartFile file) {

        System.out.println(file.getOriginalFilename());
    }
}
