package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.board.PhotoBoard;
import com.example.demo.model.board.PhotoComment;
import com.example.demo.repository.board.PhotoBoardRepository;
import com.example.demo.repository.board.PhotoCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoCommentService  {

    @Autowired
    private PhotoCommentRepository photoCommentRepository;

    @Autowired
    private PhotoBoardRepository photoBoardRepository;

    // 글번호 id인 글의 댓글들 가져오기
    public List<PhotoComment> getPhotoCommentById(Integer boardNo) {
        PhotoBoard photoBoard = photoBoardRepository.findById(boardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+boardNo+"]"));
        //System.out.println(photoBoard.getPhotoComments().get(1).getPcommentNo());

        return photoCommentRepository.findAllByPboardNo(photoBoard);
    }

    //한명이 쓴 댓글 모두 찾기
    public List<PhotoComment> getPhotoCommentByWriter(String writer){
        return photoCommentRepository.findAllByPcommentWriter(writer);
    }

    // create comment
    public PhotoComment createPhotoComment(PhotoComment pComment, Integer boardNo) {
        //pboard_no로 photoboard 객체 찾기
        PhotoBoard photoBoard = photoBoardRepository.findById(boardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+boardNo+"]"));


        pComment.setPboardNo(photoBoard);
        PhotoComment saveComment = photoCommentRepository.save(pComment);
        photoBoard.getPhotoComments().add(saveComment);
//        photoBoardRepository.save(photoBoard);
//        photoBoardService.updateCmtList(boardNo, saveComment);


        return saveComment;
    }

    // update comment
    public ResponseEntity<PhotoComment> updatePhotoComment(Integer pcommentNo, PhotoComment updatedComment, Integer boardNo) {

        //pboard_no로 photoboard 객체 찾기
        PhotoBoard photoBoard = photoBoardRepository.findById(boardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+boardNo+"]"));
        //comment 객체 찾기
        PhotoComment photoComment = photoCommentRepository.findByPcommentNoAndPboardNo(pcommentNo, photoBoard)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+pcommentNo+"]"));
        photoComment.setPcommentWriter(updatedComment.getPcommentWriter());
        photoComment.setPcommentContent(updatedComment.getPcommentContent());
        photoComment.setPcommentUpdateTime(LocalDateTime.now());

        PhotoComment endUpdatedPhotoComment = photoCommentRepository.save(photoComment);
        return ResponseEntity.ok(endUpdatedPhotoComment);
    }

    // delete comment 댓글번호를 통해
    public ResponseEntity<Map<String, Boolean>> deletePhotoComment(Integer pcommentNo, Integer pboardNo) {
       PhotoBoard photoBoard = photoBoardRepository.findById(pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+pboardNo+"]"));
       PhotoComment photoComment = photoCommentRepository.findByPcommentNoAndPboardNo(pcommentNo, photoBoard)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+pcommentNo+"]"));
       photoCommentRepository.delete(photoComment);
       Map<String, Boolean> response = new HashMap<>();
       response.put("Deleted Comment Data by id : ["+pcommentNo+"]", Boolean.TRUE);
       return ResponseEntity.ok(response);
    }


}
