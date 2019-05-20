package com.tjf.checkin.signup.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("ParticipantContraintException")
public class ParticipantContraintException extends ConstraintViolationException {

    private static final long serialVersionUID = 2071517040052831080L;

    public ParticipantContraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(constraintViolations);
    }
}