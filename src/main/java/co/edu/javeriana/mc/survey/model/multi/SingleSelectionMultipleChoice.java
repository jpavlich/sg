package co.edu.javeriana.mc.survey.model.multi;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.multi.choices.IntegerChoice;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("31")
public class SingleSelectionMultipleChoice extends SurveyItem {

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<IntegerChoice> choices= new ArrayList<>();

    public List<IntegerChoice> getChoices() {
        return choices;
    }
    

    public SingleSelectionMultipleChoice() {
    }

    public SingleSelectionMultipleChoice(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue,SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue,parent, survey, true);
    }

    

}