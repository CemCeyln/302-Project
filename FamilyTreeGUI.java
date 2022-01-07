import javax.swing.*;
import javax.swing.JFrame;

public class FamilyTreeGUI extends JFrame {
    private JPanel MainPanel;
    private JPanel TopPanel;
    private JPanel LeftPanel;
    private JPanel RightPanel;
    private JButton loadButton = new JButton("Load");
    private JButton createButton = new JButton("Create");
    private JButton mergeButton = new JButton("Merge");
    private JPanel infoenterPanel;
    private JPanel viewinfoPanel;
    private JLabel searchlabel = new JLabel("Search : ");
    private JTextField searchingfield;
    private JTextField entername;
    private JTextField entersurname;
    private JTextField enterbirthdate;
    private JTextField entergender;
    private JTextField entermember;
    private JTextField enterrelation;
    private JList memberinfo;
    private JLabel namelabel = new JLabel("Name : ");
    private JLabel surnamelabel = new JLabel("Surname : ");
    private JLabel birthdatelabel = new JLabel("Birthdate : ");
    private JLabel genderlabel = new JLabel("Gender : ");
    private JLabel memberlabel = new JLabel("Member : ");
    private JLabel relationlabel = new JLabel("Relation : ");
    private JLabel memberslabel = new JLabel("Member Info");

    public static void main(String[] args) {

    }
}
