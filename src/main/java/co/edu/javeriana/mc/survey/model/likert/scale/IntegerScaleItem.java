package co.edu.javeriana.mc.survey.model.likert.scale;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import co.edu.javeriana.mc.survey.model.likert.LikertGroup;

/**
 * @author jpavlich
 */
@Entity
@DiscriminatorValue("60")
public class IntegerScaleItem extends ScaleItem {

    public IntegerScaleItem() {
    }

    public IntegerScaleItem(String title, @Size(max = 30) String shortTitle, String description,
            Integer surveyItemOrder, String defaultValue, LikertGroup likertGroup) {
        super(title, shortTitle, description, surveyItemOrder, defaultValue, likertGroup);
    }





}