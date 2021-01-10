package dao;

import database.ConnectorDB;
import entity.Subject;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO implements DAO<Subject> {

    private final static Logger log = Logger.getLogger(SubjectDAO.class);

    private final static String SQL_GET_ALL_SUBJECTS = "SELECT * FROM subject";
    private final static String SQL_GET_SUBJECT_BY_ID = "SELECT * FROM subject WHERE id = ?";
    private final static String SQL_GET_SUBJECT_BY_SUBJECT_NAME = "SELECT * FROM subject WHERE name LIKE ?";
    private final static String SQL_INSERT_SUBJECT = "INSERT INTO subject (name, description) VALUES (?, ?)";
    private final static String SQL_DELETE_SUBJECT_BY_ID = "DELETE FROM subject WHERE id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public SubjectDAO() {
        connector = ConnectorDB.getInstance();
    }

    @Override
    public List<Subject> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Subject> subjects = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_SUBJECTS);
            while (resultSet.next()) {
                Subject subject = new Subject(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                subjects.add(subject);
            }
            log.debug("Subjects were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return subjects;
    }

    @Override
    public Subject findById(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Subject subject = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_SUBJECT_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    subject = new Subject(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );
                }
                log.debug("Subject was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return subject;
    }

    @Override
    public Subject findByName(String name) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Subject subject = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_SUBJECT_BY_SUBJECT_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    subject = new Subject(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("description")
                    );
                }
                log.debug("Subject was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return subject;
    }

    @Override
    public boolean update(Subject subject) {
        return false;
    }

    @Override
    public boolean save(Subject subject) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_SUBJECT);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setString(2, subject.getDescription());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Subject was saved");
                return true;
            } else {
                log.warn("Subject wasn't saved");
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
    public boolean delete(Subject subject) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_SUBJECT_BY_ID);
            preparedStatement.setLong(1, subject.getId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Subject was deleted");
                return true;
            } else {
                log.warn("Subject wasn't deleted");
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
