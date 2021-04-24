package com.example.demo.controller;

import com.example.demo.model.Item;
import com.example.demo.model.PhotoBoard;
import com.example.demo.model.QNABoard;
import com.example.demo.service.ItemService;
import com.example.demo.service.PhotoBoardService;
import com.example.demo.service.QNABoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MainBoardController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private PhotoBoardService photoBoardService;
    @Autowired
    private QNABoardService qnaBoardService;

    @PreAuthorize("permitAll()")
    @GetMapping("/main/search/items")
    List<Item> searchItems(@RequestParam(value="keyword") String searchKeyword) {

        return itemService.searchAllItems(searchKeyword);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/main/search/photos")
    List<PhotoBoard> searchPhotos(@RequestParam(value="keyword") String searchKeyword) {
        return photoBoardService.searchAllPhoto(searchKeyword);
    }
    @PreAuthorize("permitAll()")
    @GetMapping("/main/search/qna")
    List<QNABoard> searchQna(@RequestParam(value="keyword") String searchKeyword) {
        return qnaBoardService.searchAllBoard(searchKeyword);
    }

}
