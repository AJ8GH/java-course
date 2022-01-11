package section19.databases.testdb;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Slf4j
public class Main {
    public static final String DB_NAME = "test_java.db";
    private static final String CONNECTION_STRING = "jdbc:sqlite:/Users/jonasa/projects/aj/JavaCourse/db/" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = conn.createStatement()) {

            createContactsTable(statement);
            insertContact(statement, "Adam", 123456789,"adam@email.com");
            insertContact(statement, "Jane", 987654321,"jane@email.com");
            insertContact(statement, "Fido", 666666666,"dog@email.com");

            printContacts(statement);
        } catch (SQLException e) {
            log.error("SQL Exception: {}", e.getMessage());
        }
    }

    private static void createContactsTable(Statement statement) {
        try {
            statement.execute("CREATE TABLE IF NOT EXISTS " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text, " + COLUMN_PHONE + "integer, " + COLUMN_EMAIL + "text)");
        } catch (SQLException e) {
            log.error("createTable - SQL Exception: {}", e.getMessage());
        }
    }

    private static void insertContact(Statement statement,
                                      String name,
                                      Integer phone,
                                      String email) {
        try {
            statement.execute("INSERT INTO " + TABLE_CONTACTS +
                    "(" + COLUMN_NAME + ", " + COLUMN_PHONE + ", " + COLUMN_EMAIL + ") " +
                    "values ('" + name + "', " + phone + ", '" + email + "')");
        } catch (SQLException e) {
            log.error("insertContact - SQL Exception: {}", e.getMessage());
        }
    }

    private static void printContacts(Statement statement) {
        try (ResultSet results = statement.executeQuery(
                "SELECT DISTINCT * FROM " + TABLE_CONTACTS)) {
            while (results.next()) {
                System.out.println(results.getString("name") + " " +
                        results.getInt("phone") + " " +
                        results.getString("email"));
            }
        } catch (SQLException e) {
            log.error("printContacts - SQL Exception: {}", e.getMessage());
        }
    }
}
