import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class InputGetDeneme {
    public static int personID = 1;
    public static HashMap<Integer, DefaultMutableTreeNode> familyTree = new HashMap<Integer, DefaultMutableTreeNode>();
    public static ArrayList<Relation> Relations = new ArrayList<Relation>();
    public static ArrayList<Person> peopleInTree = new ArrayList<Person>();
    public static ArrayList<JTree> Trees = new ArrayList<JTree>();
    public static JTree tree;
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLayout(new FlowLayout());

        JButton createBtn = new JButton("Create Tree");
        createBtn.setBounds(200,150,150,150);
        frame.add(createBtn);

        JButton loadBtn = new JButton("Load Tree");
        loadBtn.setBounds(400,150,150,150);
        frame.add(loadBtn);

        JButton mergeBtn = new JButton("Merge Tree");
        mergeBtn.setBounds(600,150,150,150);
        frame.add(mergeBtn);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame newFrame = new JFrame();
                newFrame.pack();
                newFrame.setSize(1000,500);
                JPanel leftPanel = new JPanel();
                JPanel rightPanel = new JPanel();
                //Sol üst taraf
                JLabel labelExp = new JLabel("Please use this for adding child in the tree");
                labelExp.setPreferredSize(new Dimension(15,15));
                JLabel labelExp2 = new JLabel("If you are adding first person to the tree use ID as 0");
                labelExp2.setPreferredSize(new Dimension(15,15));
                JLabel labelParent = new JLabel("ParentID");
                JTextField getParentID = new JTextField(15);
                JLabel labelName = new JLabel("Name");
                JTextField getName = new JTextField(15);
                JLabel labelBirthdate = new JLabel("Birthdate");
                JTextField getbirthDate = new JTextField(15);
                JLabel labelGender = new JLabel("gender");
                JTextField getGender = new JTextField(15);
                JButton btn = new JButton("Add Child");

                //Sağ üst taraf
                JLabel labelExp3 = new JLabel("Please use this for adding spouse in the tree");
                labelExp3.setPreferredSize(new Dimension(15,15));
                JLabel labelExp4 = new JLabel("If you are adding first person to the tree use ID as 0");
                labelExp4.setPreferredSize(new Dimension(15,15));
                JLabel labelSpouseID = new JLabel("SpouseID");
                JTextField getSpouseID = new JTextField(15);
                JLabel labelNameSpouse = new JLabel("Name");
                JTextField getNameSpouse = new JTextField(15);
                JLabel labelBirthdateSpouse = new JLabel("Birthdate");
                JTextField getbirthDateSpouse = new JTextField(15);
                JLabel labelGenderSpouse = new JLabel("gender");
                JTextField getGenderSpouse = new JTextField(15);
                JButton btn2 = new JButton("Add Spouse");

                rightPanel.setLayout(new GridLayout(2,2));
                JPanel topLeft = new JPanel();
                topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.PAGE_AXIS));
                JPanel topRight = new JPanel();
                topRight.setLayout(new BoxLayout(topRight,BoxLayout.PAGE_AXIS));
                JPanel botLeft = new JPanel();
                JPanel botRight = new JPanel();

                //Sol panele ekleme
                topLeft.add(labelExp);
                topLeft.add(labelExp2);
                topLeft.add(labelParent);
                topLeft.add(getParentID);
                topLeft.add(labelName);
                topLeft.add(getName);
                topLeft.add(labelBirthdate);
                topLeft.add(getbirthDate);
                topLeft.add(labelGender);
                topLeft.add(getGender);
                topLeft.add(btn);

                //Sağ panele ekleme
                topRight.add(labelExp3);
                topRight.add(labelExp4);
                topRight.add(labelSpouseID);
                topRight.add(getSpouseID);
                topRight.add(labelNameSpouse);
                topRight.add(getNameSpouse);
                topRight.add(labelBirthdateSpouse);
                topRight.add(getbirthDateSpouse);
                topRight.add(labelGenderSpouse);
                topRight.add(getGenderSpouse);
                topRight.add(btn2);

                rightPanel.add(topLeft);
                rightPanel.add(topRight);
                rightPanel.add(botLeft);
                rightPanel.add(botRight);

                JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftPanel),new JScrollPane(rightPanel));
                mainSplitPane.setResizeWeight(0.4);
                //Add child button
                btn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Person newPerson = new Person();
                        int parentID = Integer.parseInt(getParentID.getText());
                        String name = getName.getText();
                        String birthdate = getbirthDate.getText();
                        String gender = getGender.getText();
                        if(parentID == 0)
                        {
                            newPerson.setID(personID);
                            newPerson.setName(name);
                            newPerson.setGender(gender);
                            newPerson.setBirthDate(birthdate);
                            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            tree = new JTree(rootNode);
                            familyTree.put(personID,rootNode);
                            leftPanel.add(tree);
                            peopleInTree.add(newPerson);
                            Trees.add(tree);
                        }
                        else
                        {
                            Person otherParent = new Person();
                            for(Relation var: Relations)
                            {
                                if(var.getSpouse1().getID() == parentID)
                                {
                                    otherParent = var.getSpouse2();
                                    break;
                                }
                                else if (var.getSpouse2().getID() == parentID)
                                {
                                    otherParent = var.getSpouse1();
                                    break;
                                }
                            }
                            System.out.println(otherParent.getID());


                            DefaultMutableTreeNode parentNode = familyTree.get(parentID);
                            DefaultMutableTreeNode otherParentNode = familyTree.get(otherParent.getID());
                            newPerson.setID(++personID);
                            newPerson.setName(name);
                            newPerson.setGender(gender);
                            newPerson.setBirthDate(birthdate);
                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            DefaultMutableTreeNode newNode2 = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            parentNode.add(newNode);
                            otherParentNode.add(newNode2);
                            for(JTree var: Trees)
                            {
                                var.updateUI();
                            }
                            familyTree.put(personID,newNode);
                            familyTree.put(personID,newNode2);
                            peopleInTree.add(newPerson);
                        }
                        newFrame.pack();
                       // newFrame.setSize(500,100);
                    }
                });
                //Add spouse button
                btn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Person newPerson = new Person();
                        int spouseID = Integer.parseInt(getSpouseID.getText());
                        String name = getNameSpouse.getText();
                        String birthdate = getbirthDateSpouse.getText();
                        String gender = getGenderSpouse.getText();
                        if(spouseID == 0)
                        {
                            newPerson.setID(personID);
                            newPerson.setName(name);
                            newPerson.setBirthDate(birthdate);
                            newPerson.setGender(gender);
                            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            tree = new JTree(rootNode);
                            familyTree.put(personID,rootNode);
                            leftPanel.add(tree);
                            peopleInTree.add(newPerson);
                        }
                        else
                        {
                            Relation newRelation = new Relation();
                            Person spousePerson = new Person();
                            for(Person var : peopleInTree) //Ağaçtan spouse'u arıyorum
                            {
                                if(var.getID() == spouseID)
                                {
                                    spousePerson = var;
                                }
                            }
                            newPerson.setID(++personID);
                            newPerson.setName(name);
                            newPerson.setGender(gender);
                            newPerson.setBirthDate(birthdate);
                            //Adding to relation
                            newRelation.setSpouse2(newPerson);
                            newRelation.setSpouse1(spousePerson);

                            Relations.add(newRelation);
                            DefaultMutableTreeNode newTreeRootNode = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            familyTree.put(personID, newTreeRootNode);
                            JTree newTree = new JTree(newTreeRootNode);;
                            leftPanel.add(newTree);
                            peopleInTree.add(newPerson);
                            Trees.add(newTree);
                        }
                        newFrame.pack();
                    }
                });
                newFrame.add(mainSplitPane);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
