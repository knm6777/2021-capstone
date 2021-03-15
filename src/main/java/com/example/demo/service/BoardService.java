package com.example.demo.service;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Board;
import com.example.demo.repository.BoardRepository;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // get boards data
    public List<Board> getAllBoard() {
        return boardRepository.findAllByOrderByIdxDesc();
    }


    // create board
    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    // get board one by id
    public ResponseEntity<Board> getBoard(Integer idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+idx+"]"));
        return ResponseEntity.ok(board);
    }

    // update board
    public ResponseEntity<Board> updateBoard(Integer idx, Board updatedBoard) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+idx+"]"));
        board.setNoticeYn(updatedBoard.getNoticeYn());
        board.setSecretYn(updatedBoard.getSecretYn());
        board.setTitle(updatedBoard.getTitle());
        board.setWriter(updatedBoard.getWriter());
        board.setContent(updatedBoard.getContent());
        board.setUpdateTime(LocalDateTime.now());

        Board endUpdatedBoard = boardRepository.save(board);
        return ResponseEntity.ok(endUpdatedBoard);
    }

    // delete board
    public ResponseEntity<Map<String, Boolean>> deleteBoard(
            Integer idx) {
        Board board = boardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+idx+"]"));

        boardRepository.delete(board);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+idx+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // search board
    public List<Board> getCertainBoard(String searchType, String searchKeyword) {
        if(searchType.equals("title")){
            return boardRepository.findAllByTitleIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("content")){
            return boardRepository.findAllByContentIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("writer")){
            return boardRepository.findAllByWriterIgnoreCaseContaining(searchKeyword);
        }
        else{
            return boardRepository.findAllByTitleOrContentOrWriterIgnoreCaseContaining(searchKeyword, searchKeyword, searchKeyword);
        }

    }


}