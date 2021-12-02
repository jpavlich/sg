package co.edu.javeriana.mc.survey.test;

import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import co.edu.javeriana.mc.survey.Config;
import co.edu.javeriana.mc.survey.SurveyItemCreator;
import co.edu.javeriana.mc.survey.answers.AnswerRepository;
import co.edu.javeriana.mc.survey.answers.MetadataUtil;
import co.edu.javeriana.mc.survey.model.QuestionGroup;
import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.likert.LikertGroup;
import co.edu.javeriana.mc.survey.model.likert.LikertQuestion;
import co.edu.javeriana.mc.survey.model.likert.scale.IntegerScaleItem;
import co.edu.javeriana.mc.survey.model.multi.MultipleSelectionMultipleChoice;
import co.edu.javeriana.mc.survey.model.multi.SingleSelectionMultipleChoice;
import co.edu.javeriana.mc.survey.model.multi.choices.IntegerChoice;
import co.edu.javeriana.mc.survey.model.scalars.BooleanQuestion;
import co.edu.javeriana.mc.survey.model.scalars.DateTimeQuestion;
import co.edu.javeriana.mc.survey.model.scalars.DecimalQuestion;
import co.edu.javeriana.mc.survey.model.scalars.IntegerQuestion;
import co.edu.javeriana.mc.survey.model.scalars.MultiLineQuestion;
import co.edu.javeriana.mc.survey.model.scalars.SingleLineQuestion;
import co.edu.javeriana.mc.survey.repository.QuestionGroupRepository;
import co.edu.javeriana.mc.survey.repository.SurveyRepository;
import co.edu.javeriana.mc.survey.repository.likert.LikertGroupRepository;
import co.edu.javeriana.mc.survey.repository.likert.LikertQuestionRepository;
import co.edu.javeriana.mc.survey.repository.likert.scale.IntegerScaleItemRepository;
import co.edu.javeriana.mc.survey.repository.multi.MultipleSelectionMultipleChoiceRepository;
import co.edu.javeriana.mc.survey.repository.multi.SingleSelectionMultipleChoiceRepository;
import co.edu.javeriana.mc.survey.repository.multi.choices.IntegerChoiceRepository;
import co.edu.javeriana.mc.survey.repository.scalars.BooleanQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.DateTimeQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.DecimalQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.IntegerQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.MultiLineQuestionRepository;
import co.edu.javeriana.mc.survey.repository.scalars.SingleLineQuestionRepository;

@Configuration
@Order(10)
public class CreateSurvey implements CommandLineRunner {
        Logger log = LoggerFactory.getLogger(getClass());
        @Autowired
        JdbcTemplate jdbc;

        @Autowired
        IntegerChoiceRepository integerChoiceRepository;

        @Autowired
        BooleanQuestionRepository booleanQuestionRepository;

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
        LikertGroupRepository likertGroupRepository;

        @Autowired
        LikertQuestionRepository likertQuestionRepository;

        @Autowired
        IntegerScaleItemRepository integerScaleItemRepository;
        @Autowired
        QuestionGroupRepository questionGroupRepository;

        @Autowired
        SurveyRepository surveyRepository;

        @Autowired
        Config surveyConf;

        @Autowired
        MetadataUtil metadataUtil;

        @Autowired
        AnswerRepository surveyLifecycle;

        @Autowired
        SurveyItemCreator sc;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        @Override
        @Transactional
        public void run(String... args) throws Exception {
                int order = 0;
                Survey survey = surveyRepository.save(new Survey("survey 1", "survey 1", "desc 1"));

                SingleLineQuestion singleLineQuestion = new SingleLineQuestion("A single line question", "question 1",
                                "desc 1", order++, "", survey, survey);
                singleLineQuestionRepository.save(singleLineQuestion);

                MultiLineQuestion multiLineQuestion = new MultiLineQuestion("A multi line question", "question 2",
                                "desc 2", order++, "", survey, survey);
                multiLineQuestionRepository.save(multiLineQuestion);

                IntegerQuestion integerQuestion = new IntegerQuestion("An integer question", "question 3", "desc 3", order++,
                                "3", survey, survey);
                integerQuestionRepository.save(integerQuestion);

                DecimalQuestion decimalQuestion = new DecimalQuestion("A decimal question", "question 4", "desc 4", order++,
                                "4.4", survey, survey);
                decimalQuestionRepository.save(decimalQuestion);

                SingleSelectionMultipleChoice singleSelectionMultipleChoice = new SingleSelectionMultipleChoice(
                                "A single selection multiple choice question with 5 choices", "question 5", "desc 5",order++,
                                "", survey, survey);
                singleSelectionMultipleChoiceRepository.save(singleSelectionMultipleChoice);
                for (int i = 0; i < 5; i++) {
                        integerChoiceRepository.save(new IntegerChoice("single choice" + i, "sch " + i, "", order++,
                                        null, singleSelectionMultipleChoice));
                }

                MultipleSelectionMultipleChoice multipleSelectionMultipleChoice = new MultipleSelectionMultipleChoice(
                                "A multiple selection multiple choice question with 5 choices", "question 6", "desc 6",
                                6, "", survey, survey);
                for (int i = 0; i < 5; i++) {
                        booleanQuestionRepository.save(new BooleanQuestion("multi choice" + i, "mch " + i, "", order++, "0",
                                        multipleSelectionMultipleChoice));
                }
                multipleSelectionMultipleChoiceRepository.save(multipleSelectionMultipleChoice);

                DateTimeQuestion dateQuestion = new DateTimeQuestion("A date question", "d 7", "", order++, "", survey,
                                survey);
                dateTimeQuestionRepository.save(dateQuestion);

                LikertGroup likertGroup = likertGroupRepository
                                .save(new LikertGroup("Likert group 1", "lg1", "", 8, "", survey, survey));
                for (int i = 0; i < 5; i++) {
                        likertQuestionRepository.save(new LikertQuestion("likert question " + i, "lq" + i, "", order++,
                                        "", likertGroup));
                }

                for (int i = 0; i < 5; i++) {
                        integerScaleItemRepository.save(new IntegerScaleItem("int scale " + i, "is" + i, "", order++,
                                        "", likertGroup));
                }

                QuestionGroup questionGroup = questionGroupRepository.save(new QuestionGroup("question group 1", "qg1", "", order++, null, survey, survey));
                for (int i = 0; i < 5; i++) {
                        decimalQuestionRepository.save(new DecimalQuestion("dec question " + i, "dq"+i, "", order++, null, questionGroup, survey));
                }

        }
}
