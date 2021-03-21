package com.example.demo.controller;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;

import com.example.demo.model.Board;
import com.example.demo.model.Comment;
import com.example.demo.service.BoardService;
import com.sun.istack.NotNull;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.demo.service.FileService;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.FileModel;
import com.example.demo.service.FileService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class FileController {

    @Autowired
    private FileService fileService;


    @PostMapping(value = "/file")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile multipartFile) {

        File targetFile = new File("src/main/resources/static/imgs/"+multipartFile.getOriginalFilename());

        try {
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
        } catch (IOException e) {
            FileUtils.deleteQuietly(targetFile);
            e.printStackTrace();
        }
        Map<String, Object> m = new HashMap<>();
        m.put("errorCode", 10);
        return m;
    }

}
