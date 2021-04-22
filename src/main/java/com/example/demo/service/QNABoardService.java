package com.example.demo.service;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PhotoComment;
import com.example.demo.model.QNABoard;
import com.example.demo.model.QNAComment;
import com.example.demo.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.repository.QNABoardRepository;

@Service
public class QNABoardService {

    @Autowired
    private QNABoardRepository QNABoardRepository;
    @Autowired
    private QNACommentService qnaCommentService;

    public int findAllCount() {
        return (int) QNABoardRepository.count();
    }

    public int getLatestIdx(){
        QNABoard board = QNABoardRepository.findTopByOrderByQboardNoDesc(); // 가장 마지막 레코드 가져와서
        int boardIdx = board.getQboardNo(); // 그 레코드의 기본키를 리턴
        return boardIdx;
    }

    public String getFileUrl(Integer qboardNo){
        Optional<QNABoard> board = QNABoardRepository.findById(qboardNo);
        String fileUrl = board.get().getQboardFileUrl();
        return fileUrl;

    }

    // get paging boards data
    public ResponseEntity<Map> getPagingBoard(Integer p_num) {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<QNABoard> list = QNABoardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
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


    // get boards data
    public List<QNABoard> getAllBoard() {
        return QNABoardRepository.findAllByOrderByQboardNoDesc();
    }


    // create board
    public QNABoard createBoard(QNABoard board) {
        return QNABoardRepository.save(board);
    }

    // get board one by id
    public ResponseEntity<QNABoard> getBoard(Integer qboardNo) {
        QNABoard board = QNABoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by qboardNo : ["+qboardNo+"]"));
        return ResponseEntity.ok(board);
    }

    // update board
    public ResponseEntity<QNABoard> updateBoard(Integer qboardNo, QNABoard updatedBoard) {
        QNABoard board = QNABoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by qboardNo : ["+qboardNo+"]"));
        board.setQboardFileUrl(updatedBoard.getQboardFileUrl());
        board.setQboardTitle(updatedBoard.getQboardTitle());
        board.setQboardWriter(updatedBoard.getQboardWriter());
        board.setQboardContent(updatedBoard.getQboardContent());
        board.setQboardUpdateTime(LocalDateTime.now());

        QNABoard endUpdatedBoard = QNABoardRepository.save(board);
        return ResponseEntity.ok(endUpdatedBoard);
    }

    // delete board
    public ResponseEntity<Map<String, Boolean>> deleteBoard(
            Integer qboardNo) {
        QNABoard board = QNABoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by qboardNo : ["+qboardNo+"]"));

        List<QNAComment> qbl = qnaCommentService.getCommentById(qboardNo);
        for(QNAComment qc : qbl){
            qnaCommentService.deleteComment(qc.getQcommentNo(), board.getQboardNo());
        }

        board.setQboardComments(null);
        QNABoardRepository.delete(board);

        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Board Data by id : ["+qboardNo+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // search board
    public List<QNABoard> getCertainBoard(String searchType, String searchKeyword) {
        if(searchType.equals("title")){
            return QNABoardRepository.findAllByQboardTitleIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("content")){
            return QNABoardRepository.findAllByQboardContentIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("writer")){
            return QNABoardRepository.findAllByQboardWriterIgnoreCaseContaining(searchKeyword);
        }
        else{
            return QNABoardRepository.findAllByQboardTitleOrQboardContentOrQboardWriterIgnoreCaseContaining(searchKeyword, searchKeyword, searchKeyword);
        }

    }


}