package co.edu.javeriana.mc.survey.model.multi.choices;

import javax.persistence.Entity;

import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
public abstract class Choice extends SurveyItem {

    public Choice() {
    }

    public Choice(String title, String shortTitle, String description, Integer surveyItemOrder, String defaultValue,
            SurveyItem parent) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent, parent.getSurvey(), false);
    }




}