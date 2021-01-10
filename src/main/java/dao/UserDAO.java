package dao;

import database.ConnectorDB;
import entity.User;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements DAO<User> {

    private final static Logger log = Logger.getLogger(UserDAO.class);

    private final static String SQL_GET_ALL_USERS = "SELECT * FROM user";
    private final static String SQL_GET_USER_BY_ID = "SELECT * FROM user WHERE id = ?";
    private final static String SQL_GET_USER_BY_USERNAME = "SELECT * FROM user WHERE username LIKE ?";
    private final static String SQL_INSERT_USER = "INSERT INTO user (username, password) VALUES (?, ?)";
    private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM user WHERE id = ?";
    private final static String SQL_GET_ALL_USERS_AND_ROLES =
            "SELECT u.id, u.username, u.password, r.name "
            + "FROM user u, role r, roles_users ru "
            + "WHERE ru.role_id = r.id AND ru.user_id = u.id";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public UserDAO() {
        connector = ConnectorDB.getInstance();
    }

    @Override
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

    @Override
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
                while (resultSet.next()) {
                    user = new User(
                            resultSet.getLong("id"),
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

    @Override
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

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
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
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
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

    @Override
    public boolean delete(User user) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_USER_BY_ID);
            preparedStatement.setLong(1, user.getId());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("User was deleted");
                return true;
            } else {
                log.warn("User wasn't deleted");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }
    public List<Object[]> findAllUsersAndRoles() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Object[]> rows = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_USERS_AND_ROLES);
            if (resultSet != null) {
                while (resultSet.next()) {
                        rows.add(new Object[]{
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        });
                        log.debug("Row was returned");
                    }
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return rows;
    }
}
