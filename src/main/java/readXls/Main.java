package readXls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import static readXls.XSL.getWords;

public class Main {
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String JDBC_URL = "jdbc:postgresql://localhost:5432/searchTest";
    static final String JDBC_USERNAME = "postgres";
    static final String JDBC_PASSWORD = "qwerty007";
    static final String FILE_PATH_TO_DICTIONARY = "C:/Users/rober_000/IdeaProjects/fullTextSearching/src/main/resources/dictionary.xlsx";

    public static void main(String[] args) {
        Connection conn;
        Statement stmt;
        try {
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(
                    JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = conn.createStatement();
            ArrayList<String> cities = getWords(FILE_PATH_TO_DICTIONARY);


            for (String str : cities) {
                String sql = "INSERT INTO dictionary (word) "
                        + "VALUES "
                        + "(" + "'" + str + "'"
                        + ");";
                System.out.println(sql);
                stmt.executeUpdate(sql);
            }

            stmt.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}
