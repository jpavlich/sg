package co.edu.javeriana.mc.survey.answers;

import org.springframework.stereotype.Component;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;

@Component
public class Naming {

    public String identifierOf(String text) {
        return text.replaceAll("[^a-zA-Z0-9]+", "_").toUpperCase();
    }



    String pathOf(SurveyItem surveyItem) {
        String name = identifierOf(surveyItem.getShortName());

        SurveyItem current = surveyItem.getParent();
        while (current != null && !(current instanceof Survey)) {
            name = identifierOf(current.getShortName()) + "." + name;
            current = current.getParent();
        }
        return name;
    }


}
