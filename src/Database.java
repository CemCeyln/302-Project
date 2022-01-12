

import javax.xml.transform.Result;
import java.sql.*;

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

        String sql = "Select * from Person";
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                System.out.println(rs.getInt("personID") + " " + rs.getString("name") + " " + rs.getString("birthdate") + " " + rs.getInt("gender"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
