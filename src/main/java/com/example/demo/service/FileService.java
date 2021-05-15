package com.example.demo.service;


import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.Object;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class FileService {
    private String uploadFile(File file, String fileName) throws IOException {
        BlobId blobId = BlobId.of("dzbz2021.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C://dev//dzbz2021-firebase-adminsdk-8q8nk-9464c6a8f4.json"));
        //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("//Users//10000say//Desktop//dzbz2021-firebase-adminsdk-8q8nk-9464c6a8f4.json"));

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format("https://firebasestorage.googleapis.com/v0/b/dzbz2021.appspot.com/o/%s?alt=media", URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public Object upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            //fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            return ResponseEntity.ok("Successfully Uploaded !"+TEMP_URL);                     // Your customized response
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("500 Unsuccessfully Uploaded!");
        }

    }

    public ResponseEntity<byte[]> download(String fileName) throws IOException {
        //String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
        String destFileName = fileName;
        String destFilePath = "C://Temp//imgFolder//" + destFileName;                                    // to set destination file path
        //String destFilePath = "//Users//10000say//Desktop//imgFolder//" + destFileName;

        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C://dev//dzbz2021-firebase-adminsdk-8q8nk-9464c6a8f4.json"));
        //Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("//Users//10000say//Desktop//dzbz2021-firebase-adminsdk-8q8nk-9464c6a8f4.json"));

        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("dzbz2021.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));


        InputStream imageStream = new FileInputStream(destFilePath);
        byte[] imageByteArray = IOUtils.toByteArray(imageStream);
        imageStream.close();
        return new ResponseEntity<byte[]>(imageByteArray, HttpStatus.OK);
    }






}
