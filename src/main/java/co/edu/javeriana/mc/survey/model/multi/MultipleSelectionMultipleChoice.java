package co.edu.javeriana.mc.survey.model.multi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.scalars.BooleanQuestion;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("30")
public class MultipleSelectionMultipleChoice extends SurveyItem {

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<BooleanQuestion> choices= new ArrayList<>();

    public List<BooleanQuestion> getChoices() {
        return choices;
    }


    public MultipleSelectionMultipleChoice() {
    }

    public MultipleSelectionMultipleChoice(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue,SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue,parent, survey, false);
    }

    

}