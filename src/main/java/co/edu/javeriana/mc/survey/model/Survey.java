package co.edu.javeriana.mc.survey.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.Where;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("0")
public class Survey extends SurveyItem {

    @OneToMany(mappedBy = "survey")
    @OrderBy("surveyItemOrder ASC")
    @Where(clause = "atomic = true")
    @JsonIgnore
    private List<SurveyItem> questions;

    public Survey() {
    }

    public Survey(String title, String shortTitle, String description) {
        super(title, shortTitle, description, 0, null, null, null, false);
    }

    public List<SurveyItem> getQuestions() {
        return questions;
    }

}