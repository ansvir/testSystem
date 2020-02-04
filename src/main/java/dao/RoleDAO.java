package dao;

import database.ConnectorDB;
import entity.Role;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO implements Dao<Role>{

    private final static Logger log = Logger.getLogger(RoleDAO.class);

    private final static String SQL_GET_ALL_ROLES = "SELECT * FROM roles";
    private final static String SQL_GET_ROLE_BY_ID = "SELECT * FROM roles WHERE id = ?";
    private final static String SQL_GET_ROLE_BY_NAME = "SELECT * FROM roles WHERE name LIKE ?";
    private final static String SQL_INSERT_USER = "INSERT INTO roles (name) VALUES (?)";
    private final static String SQL_DELETE_USER_BY_ID = "DELETE FROM roles WHERE id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public RoleDAO() {
        connector = ConnectorDB.getInstance();
    }

    public List<Role> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Role> roles = new ArrayList<>();
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_ROLES);
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
                roles.add(role);
            }
            log.debug("Roles were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return roles;
    }

    public Role findById(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Role role = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_ROLE_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    role = new Role(
                            resultSet.getLong("id"),
                            resultSet.getString("name")
                    );
                }
                log.debug("Role was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return role;
    }

    public Role findByName(String name) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Role role = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_ROLE_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    role = new Role(
                            resultSet.getLong("id"),
                            resultSet.getString("name")
                    );
                }
                log.debug("Role was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return role;
    }

    public boolean update(Role role) {
        return false;
    }

    public boolean save(Role role) {
        return false;
    }

    public boolean delete(Role role) {
        return false;
    }
}
