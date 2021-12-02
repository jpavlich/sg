package co.edu.javeriana.mc.survey.model.scalars;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
// import javax.persistence.Table;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("11")
public class DecimalQuestion extends SurveyItem {

    public DecimalQuestion() {
    }

    public DecimalQuestion(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue,SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue,parent, survey, true);
    }


}