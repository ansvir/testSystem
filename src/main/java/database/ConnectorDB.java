package database;

import org.apache.log4j.Logger;
import resource.DatabaseManager;

import java.sql.*;
import java.util.MissingResourceException;

public class ConnectorDB {

    private final static Logger log = Logger.getLogger(ConnectorDB.class);

    private String url = DatabaseManager.getProperty("db.url");
    private String user = DatabaseManager.getProperty("db.user");
    private String pass = DatabaseManager.getProperty("db.password");
    private static ConnectorDB instance;
    private static Connection connection;

    private ConnectorDB() {}

    public static synchronized ConnectorDB getInstance() {
        if (instance == null) {
            instance = new ConnectorDB();
            return instance;
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection != null) {
                if (connection.isClosed()) {
                    connection = DriverManager.getConnection(url, user, pass);
                    log.debug("connection with " + url + " was reestablished");
                    return connection;
                }
            } else {
                connection = DriverManager.getConnection(url, user, pass);
                log.debug("connection with " + url + " was established");
            }
            return connection;
        } catch (SQLException e) {
            log.error("error while connecting to " + url, e);
        } catch (MissingResourceException e) {
            log.error("properties file is missing ", e);
        }
        log.warn("connection is null");
        return null;
    }
    public Statement getStatement() throws SQLException {
        if (connection != null) {
            Statement statement = connection.createStatement();
            if (statement != null) {
                return statement;
            }
        }
        log.warn("connection or statement is null");
        return null;
    }

    public void closeStatement(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error("statement is null ", e);
            }
        }
    }

    public void closeStatement(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                log.error("statement is null ", e);
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                log.debug("connection with " + url + " closed");
            } catch (SQLException e) {
                log.error("error while closing connection", e);
            }
        }
    }
}
