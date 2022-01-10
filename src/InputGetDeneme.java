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
                JLabel labelParent = new JLabel("ParentID");
                JTextField getParentID = new JTextField(20);
                JLabel labelName = new JLabel("Name");
                JTextField getName = new JTextField(20);
                JLabel labelBirthdate = new JLabel("Birthdate");
                JTextField getbirthDate = new JTextField(20);
                JLabel labelGender = new JLabel("gender");
                JTextField getGender = new JTextField(20);
                JButton btn = new JButton("Add");


                rightPanel.add(labelParent);
                rightPanel.add(getParentID);
                rightPanel.add(labelName);
                rightPanel.add(getName);
                rightPanel.add(labelBirthdate);
                rightPanel.add(getbirthDate);
                rightPanel.add(labelGender);
                rightPanel.add(getGender);
                rightPanel.add(btn);

                JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftPanel),new JScrollPane(rightPanel));
                mainSplitPane.setResizeWeight(0.4);
                //JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(leftTopPanel),new JScrollPane(leftBotPanel));
                //rightPanel.add(leftSplitPane);
                //rightPanel.add(leftTopPanel,leftBotPanel);
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
                            JTree tree = new JTree(rootNode);
                            familyTree.put(personID,rootNode);
                            leftPanel.add(tree);
                            newFrame.pack();
                        }
                        else
                        {
                            DefaultMutableTreeNode parentNode = familyTree.get(parentID);
                            //parentPerson = peopleInTree.get(parentID);
                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newPerson.getName() + " - " + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            newPerson.setID(personID++);
                            newPerson.setName(name);
                            newPerson.setGender(gender);
                            newPerson.setBirthDate(birthdate);
                            parentNode.add(newNode);
                            familyTree.put(personID,newNode);
                            newFrame.pack();
                        }

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
