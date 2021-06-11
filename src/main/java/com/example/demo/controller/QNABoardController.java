package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.board.QNABoard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.QNABoardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class QNABoardController {

    @Autowired
    private QNABoardService QNABoardService;


    // get paging board # 페이징 처리를 할 수 있도록 수정
    @GetMapping("/board")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num){
        if (p_num == null || p_num <= 0) p_num = 1;
        return ResponseEntity.ok(QNABoardService.getPagingBoard(p_num));
    }

    // create board
    @PostMapping("/board")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<QNABoard> createBoard(@RequestBody QNABoard board) {
        return new ResponseEntity(QNABoardService.createBoard(board), HttpStatus.CREATED);
    }

    // get board
    @GetMapping("/board/{qboardNo}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<QNABoard> getBoardByNo(
            @PathVariable Integer qboardNo) {
        return ResponseEntity.ok(QNABoardService.getBoard(qboardNo));
    }

    // update board
    @PutMapping("/board/{qboardNo}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<QNABoard> updateBoardByNo(
            @PathVariable Integer qboardNo, @RequestBody QNABoard board){

        return ResponseEntity.ok(QNABoardService.updateBoard(qboardNo, board));
    }

    // delete board
    @DeleteMapping("/board/{qboardNo}")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<?> deleteBoardByNo(
            @PathVariable Integer qboardNo) {
        return ResponseEntity.ok(QNABoardService.deleteBoard(qboardNo));
    }

    // search board
    @GetMapping("/board/search")
    @PreAuthorize("hasAnyRole('ROLE_USER, ROLE_ADMIN')")
    public ResponseEntity<List<QNABoard>> searchAllBoard(@RequestParam(value="keyword") String searchKeyword) {
        return ResponseEntity.ok(QNABoardService.searchAllBoard(searchKeyword));
    }

}