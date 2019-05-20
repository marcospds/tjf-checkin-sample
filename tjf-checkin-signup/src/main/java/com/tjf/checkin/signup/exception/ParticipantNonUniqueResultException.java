package com.tjf.checkin.signup.exception;

import javax.persistence.NonUniqueResultException;

import com.totvs.tjf.api.context.stereotype.error.ApiBadRequest;

@ApiBadRequest("ParticipantNonUniqueResultException")
public class ParticipantNonUniqueResultException extends NonUniqueResultException {

    private static final long serialVersionUID = -4240614994020766468L;
}