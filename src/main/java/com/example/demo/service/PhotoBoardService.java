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
    public ResponseEntity<Map> getPagingPhoto(Integer p_num) throws IOException {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 9, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<PhotoBoard> list = photoBoardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());


        /// 파일 처리 하는 부분 ///

        for(int i=0;i<list.size();i++){ //리스트 사이즈만큼 반복문 돌면서 각 리스트 파일 url 가져오고

            String destFileName = list.get(i).getPboardFileUrl();
            String destFilePath = "C://Temp//imgFolder//" + destFileName; // to set destination file path

            ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
            Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("C://dev//dzbz2021-firebase-adminsdk-8q8nk-9464c6a8f4.json"));
            Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
            Blob blob = storage.get(BlobId.of("dzbz2021.appspot.com", destFileName));
            blob.downloadTo(Paths.get(destFilePath));


            InputStream imageStream = new FileInputStream(destFilePath);
            byte[] imageByteArray = IOUtils.toByteArray(imageStream);
            String str = Base64Utils.encodeToString(imageByteArray);
            list.get(i).setPboardFileUrl(str);
            imageStream.close();
        }


        /// 파일 처리 끝 ///

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

    // search
    public List<PhotoBoard> getCertainPhoto(String searchType, String searchKeyword) {
        if(searchType.equals("title")){
            return photoBoardRepository.findAllByPboardTitleIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("content")){
            return photoBoardRepository.findAllByPboardContentIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("writer")){
            return photoBoardRepository.findAllByPboardWriterIgnoreCaseContaining(searchKeyword);
        }
        else{
            return photoBoardRepository.findAllByPboardTitleOrPboardContentOrPboardWriterIgnoreCaseContaining(searchKeyword, searchKeyword, searchKeyword);
        }

    }



}
