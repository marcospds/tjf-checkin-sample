package com.tjf.checkin.signup.application;

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

import com.tjf.checkin.signup.repository.ParticipantModel;
import com.tjf.checkin.signup.repository.ParticipantRepository;

@Service
@Transactional
public class SignupService {

    @PersistenceContext
    private EntityManager em;
    
    @Autowired
    private ParticipantRepository repository;

    public ParticipantModel signup(ParticipantModel participant) {
        
        if(existsParticipantByNameOrMacAdress(participant.getName(), participant.getMacAddress())){
            System.out.println("##############################");
            System.out.println("exist");
            System.out.println("##############################");
        } else {
            System.out.println("##############################");
            System.out.println("no exist");
            System.out.println("##############################");
        }
        
        return repository.save(participant);
    }
    
    public boolean isValid(ParticipantModel participant) {
        
        return true;
    }
    
    boolean existsParticipantByNameOrMacAdress(String name, String macAddress) {
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        
        CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
        query.from(ParticipantModel.class);
        query.select(criteriaBuilder.literal(true));
        
        Subquery<ParticipantModel> subquery = query.subquery(ParticipantModel.class);
 
        Root<ParticipantModel> participant = subquery.from(ParticipantModel.class);
        
        Predicate authorNamePredicate = criteriaBuilder.equal(participant.get("name"), name);
        Predicate titlePredicate = criteriaBuilder.equal(participant.get("macAddress"), macAddress);
        Predicate orTitlePredicate = criteriaBuilder.or(titlePredicate);
        
        subquery.where(authorNamePredicate, orTitlePredicate);        
        
        query.where(criteriaBuilder.exists(subquery));
        
        subquery.select(participant);
        
        TypedQuery<Boolean> typedQuery = em.createQuery(query);
        
        try {
            // There's one
            return typedQuery.getSingleResult();
        } catch(NoResultException exception) {
            // Zero
            return false;
        } catch(NonUniqueResultException exception) {
            // More than one
            return true;
        }
    }
}
