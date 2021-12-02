package co.edu.javeriana.mc.survey.model.likert.scale;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.edu.javeriana.mc.survey.model.likert.LikertGroup;

/**
 * @author jpavlich
 */
@Entity
public abstract class ScaleItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String title;

    @Basic
    @Size(max = 30)
    private String shortTitle;

    @Basic
    private String description;

    @Basic
    private Integer surveyItemOrder;

    @Basic
    private String defaultValue;

    @ManyToOne
    @JsonIgnore
    private LikertGroup likertGroup;

    public ScaleItem() {
    }

    public ScaleItem(String title, @Size(max = 30) String shortTitle, String description, Integer surveyItemOrder,
            String defaultValue, LikertGroup likertGroup) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.description = description;
        this.surveyItemOrder = surveyItemOrder;
        this.defaultValue = defaultValue;
        this.likertGroup = likertGroup;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSurveyItemOrder() {
        return surveyItemOrder;
    }

    public void setSurveyItemOrder(Integer surveyItemOrder) {
        this.surveyItemOrder = surveyItemOrder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public LikertGroup getLikertGroup() {
        return likertGroup;
    }

    public void setLikertGroup(LikertGroup likertGroup) {
        this.likertGroup = likertGroup;
    }

    
    

}