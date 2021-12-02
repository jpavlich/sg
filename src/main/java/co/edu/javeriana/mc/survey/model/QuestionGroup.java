package co.edu.javeriana.mc.survey.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("5")
public class QuestionGroup extends SurveyItem {

    public QuestionGroup() {
    }

    public QuestionGroup(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent, survey, false);
    }


}