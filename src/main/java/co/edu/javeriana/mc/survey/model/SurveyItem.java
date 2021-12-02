package co.edu.javeriana.mc.survey.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author jpavlich
 */
@Entity
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "survey_item")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.INTEGER, length = 4)
public abstract class SurveyItem {

    // TODO (surveyItemOrder, parentId) must be unique
    // TODO (shortTitle, parentId) must be unique

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    private String title;

    @Basic
    @Size(max = 30)
    // @Column(unique = true)
    private String shortTitle;

    @Basic
    private String description;

    @Basic
    private Integer surveyItemOrder;

    @Basic
    private String defaultValue;

    @Basic
    private Boolean atomic;

    @OneToMany(mappedBy = "parent")
    private List<SurveyItem> children;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    @JsonIgnore
    private SurveyItem parent;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    @JsonIgnore
    private Survey survey;

    @Column(name = "type", insertable = false, updatable = false)
    private Integer type;

    public SurveyItem() {
    }

    public SurveyItem(String title, String shortTitle, String description, Integer surveyItemOrder, String defaultValue,
            SurveyItem parent, Survey survey, Boolean atomic) {
        this.title = title;
        this.shortTitle = shortTitle;
        this.description = description;
        this.surveyItemOrder = surveyItemOrder;
        this.defaultValue = defaultValue;
        this.parent = parent;
        this.survey = survey;
        this.atomic = atomic;
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

    public String getShortName() {
        return shortTitle;
    }

    public void setShortName(String shortName) {
        this.shortTitle = shortName;
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

    public void setSurveyItemOrder(Integer itemOrder) {
        this.surveyItemOrder = itemOrder;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public SurveyItem getParent() {
        return parent;
    }

    public void setChildren(List<SurveyItem> children) {
        this.children = children;
    }

    public void setParent(SurveyItem parent) {
        this.parent = parent;
    }

    public List<SurveyItem> getChildren() {
        return children;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Boolean isAtomic() {
        return atomic;
    }

    public void setAtomic(Boolean atomic) {
        this.atomic = atomic;
    }

    public Integer getType() {
        return type;
    }
}