package dao;

import database.ConnectorDB;
import entity.Test;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestDAO implements DAO<Test> {

    private final static Logger log = Logger.getLogger(SubjectDAO.class);

    private final static String SQL_GET_ALL_TESTS = "SELECT * FROM test";
    private final static String SQL_GET_TESTS_BY_ID = "SELECT * FROM test WHERE id = ?";
    private final static String SQL_GET_TESTS_BY_TEST_NAME = "SELECT * FROM test WHERE name LIKE ?";
    private final static String SQL_INSERT_TEST = "INSERT INTO test (name, description, time) VALUES (?, ?, ?)";
    private final static String SQL_DELETE_TEST_BY_ID = "DELETE FROM test WHERE id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public TestDAO() {
        connector = ConnectorDB.getInstance();
    }

    @Override
    public List<Test> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Test> tests = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_TESTS);
            while (resultSet.next()) {
                Test test = new Test(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getTime("time"));
                tests.add(test);
            }
            log.debug("Tests were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return tests;
    }

    @Override
    public Test findById(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Test test = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_TESTS_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    test = new Test(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTime("time")
                    );
                }
                log.debug("Test was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return test;
    }

    @Override
    public Test findByName(String name) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Test test = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_TESTS_BY_TEST_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    test = new Test(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description"),
                            resultSet.getTime("time")
                    );
                }
                log.debug("Test was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return test;
    }

    @Override
    public boolean update(Test test) {
        return false;
    }

    @Override
    public boolean save(Test test) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_TEST);
            preparedStatement.setString(1, test.getName());
            preparedStatement.setString(2, test.getDescription());
            preparedStatement.setTime(3, test.getTime());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Test was saved");
                return true;
            } else {
                log.warn("Test wasn't saved");
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
    public boolean delete(Test test) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_TEST_BY_ID);
            preparedStatement.setLong(1, test.getId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Test was deleted");
                return true;
            } else {
                log.warn("Test wasn't deleted");
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
