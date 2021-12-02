package co.edu.javeriana.mc.survey.model.multi.choices;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("21")
public class IntegerChoice extends Choice {

    public IntegerChoice() {
    }

    public IntegerChoice(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, SurveyItem parent) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent);
    }


    


}