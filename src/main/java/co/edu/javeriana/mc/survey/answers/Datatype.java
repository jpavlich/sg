package co.edu.javeriana.mc.survey.answers;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.likert.LikertQuestion;
import co.edu.javeriana.mc.survey.model.multi.SingleSelectionMultipleChoice;
import co.edu.javeriana.mc.survey.model.multi.choices.IntegerChoice;
import co.edu.javeriana.mc.survey.model.scalars.BooleanQuestion;
import co.edu.javeriana.mc.survey.model.scalars.DateTimeQuestion;
import co.edu.javeriana.mc.survey.model.scalars.DecimalQuestion;
import co.edu.javeriana.mc.survey.model.scalars.IntegerQuestion;
import co.edu.javeriana.mc.survey.model.scalars.MultiLineQuestion;
import co.edu.javeriana.mc.survey.model.scalars.SingleLineQuestion;

@Component
public class Datatype {

    private Map<Class<? extends SurveyItem>, String> surveyItemDatatype = new HashMap<>();
    private Map<String, String> fieldDatatype = new HashMap<>();

    @PostConstruct
    public void init() {
        surveyItemDatatype.put(SingleLineQuestion.class, "VARCHAR(255)");
        surveyItemDatatype.put(MultiLineQuestion.class, "TEXT");
        surveyItemDatatype.put(IntegerQuestion.class, "BIGINT(20)");
        surveyItemDatatype.put(DecimalQuestion.class, "DECIMAL(20,10)");
        surveyItemDatatype.put(SingleSelectionMultipleChoice.class, "BIGINT(20)");
        surveyItemDatatype.put(DateTimeQuestion.class, "DATETIME(6)");
        surveyItemDatatype.put(LikertQuestion.class, "INTEGER(5)");
        surveyItemDatatype.put(BooleanQuestion.class, "TINYINT(1)");
        surveyItemDatatype.put(IntegerChoice.class, "BIGINT(20)");

        
        fieldDatatype.put("ANSWER_DATETIME", "DATETIME(6)");
        fieldDatatype.put("USER_ID", "BIGINT(20)");
        fieldDatatype.put("ID", "BIGINT(20) PRIMARY KEY AUTO_INCREMENT");
    }

    public String of(SurveyItem surveyItem) {
        return surveyItemDatatype.get(surveyItem.getClass());
    }

    public String ofField(String fieldName) {
        return fieldDatatype.get(fieldName);
    }
}
