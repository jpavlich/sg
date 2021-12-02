package co.edu.javeriana.mc.survey.model.likert;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.likert.scale.ScaleItem;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("50")
public class LikertGroup extends SurveyItem {

    @OneToMany(mappedBy = "likertGroup")
    private final List<ScaleItem> scale = new ArrayList<>();

    public LikertGroup() {
    }

    public LikertGroup(String title, String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, SurveyItem parent, Survey survey) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, parent, survey, false);
    }

    public List<ScaleItem> getScale() {
        return scale;
    }

}