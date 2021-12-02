package co.edu.javeriana.mc.survey.test;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import co.edu.javeriana.mc.survey.Config;
import co.edu.javeriana.mc.survey.answers.AnswerGroup;
import co.edu.javeriana.mc.survey.answers.AnswerRepository;
import co.edu.javeriana.mc.survey.answers.MetadataUtil;
import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.multi.SingleSelectionMultipleChoice;
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
@Order(11)
public class CreateAnswers implements CommandLineRunner {
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        @Transactional
        public void run(String... args) throws Exception {
                Survey survey = surveyRepository.findById(1l).orElseThrow();

                surveyLifecycle.createTable(survey);

                Map<Long, Object> answersByQuestionId = new LinkedHashMap<>();
                Iterator<SurveyItem> it = survey.getQuestions().iterator();

                SurveyItem q1 = it.next();
                answersByQuestionId.put(q1.getId(), "answer1");
                SurveyItem q2 = it.next();
                answersByQuestionId.put(q2.getId(), "answer2");
                SurveyItem q3 = it.next();
                answersByQuestionId.put(q3.getId(), 3);
                SurveyItem q4 = it.next();
                answersByQuestionId.put(q4.getId(), 4.4);

                SingleSelectionMultipleChoice q5 = (SingleSelectionMultipleChoice) it.next();
                answersByQuestionId.put(q5.getId(), q5.getChoices().get(0).getId());

                for (int i = 0; i < 5; i++) {
                        answersByQuestionId.put(it.next().getId(), Math.random() < 0.5);
                }
                SurveyItem q7 = it.next();
                answersByQuestionId.put(q7.getId(), "2021-10-09 00:00:01");

                for (int i = 0; i < 5; i++) {
                        answersByQuestionId.put(it.next().getId(), (int) (Math.random() * 5) + 1);
                }

                for (int i = 0; i < 5; i++) {
                        answersByQuestionId.put(it.next().getId(), Math.random() * 100f);
                }

                AnswerGroup answerGroup = surveyLifecycle.insertAnswers(survey, 1l, answersByQuestionId);
                surveyLifecycle.insertAnswers(survey, 1l, answersByQuestionId);
                surveyLifecycle.insertAnswers(survey, 1l, answersByQuestionId);
                surveyLifecycle.insertAnswers(survey, 1l, answersByQuestionId);
                log.info("*** ANSWERS: {}", answerGroup);
        }
}
