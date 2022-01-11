package section19.databases.testdb;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Main {
    private static final String CONNECTION_STRING = "jdbc:sqlite:/Users/jonasa/projects/aj/JavaCourse/db/test_java.db";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING)) {
            Statement statement = conn.createStatement();

            statement.execute("CREATE TABLE IF NOT EXISTS contacts " +
                    "(name  TEXT, phone INTEGER, email TEXT)");

            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "values ('Adam', 123456789, 'adam@email.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "values ('Jane', 987654321, 'jane@email.com')");
            statement.execute("INSERT INTO contacts (name, phone, email) " +
                    "values ('John', 666666666, 'john@email.com')");

        } catch (SQLException e) {
            log.error("SQL Exception: {}", e.getMessage());
        }
    }
}
