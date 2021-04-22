package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.QNABoard;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
        if (p_num == null || p_num <= 0) p_num = 1;

        return QNABoardService.getPagingBoard(p_num);
    }

    // create board
    @PostMapping("/board")
    @PreAuthorize("permitAll()")
    public QNABoard createBoard(@RequestBody QNABoard board) {
        return QNABoardService.createBoard(board);
    }

    // get board
    @GetMapping("/board/{qboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<QNABoard> getBoardByNo(
            @PathVariable Integer qboardNo) {
        return QNABoardService.getBoard(qboardNo);
    }

    // update board
    @PutMapping("/board/{qboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<QNABoard> updateBoardByNo(
            @PathVariable Integer qboardNo, @RequestBody QNABoard board){

        return QNABoardService.updateBoard(qboardNo, board);
    }

    // delete board
    @DeleteMapping("/board/{qboardNo}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
            @PathVariable Integer qboardNo) {

        return QNABoardService.deleteBoard(qboardNo);
    }

    // search board
    @GetMapping("/board/search")
    @PreAuthorize("permitAll()")
    public List<QNABoard> getCertainBoards(@RequestParam(value="type") String searchType,
                                        @RequestParam(value="keyword") String searchKeyword) {
        return QNABoardService.getCertainBoard(searchType, searchKeyword);
    }



}