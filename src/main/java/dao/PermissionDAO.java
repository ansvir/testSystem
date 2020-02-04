package dao;

import database.ConnectorDB;
import entity.Permission;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PermissionDAO implements Dao<Permission>{

    private final static Logger log = Logger.getLogger(PermissionDAO.class);

    private final static String SQL_GET_ALL_PERMISSIONS = "SELECT * FROM permissions";
    private final static String SQL_GET_PERMISSION_BY_ID = "SELECT * FROM permissions WHERE id = ?";
    private final static String SQL_GET_PERMISSION_BY_NAME = "SELECT * FROM permissions WHERE name LIKE ?";
    private final static String SQL_INSERT_PERMISSION = "INSERT INTO permissions (name, code) VALUES (?, ?)";
    private final static String SQL_DELETE_PERMISSION_BY_ID = "DELETE FROM permissions WHERE id = ?";
    private final static String SQL_GET_PERMISSIONS_BY_ROLE_ID =
            "SELECT p.id, p.name, p.code FROM permissions p, assigned_permissions ap, roles r " +
            "WHERE p.id = ap.permission_id AND r.id = ap.role_id" +
                    " AND r.id = ?";

    private ConnectorDB connector;
    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;

    public PermissionDAO() {
        connector = ConnectorDB.getInstance();
    }

    public List<Permission> findAll() {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Permission> permissions = null;
        try {
            connection = connector.getConnection();
            statement = connection.createStatement();
            permissions = new ArrayList<>();
            ResultSet resultSet =
                    statement.executeQuery(SQL_GET_ALL_PERMISSIONS);
            while (resultSet.next()) {
                Permission permission = new Permission(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
                permissions.add(permission);
            }
            log.debug("Permissions were returned");
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(statement);
            connector.closeConnection();
        }
        return permissions;
    }

    public Permission findById(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Permission permission = null;
        try {
            connection = connector.getConnection();
            preparedStatement = connection.prepareStatement(SQL_GET_PERMISSION_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                permission = new Permission(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                );
                log.debug("Permission was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return permission;
    }

    public Permission findByName(String name) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        Permission permission = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_PERMISSION_BY_NAME);
            preparedStatement.setString(1, name);
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    permission = new Permission(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("code")
                    );
                }
                log.debug("Permission was returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return permission;
    }

    public boolean update(Permission permission) {
        return false;
    }

    public boolean save(Permission permission) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_INSERT_PERMISSION);
            preparedStatement.setString(1, permission.getName());
            preparedStatement.setString(2, permission.getCode());
            int rowsAffected =
                    preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                log.debug("Permission was saved");
                return true;
            } else {
                log.warn("Permission wasn't saved");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return false;
    }

    public List<Permission> findPermissionsByRoleId(Long id) {
        log.debug("Enter " + new Object() {}
                .getClass()
                .getEnclosingMethod()
                .getName());
        List<Permission> permissions = null;
        try {
            connection = connector.getConnection();
            if (connection == null) {
                log.debug("connection null");
            }
            preparedStatement = connection.prepareStatement(SQL_GET_PERMISSIONS_BY_ROLE_ID);
            preparedStatement.setLong(1, id);
            permissions = new ArrayList<>();
            ResultSet resultSet =
                    preparedStatement.executeQuery();
            if (resultSet != null) {
                while (resultSet.next()) {
                    Permission permission = new Permission(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            resultSet.getString("code")
                    );
                    permissions.add(permission);
                }
                log.debug("Permissions were returned");
            } else {
                log.warn("ResultSet is null");
            }
        } catch (SQLException e) {
            log.error("SQL exception (request or table failed): ", e);
        } finally {
            connector.closeStatement(preparedStatement);
            connector.closeConnection();
        }
        return permissions;
    }
    public boolean delete(Permission permission) {
        return false;
    }

}
