package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PhotoBoard;
import com.example.demo.model.PhotoComment;
import com.example.demo.repository.board.PhotoBoardRepository;
import com.example.demo.util.PagingUtil;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PhotoBoardService {

    @Autowired
    private PhotoBoardRepository photoBoardRepository;

    @Autowired
    private PhotoCommentService photoCommentService;

    public String getFileUrl(Integer pboardNo){
        Optional<PhotoBoard> board = photoBoardRepository.findById(pboardNo);
        String fileUrl = board.get().getPboardFileUrl();
        return fileUrl;

    }

    public int findAllCount() {
        return (int) photoBoardRepository.count();
    }

    // get paging boards data
    public ResponseEntity<Map> getPagingPhoto(Integer p_num){
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 9, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<PhotoBoard> list = photoBoardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());


        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        if (list == null || list.size() == 0) {
            return null;
        }

        result = new HashMap<>();
        result.put("pagingData", pu);
        result.put("list", list);

        return ResponseEntity.ok(result);
    }

    public List<PhotoBoard> getAllPhoto() {
        return photoBoardRepository.findAllByOrderByPboardNoDesc();
    }

    //create
    public PhotoBoard createPhoto(PhotoBoard photo) {
        return photoBoardRepository.save(photo);
    }

    // get photo one by id
    public ResponseEntity<PhotoBoard> getPhoto(Integer pboardNo) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+pboardNo+"]"));


        return ResponseEntity.ok(photo);
    }


    //update
    public ResponseEntity<PhotoBoard> updatePhoto(Integer pboardNo, PhotoBoard updatedPhoto) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+pboardNo+"]"));

        photo.setPboardTitle(updatedPhoto.getPboardTitle());
        photo.setPboardWriter(updatedPhoto.getPboardWriter());
        photo.setPboardContent(updatedPhoto.getPboardContent());
        photo.setPboardUpdateTime(LocalDateTime.now());
        photo.setPboardFileUrl(updatedPhoto.getPboardFileUrl());

        PhotoBoard endUpdatedPhoto = photoBoardRepository.save(photo);
        return ResponseEntity.ok(endUpdatedPhoto);
    }

    //댓글 리스트 update
    public ResponseEntity<PhotoBoard> updateCmtList(Integer pboardNo, PhotoComment photoComment) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+pboardNo+"]"));
        System.out.println(photoComment.getPcommentContent());
        photo.getPhotoComments().add(photoComment);

        PhotoBoard endUpdatedPhoto = photoBoardRepository.save(photo);
        return ResponseEntity.ok(endUpdatedPhoto);
    }

    // delete
    public ResponseEntity<Map<String, Boolean>> deletePhoto(
            Integer pboardNo) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Board Data by idx : ["+pboardNo+"]"));

//        List<PhotoComment> photoComments = photo.getPhotoComments();
//
        List<PhotoComment> pbl = photoCommentService.getPhotoCommentById(pboardNo);
        for(PhotoComment pc : pbl){
            photoCommentService.deletePhotoComment(pc.getPcommentNo(), photo.getPboardNo());
        }

        photo.setPhotoComments(null);
        photoBoardRepository.delete(photo);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Photo Board Data by id : ["+pboardNo+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


    //search all by keyword
    public List<PhotoBoard> searchAllPhoto(String searchKeyword) throws IOException{

        List<PhotoBoard> searchPhoto = new ArrayList<>();
        List<PhotoBoard> list1 = photoBoardRepository.findAllByPboardContentIgnoreCaseContaining(searchKeyword);

        if(!list1.isEmpty()){
            searchPhoto.addAll(list1);
        }
        List<PhotoBoard> list2 = photoBoardRepository.findAllByPboardTitleIgnoreCaseContaining(searchKeyword);


        if(!list2.isEmpty()){
            searchPhoto.addAll(list2);
        }

        List<PhotoBoard> list3 = photoBoardRepository.findAllByPboardWriterIgnoreCaseContaining(searchKeyword);


        if(!list3.isEmpty()){
            searchPhoto.addAll(list3);
        }

        return searchPhoto;

    }



}