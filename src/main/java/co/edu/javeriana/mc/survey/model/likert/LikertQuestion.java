package co.edu.javeriana.mc.survey.model.likert;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("51")
public class LikertQuestion extends SurveyItem {

    public LikertQuestion() {
    }

    public LikertQuestion(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, LikertGroup parent) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent, parent.getSurvey(), true);
    }

    
}