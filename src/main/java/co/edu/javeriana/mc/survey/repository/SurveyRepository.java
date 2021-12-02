package co.edu.javeriana.mc.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.mc.survey.model.Survey;

/**
* Generated by Spring Data Generator on 08/09/2021
*/
@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long>, JpaSpecificationExecutor<Survey> {

}