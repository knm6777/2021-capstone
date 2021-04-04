package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")

public class BoardController {

    @Autowired
    private BoardService boardService;

    // get paging board # 페이징 처리를 할 수 있도록 수정
    @GetMapping("/board")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
        if (p_num == null || p_num <= 0) p_num = 1;

        return boardService.getPagingBoard(p_num);
    }

    // get all board
    //@GetMapping("/board")
    // List<Board> getAllBoards() {

    //    return boardService.getAllBoard();
    //}

    // create board
    @PostMapping("/board")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // get board
    @GetMapping("/board/{idx}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Board> getBoardByNo(
            @PathVariable Integer idx) {

        return boardService.getBoard(idx);
    }

    // update board
    @PutMapping("/board/{idx}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Board> updateBoardByNo(
            @PathVariable Integer idx, @RequestBody Board board){

        return boardService.updateBoard(idx, board);
    }

    // delete board
    @DeleteMapping("/board/{idx}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
            @PathVariable Integer idx) {

        return boardService.deleteBoard(idx);
    }

    // search board
    @GetMapping("/board/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<Board> getCertainBoards(@RequestParam(value="type") String searchType,
                                        @RequestParam(value="keyword") String searchKeyword) {
        return boardService.getCertainBoard(searchType, searchKeyword);
    }



}