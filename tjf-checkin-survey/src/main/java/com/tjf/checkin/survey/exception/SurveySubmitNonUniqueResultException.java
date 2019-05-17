package com.tjf.checkin.survey.exception;

import javax.persistence.NonUniqueResultException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("SurveySubmitNonUniqueResultException")
public class SurveySubmitNonUniqueResultException extends NonUniqueResultException {

	private static final long serialVersionUID = -2588331690625306253L;

}
