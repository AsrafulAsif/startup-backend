package com.example.startup.startup.controller;

import com.example.startup.startup.dto.Asif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    final MongoTemplate mongoTemplate;

    @Autowired
    public UserController(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping
    public ResponseEntity<String> postValue(){
        Asif asif = new Asif();
        asif.setName("asif");
        mongoTemplate.save(asif,"aa");
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }
}
