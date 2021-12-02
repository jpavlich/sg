package co.edu.javeriana.mc.survey.model.scalars;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("20")
public class BooleanQuestion extends SurveyItem {

    public BooleanQuestion() {
    }

    public BooleanQuestion(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, SurveyItem parent) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent, parent.getSurvey(), true);
    }

}