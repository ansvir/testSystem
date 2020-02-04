package dao;

import database.ConnectorDB;
import entity.Role;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements Dao<User>{

    private final static Logger log = Logger.getLogger(UserDAO.class);

    private final static String SQL_GET_ALL_USERS = "SELECT * FROM users";
    private final static String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
    private final static String SQL_GET_USER_BY_USERNAME = "SELECT * FROM users WHERE username LIKE ?";
    private final static String SQL_INSERT_USER = "INSERT INTO users (role_id, username, password) VALUES (?, ?, ?)";
    private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UserDAO() {
        connector = ConnectorDB.getInstance();
    }

    public List<User> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<User> users = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_USERS);
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getLong("role_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                users.add(user);
            }
            log.debug("Users were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return users;
    }

    public User findById(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        User user = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getLong("role_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")
                );
                log.debug("User was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return user;
    }

    public User findByName(String username) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        User user = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getLong("id"),
                            resultSet.getLong("role_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password")
                    );
                }
                log.debug("User was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return user;
    }

    public boolean update(User user) {
        return false;
    }

    public boolean save(User user) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_USER);
            preparedStatement.setLong(1, user.getRoleId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("User was saved");
                return true;
            } else {
                log.warn("User wasn't saved");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }

    public boolean delete(User user) {
        return false;
    }

}
