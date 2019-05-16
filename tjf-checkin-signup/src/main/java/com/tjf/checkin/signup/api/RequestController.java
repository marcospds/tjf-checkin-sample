package com.tjf.checkin.signup.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tjf.checkin.signup.repository.User;
import com.tjf.checkin.signup.repository.UserRepository;
import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion;

@RestController
@RequestMapping(path = "/api/v1/signup", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(ApiGuidelineVersion.v1)
public class RequestController {

    @Autowired
    private UserRepository repository;
    
    @Lazy
    @Autowired
    private ConversionService conversionService;
    
    @PostMapping(path = "/", consumes = { APPLICATION_JSON_VALUE })
    ResponseEntity<User> submitRequest(@RequestBody User user) {
        
        return new ResponseEntity<User>(repository.save(conversionService.convert(user, User.class)), HttpStatus.CREATED);
    }
}