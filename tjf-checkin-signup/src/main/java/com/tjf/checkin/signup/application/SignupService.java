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
        
        if(existsParticipantByEmailOrMacAdress(participant.getEmail(), participant.getMacAddress())){
            System.out.println("##############################");
            System.out.println("exist");
            System.out.println("##############################");
            throw new RuntimeException("Duplicado email ou macAddress");
        } else {
            System.out.println("##############################");
            System.out.println("no exist");
            System.out.println("##############################");
            return repository.save(participant);
        }       
    }
    
    private boolean existsParticipantByEmailOrMacAdress(String email, String macAddress) {
        
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
        Root<ParticipantModel> participantRoot = query.from(ParticipantModel.class);
        
        Predicate emailPredicate = criteriaBuilder.equal(participantRoot.get("email"), email);
        Predicate macAddressPredicate = criteriaBuilder.equal(participantRoot.get("macAddress"), macAddress);
        Predicate orPredicate = criteriaBuilder.or(emailPredicate, macAddressPredicate);
        
        query.select(criteriaBuilder.literal(true));
        query.where(orPredicate);        
        
        TypedQuery<Boolean> typedQuery = em.createQuery(query);
        
        boolean exists = false;
        
        try {
            exists = typedQuery.getSingleResult();
        } catch(NoResultException exception) {
            exists = false;
        } catch(NonUniqueResultException exception) {
            exists = true;
        }
        
        em.close();
        
        return exists;
    }
}