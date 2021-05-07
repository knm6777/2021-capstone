package com.example.demo.service;


import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // 회원정보 가져오기
    public ResponseEntity<User> getUserById(String id) {

        User user = userRepository.findAllById(id);

        if(user == null) {
            return null;
        }
        return ResponseEntity.ok(user);
    }

}