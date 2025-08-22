package com.ecommerce.project.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;


@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String randomUUID = UUID.randomUUID().toString();
        assert originalFilename != null;
        String fileName = randomUUID.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath=path+ File.separator  + fileName;

        File dest = new File(filePath);

        if(!dest.exists()){
            dest.mkdir();
        }

        Files.copy(file.getInputStream(), Paths.get(filePath+fileName), StandardCopyOption.REPLACE_EXISTING);
        return fileName;


    }
}
