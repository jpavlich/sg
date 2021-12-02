package co.edu.javeriana.mc.survey.test;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import co.edu.javeriana.mc.survey.Config;
import co.edu.javeriana.mc.survey.SurveyProcessing;
import co.edu.javeriana.mc.survey.answers.AnswerGroup;
import co.edu.javeriana.mc.survey.answers.AnswerRepository;
import co.edu.javeriana.mc.survey.answers.MetadataUtil;
import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.repository.SurveyRepository;
import co.edu.javeriana.mc.survey.repository.multi.MultipleSelectionMultipleChoiceRepository;
import co.edu.javeriana.mc.survey.repository.multi.SingleSelectionMultipleChoiceRepository;
import co.edu.javeriana.mc.survey.repository.multi.choices.IntegerChoiceRepository;
import co.edu.javeriana.mc.survey.repository.scalars.DateTimeQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.DecimalQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.IntegerQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.MultiLineQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.SingleLineQuestionRepository;

@Configuration
@Order(12)
public class QueryAnswers implements CommandLineRunner {
        Logger log = LoggerFactory.getLogger(getClass());
        @Autowired
        JdbcTemplate jdbc;

        @Autowired
        IntegerChoiceRepository choiceRepository;

        @Autowired
        DateTimeQuestionRepository dateTimeQuestionRepository;

        @Autowired
        MultiLineQuestionRepository multiLineQuestionRepository;

        @Autowired
        MultipleSelectionMultipleChoiceRepository multipleSelectionMultipleChoiceRepository;

        @Autowired
        SingleLineQuestionRepository singleLineQuestionRepository;

        @Autowired
        IntegerQuestionRepository integerQuestionRepository;

        @Autowired
        DecimalQuestionRepository decimalQuestionRepository;

        @Autowired
        SingleSelectionMultipleChoiceRepository singleSelectionMultipleChoiceRepository;

        @Autowired
        SurveyRepository surveyRepository;

        @Autowired
        Config surveyConf;

        @Autowired
        MetadataUtil metadataUtil;

        @Autowired
        AnswerRepository surveyLifecycle;

        @Autowired
        SurveyProcessing surveyProcessing;

        @Override
        @Transactional
        public void run(String... args) throws Exception {
                Survey survey = surveyRepository.findById(1l).orElseThrow();
                AnswerGroup answerGroup = surveyLifecycle.findAnswersById(survey, 1l);
                log.info("*** {}", answerGroup);

                Boolean result = surveyProcessing.evalExpression("QUESTION_4 > 4 and QUESTION_5==7", answerGroup);
                log.info("*** RESULT: {}", result);
                // Object result2 = surveyProcessing.evalExpression("max(QUESTION_4,
                // QUESTION_5==7)", resp);
                // log.info("*** RESULT: {}", result2);

                result = surveyProcessing.evalExpression("QUESTION_6.MCH_0", answerGroup);
                log.info("*** RESULT: {}", result);
                result = surveyProcessing.evalExpression("QUESTION_6.MCH_1", answerGroup);
                log.info("*** RESULT: {}", result);
                result = surveyProcessing.evalExpression("QUESTION_6.MCH_2", answerGroup);
                log.info("*** RESULT: {}", result);
                result = surveyProcessing.evalExpression("QUESTION_6.MCH_3", answerGroup);
                log.info("*** RESULT: {}", result);
                result = surveyProcessing.evalExpression("QUESTION_6.MCH_4", answerGroup);
                log.info("*** RESULT: {}", result);


                float sum = surveyProcessing.evalExpression("sum(LG1)", answerGroup);
                log.info("**SUM: {}", sum);
                float avg = surveyProcessing.evalExpression("avg(LG1)", answerGroup);
                log.info("**avg: {}", avg);
                float max = surveyProcessing.evalExpression("max(LG1)", answerGroup);
                log.info("**MAX: {}", max);
                float min = surveyProcessing.evalExpression("min(LG1)", answerGroup);
                log.info("**min: {}", min);

                sum = surveyProcessing.evalExpression("sum(QG1)", answerGroup);
                log.info("**SUM: {}", sum);
                avg = surveyProcessing.evalExpression("avg(QG1)", answerGroup);
                log.info("**avg: {}", avg);
                max = surveyProcessing.evalExpression("max(QG1)", answerGroup);
                log.info("**MAX: {}", max);
                min = surveyProcessing.evalExpression("min(QG1)", answerGroup);
                log.info("**min: {}", min);

        }
}
