package com.example.demo.controller;

import com.example.demo.model.board.PhotoBoard;
import com.example.demo.service.PhotoBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<PhotoBoard>> getAllPhotos() {
        return ResponseEntity.ok(photoBoardService.getAllPhoto());
    }

    // create
    @PostMapping("/photo")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PhotoBoard> createBoard(@RequestBody PhotoBoard photo) {
        return new ResponseEntity(photoBoardService.createPhoto(photo), HttpStatus.CREATED);
    }

    // get board
    @GetMapping("/photo/{pboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PhotoBoard> getPhotoByNo(
            @PathVariable Integer pboardNo) {

        return ResponseEntity.ok(photoBoardService.getPhoto(pboardNo));
    }

    //update
    @PutMapping("/photo/{pboardNo}")
    //@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    @PreAuthorize("permitAll()")
    public ResponseEntity<PhotoBoard> updatePhotoByNo(@PathVariable Integer pboardNo, @RequestBody PhotoBoard updatePhoto){
        return ResponseEntity.ok(photoBoardService.updatePhoto(pboardNo, updatePhoto));
    }

    // delete board
    @DeleteMapping("/photo/{pboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Boolean>> deletePhotoByNo(
            @PathVariable Integer pboardNo) {

        return ResponseEntity.ok(photoBoardService.deletePhoto(pboardNo));
    }

    // search board 키워드 통합검색
    @GetMapping("/photo/search")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<PhotoBoard>> searchAllPhoto(@RequestParam(value="keyword") String searchKeyword){
        return ResponseEntity.ok(photoBoardService.searchAllPhoto(searchKeyword));
    }


}