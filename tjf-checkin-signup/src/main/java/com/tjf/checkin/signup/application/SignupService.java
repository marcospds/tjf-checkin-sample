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
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tjf.checkin.signup.exception.ParticipantNonUniqueResultException;
import com.tjf.checkin.signup.repository.ParticipantModel;
import com.tjf.checkin.signup.repository.ParticipantRepository;

@Service
@Transactional
public class SignupService {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private ParticipantRepository repository;

    public ParticipantModel signup(ParticipantModel participant) {        
        validUniqueParticipant(participant);
        return repository.save(participant);
    }
    
    private void validUniqueParticipant (ParticipantModel participant) {        
        if(existsParticipantByEmailOrMacAdress(participant.getEmail(), participant.getMacAddress()))
            throw new ParticipantNonUniqueResultException();
    }
    
    private boolean existsParticipantByEmailOrMacAdress(String email, String macAddress) {
        
        boolean exists;
        
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        TypedQuery<Boolean> typedQuery = createQueryByEmailOrMacAdress(criteriaBuilder, email, macAddress);
        
        try {
            exists = typedQuery.getSingleResult();
        } catch(NoResultException exception) {
            exists = false;
        } catch(NonUniqueResultException exception) {
            exists = true;
        }
        
        entityManager.close();
        
        return exists;
    }
    
    private TypedQuery<Boolean> createQueryByEmailOrMacAdress(CriteriaBuilder criteriaBuilder, String email,
            String macAddress) {

        CriteriaQuery<Boolean> query = criteriaBuilder.createQuery(Boolean.class);
        query.select(criteriaBuilder.literal(true));
        Root<ParticipantModel> participantRoot = query.from(ParticipantModel.class);
        query.where(createPredicateByEmailOrMacAdress(criteriaBuilder, participantRoot, email, macAddress));

        return entityManager.createQuery(query);
    }

    private Predicate createPredicateByEmailOrMacAdress(CriteriaBuilder criteriaBuilder,
            Root<ParticipantModel> participantRoot, String email, String macAddress) {
        
        Predicate emailPredicate = criteriaBuilder.equal(participantRoot.get("email"), email);
        Predicate macAddressPredicate = criteriaBuilder.equal(participantRoot.get("macAddress"), macAddress);
        return criteriaBuilder.or(emailPredicate, macAddressPredicate);
    }
}