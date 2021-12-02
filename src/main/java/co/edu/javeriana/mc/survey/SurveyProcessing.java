package co.edu.javeriana.mc.survey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import co.edu.javeriana.mc.survey.answers.AnswerGroup;

@Component
@SuppressWarnings("unchecked")
public class SurveyProcessing {
    
    Logger log = LoggerFactory.getLogger(getClass());





    public <T> T evalExpression(String expression, AnswerGroup resp) {
        StandardEvaluationContext context = new StandardEvaluationContext(resp);
 
        context.addPropertyAccessor(new MapAccessor());

        ExpressionParser expressionParser = new SpelExpressionParser();
        return (T) expressionParser.parseExpression(expression).getValue(context);
    }
}
