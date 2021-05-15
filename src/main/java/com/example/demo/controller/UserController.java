package com.example.demo.controller;

import com.example.demo.model.user.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //유저정보 가져오기(권한이 있을 경우에 만이고싶달까 ㅋ)
    @GetMapping("/user")
    @PreAuthorize("permitAll()")
    public ResponseEntity<User> getPhotoByNo(@RequestParam(value="id") String userid) {

        return userService.getUserById(userid);
    }

}