import com.sun.source.tree.Tree;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.multi.MultiLabelUI;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static int personID = 1;
    public static ArrayList<Relation> Relations = new ArrayList<Relation>();
    public static ArrayList<Person> peopleInTree = new ArrayList<Person>();
    public static ArrayList<JTree> Trees = new ArrayList<JTree>();
    public static ArrayList<Node> nodes = new ArrayList<Node>();
    public static ArrayList<Node> nodesTemp = new ArrayList<Node>();
    public static JTree tree;
    public static ArrayList<String> relationList = new ArrayList<String>();
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JMenuBar menuBarHomePage = new JMenuBar();
        menuBarHomePage.setBounds(0,0,1200,30);
        JMenu menuHomePage = new JMenu("Yardım");
        menuBarHomePage.add(menuHomePage);
        JMenuItem helpButton = new JMenuItem("Yardım");
        menuHomePage.add(helpButton);

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Desktop.getDesktop().browse(new URL("https://github.com/CemCeyln/302-Project/blob/master/Family%20Tree%20Creator%20Application%20Homepage.pdf").toURI());
                } catch (IOException | URISyntaxException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        frame.setTitle("Family Tree Creator");
        frame.setSize(1200,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setLayout(null);
        frame.add(menuBarHomePage);
        JLabel welcomeLabel = new JLabel("Hoşgeldiniz! Lütfen başlamak için butona tıklayınız :)");
        welcomeLabel.setBounds(435,125,Integer.MAX_VALUE,20);
        JButton createBtn = new JButton("Ağaç oluştur");
        createBtn.setBounds(500,160,150,150);
        JLabel createdByLabel = new JLabel("Cem Ceylan tarafından yaratılmıştır.");
        createdByLabel.setBounds(480,400, Integer.MAX_VALUE, 20);
        frame.add(welcomeLabel);
        frame.add(createBtn);
        frame.add(createdByLabel);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame newFrame = new JFrame();
                newFrame.pack();
                newFrame.setSize(1200,600);
                newFrame.setTitle("Family Tree Creator");
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
                JLabel labelExp4 = new JLabel("Eğer ağaca ilk kişiyi ekliyorsanız Eş ID'yi 0 olarak kullanın.");
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

                JLabel labelsearchID = new JLabel("İlişkileri görmek için bir ID girin");
                JTextField getSearchID = new JTextField(15);
                getSearchID.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
                JButton searchbtn = new JButton("İlişkileri listele");
                JLabel relationLabel = new JLabel();


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
                botRight.add(labelsearchID);
                botRight.add(getSearchID);
                botRight.add(searchbtn);
                botRight.add(relationLabel);

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
                        try {
                            int parentID = Integer.parseInt(getParentID.getText());
                            String name = getName.getText();
                            String birthdate = getbirthDate.getText();
                            String gender = getGender.getText();
                            if((gender.toLowerCase().equals("kadın")) || (gender.toLowerCase().equals("erkek")))
                            {
                                if(parentID == 0)
                                {
                                    newPerson.setID(personID);
                                    newPerson.setName(name);
                                    newPerson.setGender(gender);
                                    newPerson.setBirthDate(birthdate);
                                    DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(newPerson.getName() + " - ID:" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
                                    tree = new JTree(rootNode);
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
                                            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(newPerson.getName() + " - ID:" + newPerson.getID() + " - " + newPerson.getBirthDate() + " - " + newPerson.getGender());
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
                            else {
                                JOptionPane.showMessageDialog(null, "Lütfen cinsiyeti 'kadın' ya da 'erkek' olarak girin.");
                            }
                        }
                        catch (Exception exp)
                        {
                            JOptionPane.showMessageDialog(null, "Lütfen ID kısmına geçerli bir değer girin.");
                        }
                    }
                });
                //Add spouse button
                btn2.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Person newPerson = new Person();
                        try
                        {
                            int spouseID = Integer.parseInt(getSpouseID.getText());
                            String name = getNameSpouse.getText();
                            String birthdate = getbirthDateSpouse.getText();
                            String gender = getGenderSpouse.getText();
                            if((gender.toLowerCase().equals("kadın")) || (gender.toLowerCase().equals("erkek")))
                            {
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
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Lütfen cinsiyeti 'kadın' ya da 'erkek' olarak girin.");
                            }
                        }
                        catch (Exception exp)
                        {
                            JOptionPane.showMessageDialog(null, "Lütfen ID kısmına geçerli bir değer girin.");
                        }
                    }
                });
                //Remove butonu
                removeBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try {
                            int removedPersonID = Integer.parseInt(getEditedID.getText());
                            Person removedPerson = null;
                            Person removedPersonSpouse = new Person();
                            removedPersonSpouse.setID(0);


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
                                    if(var.getNode().isRoot())
                                    {
                                        var.getNode().setUserObject(null);
                                        for(int i=0; i < peopleInTree.size(); i++)
                                        {
                                            if(peopleInTree.get(i).getID() == var.getNodeID())
                                            {
                                                peopleInTree.remove(i);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        var.getNode().removeFromParent();
                                        for(int i=0; i < peopleInTree.size(); i++)
                                        {
                                            if(peopleInTree.get(i).getID() == var.getNodeID())
                                            {
                                                peopleInTree.remove(i);
                                            }
                                        }
                                    }
                                }
                                else if(var.getNodeID() == removedPersonSpouse.getID())
                                {
                                    var.getNode().removeAllChildren();
                                    if(var.getNode().isRoot())
                                    {
                                        var.getNode().setUserObject(null);
                                        for(int i=0; i < peopleInTree.size(); i++)
                                        {
                                            if(peopleInTree.get(i).getID() == var.getNodeID())
                                            {
                                                peopleInTree.remove(i);
                                            }
                                        }
                                    }
                                    else
                                    {
                                        var.getNode().removeFromParent();
                                        for(int i=0; i < peopleInTree.size(); i++)
                                        {
                                            if(peopleInTree.get(i).getID() == var.getNodeID())
                                            {
                                                peopleInTree.remove(i);
                                            }
                                        }
                                    }
                                    //eğer parent root ise nasıl kaldıracağını kontrol et
                                }
                            }
                            System.out.println(nodes.size());
                            for(int i=0; i < nodes.size(); i++)
                            {
                                if(nodes.get(i).getNodeID() == removedPerson.getID())
                                {
                                    nodes.remove(i);
                                }
                                if(nodes.get(i).getNodeID() == removedPersonSpouse.getID())
                                {
                                    nodes.remove(i);
                                }
                            }
                            System.out.println(nodes.size());
                            for(JTree var: Trees)
                            {
                                var.updateUI();
                            }
                            personID++;
                        }
                        catch (Exception exp)
                        {
                            JOptionPane.showMessageDialog(null, "Lütfen ID kısmına geçerli bir değer girin.");
                        }
                    }
                });
                editBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            int editedPersonID = Integer.parseInt(getEditedID.getText());
                            String editedPersonName = getEditedName.getText();
                            String editedPersonBirthdate = getEditedbirthDate.getText();
                            String editedPersonGender = getEditedGender.getText();
                            if((editedPersonGender.toLowerCase().equals("kadın")) || (editedPersonGender.toLowerCase().equals("erkek")))
                            {
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
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Lütfen cinsiyeti 'kadın' ya da 'erkek' olarak girin.");
                            }
                        }
                        catch (Exception exp)
                        {
                            JOptionPane.showMessageDialog(null, "Lütfen ID kısmına geçerli bir değer girin.");
                        }
                    }
                });
                //İlişki listeleme
                searchbtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        try
                        {
                            int searchedPersonID = Integer.parseInt(getSearchID.getText());
                            for(Node var : nodes)
                            {
                                if(var.getNodeID() == searchedPersonID)
                                {
                                    String[] nodeInfoArray = var.getNode().getUserObject().toString().split("-");
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
                                        int siblingNumber = parentNode.getChildCount();
                                        if(siblingNumber > 1)
                                        {
                                            for(int a =0; a < siblingNumber; a++)
                                            {
                                                String siblingInfo = null;
                                                DefaultMutableTreeNode siblingNode = (DefaultMutableTreeNode) parentNode.getChildAt(a);
                                                String[] siblingInfoArray = siblingNode.getUserObject().toString().split("-");
                                                if(!(siblingInfoArray[0].equals(nodeInfoArray[0])))
                                                {
                                                    if(siblingInfoArray[3].toLowerCase().equals(" erkek"))
                                                    {
                                                        siblingInfo = "Erkek kardeş: " + siblingInfoArray[0];
                                                    }
                                                    else if(siblingInfoArray[3].toLowerCase().equals(" kadın"))
                                                    {
                                                        siblingInfo = "Kız kardeş: " + siblingInfoArray[0];
                                                    }
                                                    if(!(relationList.contains(siblingInfo)))
                                                    {
                                                        relationList.add(siblingInfo);
                                                    }
                                                }
                                            }
                                        }
                                        if(!(parentNode.isRoot()))
                                        {
                                            String childInfo = null;
                                            DefaultMutableTreeNode grandParentNode = (DefaultMutableTreeNode) parentNode.getParent();
                                            String[] grandParentInfoArray = grandParentNode.getUserObject().toString().split("-");
                                            if(grandParentInfoArray[3].toLowerCase().equals(" erkek"))
                                            {
                                                grandParentInfo = "Dede: " + grandParentInfoArray[0];
                                            }
                                            else if(grandParentInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" erkek"))
                                            {
                                                grandParentInfo = "Babaanne: " + grandParentInfoArray[0];
                                            }
                                            else if(grandParentInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" kadın"))
                                            {
                                                grandParentInfo = "Anneanne: " + grandParentInfoArray[0];
                                            }
                                            if(!(relationList.contains(grandParentInfo)))
                                            {
                                                relationList.add(grandParentInfo);
                                            }
                                            int childNumber = grandParentNode.getChildCount();
                                            for(int k=0; k < childNumber; k++)
                                            {
                                                DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) grandParentNode.getChildAt(k);
                                                String[] childInfoArray = childNode.getUserObject().toString().split("-");
                                                if(!(childInfoArray[0].equals(parentInfoArray[0])))
                                                {
                                                    if((childInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" erkek")))
                                                    {
                                                        childInfo = "Hala: " + childInfoArray[0];
                                                    }
                                                    else if(childInfoArray[3].toLowerCase().equals(" kadın") && parentInfoArray[3].toLowerCase().equals(" kadın"))
                                                    {
                                                        childInfo = "Teyze: " + childInfoArray[0];
                                                    }
                                                    else if(childInfoArray[3].toLowerCase().equals(" erkek") && parentInfoArray[3].toLowerCase().equals(" erkek"))
                                                    {
                                                        childInfo = "Amca: " + childInfoArray[0];
                                                    }
                                                    else if(childInfoArray[3].toLowerCase().equals(" erkek") && parentInfoArray[3].toLowerCase().equals(" kadın"))
                                                    {
                                                        childInfo = "Dayı: " + childInfoArray[0];
                                                    }
                                                    if(!(relationList.contains(childInfo)))
                                                    {
                                                        relationList.add(childInfo);
                                                    }

                                                }
                                            }
                                        }
                                    }
                                    if(var.getNode().getChildCount() != 0)
                                    {
                                        String childInfo = null;
                                        DefaultMutableTreeNode Node = (DefaultMutableTreeNode) var.getNode();
                                        int childCount = Node.getChildCount();
                                        for(int i=0; i < childCount; i++)
                                        {
                                            DefaultMutableTreeNode childNode = (DefaultMutableTreeNode) Node.getChildAt(i);
                                            String[] childInfoArray = childNode.getUserObject().toString().split("-");
                                            childInfo = "Çocuk: " + childInfoArray[0];
                                            if(!(relationList.contains(childInfo)))
                                            {
                                                relationList.add(childInfo);
                                            }
                                        }
                                    }
                                    for(Relation rel: Relations)
                                    {
                                        if(rel.getSpouse1().getID() == searchedPersonID) //ID'yi böl
                                        {
                                            String infoSpouse = "Eş: " + rel.getSpouse2().getName();
                                            relationList.add(infoSpouse);
                                            break;
                                        }
                                        else if (rel.getSpouse2().getID() == searchedPersonID)
                                        {
                                            String infoSpouse = "Eş: " + rel.getSpouse1().getName();
                                            relationList.add(infoSpouse);
                                            break;
                                        }
                                    }
                                }
                            }
                            String relations = "<html>";
                            for(int m = 0; m < relationList.size(); m++)
                            {

                                relations = relations + "<br/>" + relationList.get(m);
                            }
                            relations = relations + "</html>";
                            relationLabel.setText(relations);
                            relationList.clear();
                        }
                        catch (Exception exp)
                        {
                            JOptionPane.showMessageDialog(null, "Lütfen ID kısmına geçerli bir değer girin.");
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
