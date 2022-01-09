import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.sql.*;
import java.text.SimpleDateFormat;

public class CreateTreeTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String url = "jdbc:sqlite:C:..\\302-Project\\sqlite_db\\familytree.db";
        Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        Person rootPerson = new Person();
        try {
            conn = DriverManager.getConnection(url);
            System.out.println("Database connected.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String FirstPersonSql = "Select * from Person where personID == 1";
        try {
            assert conn != null;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(FirstPersonSql);
            while (rs.next())
            {
                rootPerson.setName(rs.getString("name"));
                rootPerson.setID(rs.getInt("personID"));
                rootPerson.setBirthDate(rs.getString("birthdate"));
                if(rs.getInt("gender")==0)
                {
                    rootPerson.setGender("Female");
                }
                else
                {
                    rootPerson.setGender("Male");
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

     /*  JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setLayout(new FlowLayout());

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode("Child");
        DefaultMutableTreeNode childNode2 = new DefaultMutableTreeNode("child2");
        DefaultMutableTreeNode grandchild1 = new DefaultMutableTreeNode("GrandChild");
        DefaultMutableTreeNode grandChild2 = new DefaultMutableTreeNode("Grandchild2");
        childNode.add(grandChild2);
        childNode2.add(grandchild1);
        root.add(childNode);
        root.add(childNode2);

        JTree tree = new JTree(root);
        tree.setVisible(true);
        frame.add(tree);
        frame.setVisible(true); */

        JFrame frame = new JFrame();
        frame.setSize(1000, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode root = new DefaultMutableTreeNode(rootPerson.getName() + " - " + rootPerson.getID() + " - " + rootPerson.getBirthDate() + " - " + rootPerson.getGender());
        JTree tree = new JTree(root);
        frame.add(tree);
        frame.setVisible(true);
    }
}
