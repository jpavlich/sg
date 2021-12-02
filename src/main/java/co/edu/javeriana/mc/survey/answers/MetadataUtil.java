package co.edu.javeriana.mc.survey.answers;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import co.edu.javeriana.mc.survey.model.Survey;
import co.edu.javeriana.mc.survey.model.SurveyItem;

@Component
public class MetadataUtil {
    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    JdbcTemplate jdbc;

    @Autowired
    Datatype datatype;

    @Autowired
    public Naming naming;

    /**
     * Extracts metadata from an existing table in the DB
     * 
     * @param table
     * @return A {@link LinkedHashMap}, where each entry is of the form:
     *         <p>
     *         column name -> column datatype
     *         </p>
     * @throws SQLException
     */
    public Map<String, String> columnsMetadata(String table) throws SQLException {
        DatabaseMetaData meta = jdbc.getDataSource().getConnection().getMetaData();
        ResultSet columns = meta.getColumns(null, null, "CUSTOMER_ADDRESS", null);
        Map<String, String> cols = new LinkedHashMap<>();

        while (columns.next()) {
            cols.put(columns.getString("COLUMN_NAME"), columns.getString("DATA_TYPE"));
        }
        return cols;

    }

    /**
     * Extracts metadata from the questions of a {@link Survey}
     * 
     * @param survey
     * @return A {@link LinkedHashMap}, where each entry is of the form:
     *         <p>
     *         column name -> column datatype
     *         </p>
     * 
     */
    public Map<String, String> columnsMetadata(Survey survey) {
        Map<String, String> metadata = new LinkedHashMap<>();
        for (SurveyItem surveyItem : survey.getQuestions()) {
            metadata.put(naming.pathOf(surveyItem), datatype.of(surveyItem));
        }
        return metadata;
    }


}