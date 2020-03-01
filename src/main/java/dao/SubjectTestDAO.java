package dao;

import database.ConnectorDB;
import entity.SubjectTest;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectTestDAO implements WiringDAO<SubjectTest>{

    private final static Logger log = Logger.getLogger(SubjectTestDAO.class);

    private final static String SQL_GET_ALL_ASSIGNMENTS = "SELECT * FROM subjects_tests";
    private final static String SQL_GET_BY_SUBJECT_ID_AND_TEST_ID =
            "SELECT * FROM subjects_tests " +
                    "WHERE subject_id = ? AND test_id = ?";
    private final static String SQL_INSERT_ASSIGNMENT = "INSERT INTO subjects_tests (subject_id, test_id) VALUES (?, ?)";
    private final static String SQL_DELETE_ASSIGNMENT_BY_SUBJECT_ID_AND_TEST_ID =
            "DELETE FROM subjects_tests " +
                    "WHERE subject_id = ? " +
                    "AND test_id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public SubjectTestDAO() {
        connector = ConnectorDB.getInstance();
    }

    @Override
    public List<SubjectTest> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<SubjectTest> assignments = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_ASSIGNMENTS);
            while (resultSet.next()) {
                SubjectTest subjectTest = new SubjectTest(
                        resultSet.getLong("subject_id"),
                        resultSet.getLong("test_id")
                );
                assignments.add(subjectTest);
            }
            log.debug("Assignments were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return assignments;
    }

    @Override
    public SubjectTest findByIds(Long subjectId, Long testId) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        SubjectTest subjectTest = new SubjectTest();
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_SUBJECT_ID_AND_TEST_ID);
            preparedStatement.setLong(1, subjectId);
            preparedStatement.setLong(2, testId);
            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {
                subjectTest.setSubjectId(resultSet.getLong("subject_id"));
                subjectTest.setTestId(resultSet.getLong("test_id"));
            }
            log.debug("Assignment was returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return subjectTest;
    }

    @Override
    public boolean update(SubjectTest subjectTest) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean save(SubjectTest subjectTest) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_ASSIGNMENT);
            preparedStatement.setLong(1, subjectTest.getSubjectId());
            preparedStatement.setLong(2, subjectTest.getTestId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Assignment was saved");
                return true;
            } else {
                log.warn("Assignment wasn't saved");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }

    @Override
    public boolean delete(SubjectTest subjectTest) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_ASSIGNMENT_BY_SUBJECT_ID_AND_TEST_ID);
            preparedStatement.setLong(1, subjectTest.getSubjectId());
            preparedStatement.setLong(2, subjectTest.getTestId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Assignment was deleted");
                return true;
            } else {
                log.warn("Assignment wasn't deleted");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }
}
