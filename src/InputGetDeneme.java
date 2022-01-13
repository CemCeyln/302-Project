import com.sun.source.tree.Tree;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class InputGetDeneme {
    public static int personID = 1;
    public static ArrayList<Relation> Relations = new ArrayList<Relation>();
    public static ArrayList<Person> peopleInTree = new ArrayList<Person>();
    public static ArrayList<JTree> Trees = new ArrayList<JTree>();
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public static ArrayList<Node> nodesTemp = new ArrayList<Node>();
    public static JTree tree;
    public static ArrayList<String> relationList = new ArrayList<String>();
   // public static HashMap<String, DefaultMutableTreeNode> listedRelations = new HashMap<String, DefaultMutableTreeNode>();
   /* public static void methodWrite() throws IOException {
        String createPath = "C:";
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(createPath));
        oos.writeObject(nodes);
        oos.close();
    }

    public static void methodRead() throws IOException, ClassNotFoundException {
        String filePath = "C:";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
        nodes = (ArrayList<Node>) ois.readObject();
        ois.close();
    } */

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1200,600);
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
                newFrame.setSize(1200,600);
                JPanel leftPanel = new JPanel();
                JPanel rightPanel = new JPanel();

                //Sol üst taraf
                JLabel labelExp = new JLabel("Lütfen bu kısmı ağaca yeni bir çocuk eklerken kullanın.");
                labelExp.setPreferredSize(new Dimension(15,15));
                JLabel labelExp2 = new JLabel("Eğer ağaca ilk kişiyi ekliyorsanız Ebeveyn ID'yi 0 olarak kullanın.");
                labelExp2.setPreferredSize(new Dimension(15,15));
                JLabel labelParent = new JLabel("Ebeveyn ID");
                JTextField getParentID = new JTextField(15);
                JLabel labelName = new JLabel("İsim");
                JTextField getName = new JTextField(15);
                JLabel labelBirthdate = new JLabel("Doğum Tarihi");
                JTextField getbirthDate = new JTextField(15);
                JLabel labelGender = new JLabel("Cinsiyet");
                JTextField getGender = new JTextField(15);
                JButton btn = new JButton("Çocuk Ekle");

                //Sağ üst taraf
                JLabel labelExp3 = new JLabel("Lütfen bu kısmı ağaca yeni bir eş eklerken kullanın");
                labelExp3.setPreferredSize(new Dimension(15,15));
                JLabel labelExp4 = new JLabel("Eğer ağaca ilk kişiyi ekliyorsanız Ebeveyn ID'yi 0 olarak kullanın.");
                labelExp4.setPreferredSize(new Dimension(15,15));
                JLabel labelSpouseID = new JLabel("Eş ID");
                JTextField getSpouseID = new JTextField(15);
                JLabel labelNameSpouse = new JLabel("İsim");
                JTextField getNameSpouse = new JTextField(15);
                JLabel labelBirthdateSpouse = new JLabel("Doğum Tarihi");
                JTextField getbirthDateSpouse = new JTextField(15);
                JLabel labelGenderSpouse = new JLabel("Cinsiyet");
                JTextField getGenderSpouse = new JTextField(15);
                JButton btn2 = new JButton("Eş Ekle");

                //Sol alt taraf
                JLabel labelExp5 = new JLabel("Lütfen bu kısmı ağaç üzerinde değişiklik yapmak için kullanın.");
                labelExp5.setPreferredSize(new Dimension(15,15));
                JLabel labelExp6 = new JLabel("Eğer bilgileri değiştirecekseniz, yeni bilgileri yazıp Güncelle butonuna basın");
                JLabel labelExp7 = new JLabel("Eğer kişi silecekseniz Kaldır butonuna basın. (Bütün çocukları silinir.)");
                labelExp6.setPreferredSize(new Dimension(15,15));
                labelExp7.setPreferredSize(new Dimension(15,15));
                JLabel labelID = new JLabel("ID");
                JTextField getEditedID = new JTextField(15);
                JLabel labelEditedName = new JLabel("İsim");
                JTextField getEditedName = new JTextField(15);
                JLabel labelEditedBirthdate = new JLabel("Doğum Tarihi");
                JTextField getEditedbirthDate = new JTextField(15);
                JLabel labelEditedGender = new JLabel("Cinsiyet");
                JTextField getEditedGender = new JTextField(15);
                JButton editBtn = new JButton("Güncelle");
                JButton removeBtn = new JButton("Kaldır");

                //Sağ alt taraf
             //   JLabel labelExp8 = new JLabel("Akrabalık ilişkilerini görüntüle");
              //  JLabel labelExp9 = new JLabel("Kişinin ID'sini girin.");
                //labelExp8.setPreferredSize(new Dimension(15,15));
                //labelExp9.setPreferredSize(new Dimension(15,15));
                JLabel labelsearchID = new JLabel("İlişkileri görmek için bir ID girin");
                JTextField getSearchID = new JTextField(15);
                getSearchID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                JButton searchbtn = new JButton("İlişkileri listele");

                rightPanel.setLayout(new GridLayout(2,2));
                JPanel topLeft = new JPanel();
                topLeft.setLayout(new BoxLayout(topLeft, BoxLayout.PAGE_AXIS));
                JPanel topRight = new JPanel();
                topRight.setLayout(new BoxLayout(topRight,BoxLayout.PAGE_AXIS));
                JPanel botLeft = new JPanel();
                botLeft.setLayout(new BoxLayout(botLeft,BoxLayout.PAGE_AXIS));
                JPanel botRight = new JPanel();
                botLeft.setBorder(BorderFactory.createLineBorder(Color.black));
                botRight.setBorder(BorderFactory.createLineBorder(Color.black));
                botRight.setLayout(new BoxLayout(botRight,BoxLayout.PAGE_AXIS));

                //Sol üst panele ekleme
                topLeft.setBorder(BorderFactory.createLineBorder(Color.black));
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

                //Sağ üst panele ekleme
                topRight.setBorder(BorderFactory.createLineBorder(Color.black));
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

                //Sol alt panele ekleme
                botLeft.add(labelExp5);
                botLeft.add(labelExp6);
                botLeft.add(labelExp7);
                botLeft.add(labelID);
                botLeft.add(getEditedID);
                botLeft.add(labelEditedName);
                botLeft.add(getEditedName);
                botLeft.add(labelEditedBirthdate);
                botLeft.add(getEditedbirthDate);
                botLeft.add(labelEditedGender);
                botLeft.add(getEditedGender);
                botLeft.add(editBtn);
                botLeft.add(removeBtn);

                //Sağ alt panele ekleme
               // botRight.add(labelExp8);
               // botRight.add(labelExp9);

                botRight.add(labelsearchID);
                botRight.add(getSearchID);
                botRight.add(searchbtn);

                rightPanel.add(topLeft);
                rightPanel.add(topRight);
                rightPanel.add(botLeft);
                rightPanel.add(botRight);


                JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftPanel),new JScrollPane(rightPanel));
                mainSplitPane.setResizeWeight(0.6);
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
                            //familyTree.put(personID,rootNode);
                            Node newnode = new Node();
                            newnode.setNodeID(personID);
                            newnode.setNode(rootNode);
                            nodes.add(newnode);
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
                            newPerson.setID(++personID);
                            newPerson.setName(name);
                            newPerson.setGender(gender);
                            newPerson.setBirthDate(birthdate);
                           for(Node var : nodes)
                           {
                               if(var.getNodeID() == parentID)
                               {
                                   DefaultMutableTreeNode parentNode = var.getNode();
                                   DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newPerson.getName() + " - ID" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                                   parentNode.add(newNode);
                                   Node newnode = new Node();
                                   newnode.setNodeID(newPerson.getID());
                                   newnode.setNode(newNode);
                                   nodesTemp.add(newnode);
                               }
                               else if(var.getNodeID() == otherParent.getID())
                               {
                                   DefaultMutableTreeNode otherParentNode = var.getNode();
                                   DefaultMutableTreeNode newNode2 = new DefaultMutableTreeNode(newPerson.getName() + " - ID:" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                                   otherParentNode.add(newNode2);
                                   Node newnode = new Node();
                                   newnode.setNodeID(newPerson.getID());
                                   newnode.setNode(newNode2);
                                   nodesTemp.add(newnode);
                               }
                           }
                            nodes.addAll(nodesTemp);
                            nodesTemp.clear();
                            for(JTree var: Trees)
                            {
                                var.updateUI();
                            }
                            peopleInTree.add(newPerson);
                        }
                        Dimension size = newFrame.getSize();
                        newFrame.pack();
                        newFrame.setSize(size);
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
                            DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newPerson.getName() + " - ID:" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                            tree = new JTree(rootNode);
                            //familyTree.put(personID,rootNode);
                            leftPanel.add(tree);
                            peopleInTree.add(newPerson);
                            Node newnode = new Node();
                            newnode.setNodeID(personID);
                            newnode.setNode(rootNode);
                            nodes.add(newnode);
                            Trees.add(tree);

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
                            DefaultMutableTreeNode newTreeRootNode = new DefaultMutableTreeNode(newPerson.getName() + " - ID:" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender()+ "- " + spousePerson.getName() + "'nin Eşi");
                            //familyTree.put(personID, newTreeRootNode);
                            JTree newTree = new JTree(newTreeRootNode);;
                            leftPanel.add(newTree);
                            peopleInTree.add(newPerson);
                            Trees.add(newTree);
                            Node newnode = new Node();
                            newnode.setNodeID(personID);
                            newnode.setNode(newTreeRootNode);
                            nodes.add(newnode);
                        }
                      /*  Trees.get(Trees.size() - 1).addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {
                                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)Trees.get(Trees.size()-1).getLastSelectedPathComponent();
                                String[] splitName = selectedNode.getUserObject().toString().split("-");
                                String[] id = splitName[1].split(":");
                                getEditedName.setText(splitName[0]);
                                getEditedID.setText(id[1]);
                                getEditedbirthDate.setText(splitName[2]);
                                getEditedGender.setText(splitName[3]);
                            }
                        }); */
                    /*    tree.addTreeSelectionListener(new TreeSelectionListener() {
                            @Override
                            public void valueChanged(TreeSelectionEvent e) {

                                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
                                String[] splitName = selectedNode.getUserObject().toString().split("-");
                                getEditedName.setText(splitName[0]);
                                getEditedID.setText(splitName[1]);
                                getEditedbirthDate.setText(splitName[2]);
                                getEditedGender.setText(splitName[3]);
                            }
                        }); */
                        botLeft.setVisible(true);
                        Dimension size = newFrame.getSize();
                        newFrame.pack();
                        newFrame.setSize(size);
                    }
                });
                //Remove butonu
                removeBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int removedPersonID = Integer.parseInt(getEditedID.getText());
                        Person removedPerson = null;
                        Person removedPersonSpouse = null;


                        for(Person var : peopleInTree) //Silinecek person'ı arıyorum
                        {
                            if(var.getID() == removedPersonID)
                            {
                                removedPerson = var;
                            }
                        }

                        for(Relation var: Relations)
                        {
                            if(var.getSpouse1().getID() == removedPersonID)
                            {
                                removedPersonSpouse = var.getSpouse2();
                                break;
                            }
                            else if (var.getSpouse2().getID() == removedPersonID)
                            {
                                removedPersonSpouse = var.getSpouse1();
                                break;
                            }
                        }

                        for(Node var : nodes)
                        {
                            if(var.getNodeID() == removedPerson.getID())
                            {
                                var.getNode().removeAllChildren();
                                var.getNode().removeFromParent();
                            }
                            else if(var.getNodeID() == removedPersonSpouse.getID())
                            {
                                var.getNode().removeAllChildren();
                                if(var.getNode().isRoot())
                                {
                                    var.getNode().setUserObject(null);
                                }
                                else
                                {
                                    var.getNode().removeFromParent();
                                }
                                //eğer parent root ise nasıl kaldıracağını kontrol et

                            }
                        }
                        for(JTree var: Trees)
                        {
                            var.updateUI();
                        }
                    }
                });
                editBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int editedPersonID = Integer.parseInt(getEditedID.getText());
                        String editedPersonName = getEditedName.getText();
                        String editedPersonBirthdate = getEditedbirthDate.getText();
                        String editedPersonGender = getEditedGender.getText();

                        Person editedPerson = null;
                        for(Person var : peopleInTree) //Editlenecek person'ı arıyorum
                        {
                            if(var.getID() == editedPersonID)
                            {
                                editedPerson = var;
                            }
                        }
                        editedPerson.setName(editedPersonName);
                        editedPerson.setBirthDate(editedPersonBirthdate);
                        editedPerson.setGender(editedPersonGender);
                        for(Node var : nodes)
                        {
                            if(var.getNodeID() == editedPerson.getID())
                            {
                                var.getNode().setUserObject(editedPerson.getName() + " - ID:" + editedPerson.getID() + " - " + editedPerson.getBirthDate() + " - " + editedPerson.getGender());
                            }
                        }
                        for(JTree var: Trees)
                        {
                            var.updateUI();
                        }
                    }
                });

                searchbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int searchedPersonID = Integer.parseInt(getSearchID.getText());
                        for(Node var : nodes)
                        {
                            if(var.getNodeID() == searchedPersonID)
                            {
                                if(!(var.getNode().isRoot()))
                                {
                                    String parentInfo = null;
                                    String grandParentInfo = null;
                                    DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) var.getNode().getParent();
                                    String[] parentInfoArray = (parentNode.getUserObject()).toString().split("-");
                                    if(parentInfoArray[3].toLowerCase().equals(" erkek"))
                                    {

                                        parentInfo = "Baba: " + parentInfoArray[0];
                                    }
                                    else if(parentInfoArray[3].toLowerCase().equals(" kadın"))
                                    {
                                        parentInfo = "Anne: " + parentInfoArray[0];
                                    }
                                    if(!(relationList.contains(parentInfo)))
                                    {
                                        relationList.add(parentInfo);
                                    }
                                    System.out.println(parentInfo);
                                    if(!(parentNode.isRoot()))
                                    {
                                        DefaultMutableTreeNode grandParentNode = (DefaultMutableTreeNode)  parentNode.getParent();
                                        String[] grandParentInfoArray = grandParentNode.getUserObject().toString().split("-");
                                        if(grandParentInfoArray[3].toLowerCase().equals(" erkek"))
                                        {
                                            grandParentInfo = "Dede: " + parentInfoArray[0];
                                        }
                                        else if(grandParentInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" erkek"))
                                        {
                                            grandParentInfo = "Babaanne: " + parentInfoArray[0];
                                        }
                                        else if(grandParentInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" kadın"))
                                        {
                                            grandParentInfo = "Anneanne: " + parentInfoArray[0];
                                        }
                                        if(!(relationList.contains(grandParentInfo)))
                                        {
                                            relationList.add(grandParentInfo);
                                        }
                                        System.out.println(grandParentInfo);
                                    }
                                }
                                else if(!(var.getNode().isLeaf()))
                                {
                                    var.getNode().children();

                                }
                                else if(var.getNode().getSiblingCount() != 0)
                                {
                                    var.getNode().getNextSibling().getUserObjectPath();
                                    var.getNode().getPreviousSibling().getUserObjectPath();
                                }
                            }
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
