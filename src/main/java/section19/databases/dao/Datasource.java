package section19.databases.dao;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Datasource implements AutoCloseable {
    public static final String DB_NAME = "music.db";
    public static final String PATH = "jdbc:sqlite:./db/";
    public static final String CONNECTION_STRING = PATH + DB_NAME;

    private Connection conn;

    public Datasource() {
        open();
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            log.info("#open() - SQL Exception: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            log.info("#close() - SQL Exception: {}", e.getMessage());
        }
    }

    public Statement createStatement() {
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            log.info("#createStatement() - SQL Exception: {}", e.getMessage());
            return null;
        }
    }
}
