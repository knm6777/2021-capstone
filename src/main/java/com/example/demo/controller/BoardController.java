package com.example.demo.controller;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Board;
import com.example.demo.service.BoardService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BoardController {

    @Autowired
    private BoardService boardService;

    // get all board
    @GetMapping("/board")
    public List<Board> getAllBoards() {

        return boardService.getAllBoard();
    }

    // create board
    @PostMapping("/board")
    public Board createBoard(@RequestBody Board board) {
        return boardService.createBoard(board);
    }

    // get board
    @GetMapping("/board/{idx}")
    public ResponseEntity<Board> getBoardByNo(
            @PathVariable Integer idx) {

        return boardService.getBoard(idx);
    }

    // update board
    @PutMapping("/board/{idx}")
    public ResponseEntity<Board> updateBoardByNo(
            @PathVariable Integer idx, @RequestBody Board board){

        return boardService.updateBoard(idx, board);
    }

    // delete board
    @DeleteMapping("/board/{idx}")
    public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(
            @PathVariable Integer idx) {

        return boardService.deleteBoard(idx);
    }

    // search board
    @GetMapping("/board/search")
    public List<Board> getCertainBoards(@RequestParam(value="type") String searchType,
                                        @RequestParam(value="keyword") String searchKeyword) {
        return boardService.getCertainBoard(searchType, searchKeyword);
    }



}