package com.tjf.checkin.survey.exception;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SurveySubmitConstraintException")
public class SurveySubmitConstraintException extends ConstraintViolationException {
	
	private static final long serialVersionUID = 2071517040052831080L;
	
	public SurveySubmitConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
		super(constraintViolations);
	}

}