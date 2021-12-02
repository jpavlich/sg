package co.edu.javeriana.mc.survey.answers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;
import co.edu.javeriana.mc.survey.model.multi.MultipleSelectionMultipleChoice;

@Repository
public class AnswerRepository {

    private static final String ID = "ID";
    private static final String ANSWER_DATETIME_COL_NAME = "ANSWER_DATETIME";
    private static final String USER_ID_COL_NAME = "USER_ID";

    public static final String SCHEMA = "survey";

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    MetadataUtil metadataUtil;

    @Autowired
    Datatype datatype;

    @Autowired
    DateTimeFormatter formatter;

    @Autowired
    Naming naming;

    public void createTable(Survey survey) {

        if (survey.getQuestions().size() > 0) {
            String tableName = naming.identifierOf(survey.getTitle());

            Map<String, String> surveyCols = metadataUtil.columnsMetadata(survey);
            surveyCols.put(ID, datatype.ofField(ID));
            surveyCols.put(ANSWER_DATETIME_COL_NAME, datatype.ofField(ANSWER_DATETIME_COL_NAME));
            surveyCols.put(USER_ID_COL_NAME, datatype.ofField(USER_ID_COL_NAME));

            jdbc.update(String.format("drop table if exists %s.%s", SCHEMA, tableName));

            String query = String.format("create table if not exists %s.%s (", SCHEMA, tableName);

            query += String.join(",", surveyCols.entrySet().stream()
                    .map(e -> String.format("`%s` %s", e.getKey(), e.getValue())).collect(Collectors.toList()));

            query += ")";

            jdbc.update(query);

        }
    }

    /**
     * 
     * @param survey
     * @param answersByQuestionId Map where keys are the {@link Question#getId()} of
     *                            each question, and values are the answers
     * @return
     * @throws TablesNotConsistentException
     */
    public AnswerGroup insertAnswers(Survey survey, Long userId, Map<Long, Object> answersByQuestionId) {
        String tableName = naming.identifierOf(survey.getTitle());

        AnswerGroup answerGroup = createAnswerGroup(survey, answersByQuestionId);

        // Answer columns
        List<String> columns = answerGroup.getAllAnswers().keySet().stream().map(c -> "`" + c + "`")
                .collect(Collectors.toList());
        // Extra columns
        columns.add("`" + ANSWER_DATETIME_COL_NAME + "`");
        columns.add("`" + USER_ID_COL_NAME + "`");

        List<Object> tuple = new ArrayList<>();

        // Answer columns data
        tuple.addAll(answerGroup.getAllAnswers().values());
        // Extra columns data
        tuple.add(LocalDateTime.now().format(formatter)); // FIXME Should we use local time or GMT?
        tuple.add(String.valueOf(userId));

        String query = String.format("insert into %s.%s (%s) values (%s)", SCHEMA, tableName, String.join(",", columns),
                // Generates a sequence of "?, ?, ?, ..."
                String.join(", ", Collections.nCopies(columns.size(), "?")));
        log.info("***** TUPLE {}", tuple);
        jdbc.update(query, tuple.toArray());
        return answerGroup;
    }

    /**
     * Queries one tuple from a survey table and returns it as a map.
     * {@link MultipleSelectionMultipleChoice} questions are also converted to maps
     * (@see #answersAsMaps(Map))
     * 
     * @param query A query that must return only one tuple
     * @return
     */
    public AnswerGroup findAnswersById(Survey survey, Long id) {
        String tableName = naming.identifierOf(survey.getTitle());

        String query = String.format("select * from %s.%s where id = ? limit 1", SCHEMA, tableName);
        Map<String, Object> result = jdbc.queryForMap(query, id);
        AnswerGroup answerGroup = new AnswerGroup();
        answerGroup.addAnswersByColumnName(result);
        return answerGroup;
    }

    /**
     * 
     * @param survey
     * @param answersByQuestionId Map where keys are the {@link Question#getId()} of
     *                            each question, and values are the answers
     * @return
     */
    public AnswerGroup createAnswerGroup(Survey survey, Map<Long, Object> answersByQuestionId) {
        Map<Long, SurveyItem> questionIndex = survey.getQuestions().stream()
                .collect(Collectors.toMap(q -> q.getId(), q -> q));
        AnswerGroup root = new AnswerGroup();
        for (Entry<Long, Object> entry : answersByQuestionId.entrySet()) {
            Long questionId = entry.getKey();
            Object answer = entry.getValue();

            SurveyItem surveyItem = questionIndex.get(questionId);
            if (surveyItem == null) {
                log.error("Question with id {} does not exist", questionId);
            } else {
                String path = naming.pathOf(surveyItem);
                root.addAnswer(path, answer);
            }

        }
        return root;
    }

}
