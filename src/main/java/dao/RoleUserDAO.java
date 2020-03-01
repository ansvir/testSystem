package dao;

import database.ConnectorDB;
import entity.RoleUser;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleUserDAO implements WiringDAO<RoleUser>{

    private final static Logger log = Logger.getLogger(RoleUserDAO.class);

    private final static String SQL_GET_ALL_ASSIGNMENTS = "SELECT * FROM roles_users";
    private final static String SQL_GET_BY_ROLE_ID_AND_USER_ID =
            "SELECT * FROM roles_users " +
            "WHERE role_id = ? AND user_id = ?";
    private final static String SQL_GET_BY_USER_ID = "SELECT * FROM roles_users WHERE user_id = ?";
    private final static String SQL_INSERT_ASSIGNMENT = "INSERT INTO roles_users (role_id, user_id) VALUES (?, ?)";
    private final static String SQL_DELETE_ASSIGNMENT_BY_ROLE_ID_AND_USER_ID =
            "DELETE FROM roles_users " +
            "WHERE role_id = ? " +
            "AND user_id = ?";
    private final static String SQL_DELETE_ASSIGNMENTS_BY_USER_ID = "DELETE FROM roles_users WHERE user_id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public RoleUserDAO() {
        connector = ConnectorDB.getInstance();
    }

    @Override
    public List<RoleUser> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<RoleUser> assignments = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_ASSIGNMENTS);
            while (resultSet.next()) {
                RoleUser roleUser = new RoleUser(
                        resultSet.getLong("role_id"),
                        resultSet.getLong("user_id")
                );
                assignments.add(roleUser);
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
    public RoleUser findByIds(Long roleId, Long userId) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        RoleUser roleUser = new RoleUser();
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_ROLE_ID_AND_USER_ID);
            preparedStatement.setLong(1, roleId);
            preparedStatement.setLong(2, userId);
            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {
                roleUser.setRoleId(resultSet.getLong("role_id"));
                roleUser.setUserId(resultSet.getLong("user_id"));
            }
            log.debug("Assignment was returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return roleUser;
    }

    @Override
    public boolean update(RoleUser roleUser) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean save(RoleUser roleUser) {
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
            preparedStatement.setLong(1, roleUser.getRoleId());
            preparedStatement.setLong(2, roleUser.getUserId());
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
    public boolean delete(RoleUser roleUser) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_ASSIGNMENT_BY_ROLE_ID_AND_USER_ID);
            preparedStatement.setLong(1, roleUser.getRoleId());
            preparedStatement.setLong(2, roleUser.getUserId());
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

    public boolean deleteByUserId(Long userId) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_DELETE_ASSIGNMENTS_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Assignment(s) was(were) deleted");
                return true;
            } else {
                log.warn("Assignment(s) wasn't(weren't) deleted");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }

    public List<RoleUser> findByUserId(Long userId) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<RoleUser> rolesUsers = new ArrayList<>();
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_BY_USER_ID);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            while (resultSet.next()) {
                RoleUser roleUser = new RoleUser();
                roleUser.setRoleId(resultSet.getLong("role_id"));
                roleUser.setUserId(resultSet.getLong("user_id"));
                rolesUsers.add(roleUser);
            }
            log.debug("Assignments were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return rolesUsers;
    }
}
