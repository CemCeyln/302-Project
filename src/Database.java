import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:sqlite:C:..\\302-Project\\sqlite_db\\familytree.db";
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Database connected.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
