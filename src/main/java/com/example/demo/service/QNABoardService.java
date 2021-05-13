package com.example.demo.service;
import java.time.LocalDateTime;
import java.util.*;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.QNABoard;
import com.example.demo.model.QNAComment;
import com.example.demo.util.PagingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.repository.board.QNABoardRepository;

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
    public Map<PagingUtil, QNABoard> getPagingBoard(Integer p_num){
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

        return result;
    }


    // get boards data
    public List<QNABoard> getAllBoard() {
        return QNABoardRepository.findAllByOrderByQboardNoDesc();
    }


    // create board
    public QNABoard createBoard(QNABoard board) {
        QNABoard qnaBoard = QNABoardRepository.save(board);
        return qnaBoard;
    }

    // get board one by id
    public QNABoard getBoard(Integer qboardNo) {
        QNABoard board = QNABoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by qboardNo : ["+qboardNo+"]"));
        return board;
    }



    // update board
    public QNABoard updateBoard(Integer qboardNo, QNABoard updatedBoard) {
        QNABoard board = QNABoardRepository.findById(qboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by qboardNo : ["+qboardNo+"]"));
        board.setQboardFileUrl(updatedBoard.getQboardFileUrl());
        board.setQboardTitle(updatedBoard.getQboardTitle());
        board.setQboardWriter(updatedBoard.getQboardWriter());
        board.setQboardContent(updatedBoard.getQboardContent());
        board.setQboardUpdateTime(LocalDateTime.now());

        QNABoard endUpdatedBoard = QNABoardRepository.save(board);
        return endUpdatedBoard;
    }

    // delete board
    public Map<String, Boolean> deleteBoard(
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
        return response;
    }

    // search board
    public List<QNABoard> searchAllBoard(String searchKeyword) {
        List<QNABoard> searchQna = new ArrayList<>();
        List<QNABoard> list1 = QNABoardRepository.findAllByQboardTitleIgnoreCaseContaining(searchKeyword);
        if(!list1.isEmpty()){
            searchQna.addAll(list1);
        }
        List<QNABoard> list2 = QNABoardRepository.findAllByQboardContentIgnoreCaseContaining(searchKeyword);
        if(!list2.isEmpty()){
            searchQna.addAll(list2);
        }
        List<QNABoard> list3 = QNABoardRepository.findAllByQboardWriterIgnoreCaseContaining(searchKeyword);
        if(!list3.isEmpty()){
            searchQna.addAll(list3);
        }

        return searchQna;

    }


}