package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.board.PhotoBoard;
import com.example.demo.model.board.PhotoComment;
import com.example.demo.repository.board.PhotoBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<PhotoBoard> getAllPhoto() {
        return photoBoardRepository.findAllByOrderByPboardNoDesc();
    }

    //create
    public PhotoBoard createPhoto(PhotoBoard photo) {
        return photoBoardRepository.save(photo);
    }

    // get photo one by id
    public PhotoBoard getPhoto(Integer pboardNo) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+pboardNo+"]"));
        int views = photo.getPboardViews()+1;
        photo.setPboardViews(views);

        return photoBoardRepository.save(photo);
    }


    //update
    public PhotoBoard updatePhoto(Integer pboardNo, PhotoBoard updatedPhoto) {
        PhotoBoard photo = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+pboardNo+"]"));

        photo.setPboardTitle(updatedPhoto.getPboardTitle());
        photo.setPboardWriter(updatedPhoto.getPboardWriter());
        photo.setPboardContent(updatedPhoto.getPboardContent());
        photo.setPboardUpdateTime(LocalDateTime.now());
        photo.setPboardFileUrl(updatedPhoto.getPboardFileUrl());

        return photoBoardRepository.save(photo);
    }

    // delete
    public Map<String, Boolean> deletePhoto(
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
        return response;
    }


    //search all by keyword
    public List<PhotoBoard> searchAllPhoto(String searchKeyword){

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