package com.tjf.checkin.survey.application;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjf.checkin.survey.exception.SurveySubmitNonUniqueResultException;
import com.tjf.checkin.survey.repository.SurveyModel;
import com.tjf.checkin.survey.repository.SurveyModelRepository;

@Service
@Transactional
public class SurveyService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private SurveyModelRepository repo;

	public void addSurvey(SurveyModel dto) {
		if (!existsSurveyByEmailAndEvent(dto.getEmail(), dto.getEvent())) {
			repo.saveAndFlush(dto);
		} else {
			throw new SurveySubmitNonUniqueResultException(); 
		}

	}

	boolean existsSurveyByEmailAndEvent(String email, String event) {

		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();

		CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
		query.from(SurveyModel.class);
		query.select(criteriaBuilder.literal(true));

		Subquery<SurveyModel> subquery = query.subquery(SurveyModel.class);

		Root<SurveyModel> survey = subquery.from(SurveyModel.class);

		Predicate emailNamePredicate = criteriaBuilder.equal(survey.get("email"), email);
		Predicate eventPredicate = criteriaBuilder.equal(survey.get("event"), event);

		subquery.where(emailNamePredicate, eventPredicate);

		query.where(criteriaBuilder.exists(subquery));

		subquery.select(survey);

		TypedQuery<Boolean> typedQuery = em.createQuery(query);

		try {
			return typedQuery.getSingleResult();
		} catch (NoResultException exception) {
			return false;
		} catch (NonUniqueResultException exception) {
			return true;
		}
	}

}
