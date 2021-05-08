package com.example.demo.controller;

import com.example.demo.model.PhotoBoard;
import com.example.demo.service.PhotoBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PhotoBoardController {

    @Autowired
    private PhotoBoardService photoBoardService;

    // get paging board # 페이징 처리를 할 수 있도록 수정
    @GetMapping("/photo")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map> getAllPhotos(@RequestParam(value = "p_num", required=false) Integer p_num) throws IOException {
        if (p_num == null || p_num <= 0) p_num = 1;
        //System.out.println(p_num);
        return photoBoardService.getPagingPhoto(p_num);
    }

    // create
    @PostMapping("/photo")
    @PreAuthorize("permitAll()")
    public PhotoBoard createBoard(@RequestBody PhotoBoard photo) {
        return photoBoardService.createPhoto(photo);
    }

    // get board
    @GetMapping("/photo/{pboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PhotoBoard> getPhotoByNo(
            @PathVariable Integer pboardNo) {

        return photoBoardService.getPhoto(pboardNo);
    }

    //update
    @PutMapping("/photo/{pboardNo}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PhotoBoard> updatePhotoByNo(@PathVariable Integer pboardNo, @RequestBody PhotoBoard updatePhoto){
        return photoBoardService.updatePhoto(pboardNo, updatePhoto);
    }

    // delete board
    @DeleteMapping("/photo/{pboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Boolean>> deletePhotoByNo(
            @PathVariable Integer pboardNo) {

        return photoBoardService.deletePhoto(pboardNo);
    }

    // search board 키워드 통합검색
    @GetMapping("/photo/search")
    @PreAuthorize("permitAll()")
    public List<PhotoBoard> searchAllPhoto(@RequestParam(value="keyword") String searchKeyword) throws IOException{
        return photoBoardService.searchAllPhoto(searchKeyword);
    }


}