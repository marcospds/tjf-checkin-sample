package com.tjf.checkin.signup.api;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.HttpStatus.CREATED; 

import static com.totvs.tjf.api.context.stereotype.ApiGuideline.ApiGuidelineVersion.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.totvs.tjf.api.context.stereotype.ApiGuideline;
import com.tjf.checkin.signup.application.SignupService;
import com.tjf.checkin.signup.exception.SignupContraintException;
import com.tjf.checkin.signup.repository.ParticipantModel;
import com.tjf.checkin.signup.repository.ParticipantRepository;
import com.totvs.tjf.core.validation.ValidatorService;

@RestController
@RequestMapping(path = "/api/v1/signup", produces = { APPLICATION_JSON_VALUE })
@ApiGuideline(v1)
public class SignupController {

    @Autowired
    private ValidatorService validator;
    
    @Autowired
    private SignupService service;

    @PostMapping
    @ResponseStatus(CREATED)
    public ParticipantModel signUp(@RequestBody ParticipantModel participant) {
        
        validator.validate(participant).ifPresent(violations -> { 
            throw new SignupContraintException(violations); 
        });
        
        return service.signup(participant);
    }
}
