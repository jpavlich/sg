package co.edu.javeriana.mc.survey;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.persistence.DiscriminatorValue;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.javeriana.mc.survey.answers.AnswerGroup;
import co.edu.javeriana.mc.survey.answers.AnswerRepository;
import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.repository.SurveyRepository;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    AnswerRepository surveyLC;

    @Autowired
    SurveyRepository surveyRepository;
    
    @Autowired
    EntityManagerFactory emf;


    Map<Long, String> surveyItemCodes = new LinkedHashMap<>();

    @PostConstruct
    void init() {
        for(ManagedType<?> mt : emf.getMetamodel().getManagedTypes()) {
            DiscriminatorValue annotation = mt.getJavaType().getAnnotation(DiscriminatorValue.class);
            if (annotation != null) {
                Long val = Long.valueOf(annotation.value());
                surveyItemCodes.put(val, mt.getJavaType().getSimpleName());
            }
        }
    }

    @GetMapping("/{surveyId}/answer/{answerId}")
    void answer(@PathVariable Long surveyId, @PathVariable Long answerId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow();
        AnswerGroup result = surveyLC.findAnswersById(survey, answerId);
        log.info("{}", result);

    }

    @GetMapping("{id}")
    Survey survey(@PathVariable Long id) {
        return surveyRepository.findById(id).orElseThrow();
    }

    @GetMapping("survey-item-codes") 
    Map<Long, String> surveyItemCodes() {
        return surveyItemCodes;
    }

}
