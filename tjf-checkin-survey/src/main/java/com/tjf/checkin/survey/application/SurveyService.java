package com.tjf.checkin.survey.application;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjf.checkin.survey.repository.SurveyModel;
import com.tjf.checkin.survey.repository.SurveyModelRepository;

@Service
@Transactional
public class SurveyService {

	@Autowired
	private SurveyModelRepository repo;
	
	public void addSurvey(SurveyModel dto) {
		repo.saveAndFlush(dto);
	}

}
