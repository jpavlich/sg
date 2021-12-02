package co.edu.javeriana.mc.survey.model.scalars;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("10")
public class DateTimeQuestion extends SurveyItem {

    public DateTimeQuestion() {
    }

    public DateTimeQuestion(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue,SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue,parent,survey, true);
    }



}