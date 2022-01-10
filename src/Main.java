import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
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
                JPanel leftTopPanel = new JPanel();
                JPanel leftBotPanel = new JPanel();
                leftTopPanel.setBounds(0,400,300,500);
                leftBotPanel.setBounds(0,700,300,500);
                leftBotPanel.setBackground(Color.red);
                leftTopPanel.setBackground(Color.black);
                leftBotPanel.setVisible(true);
                leftTopPanel.setVisible(true);
                leftBotPanel.setLayout(new BorderLayout());
                leftTopPanel.setLayout(new BorderLayout());
                JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(leftPanel),new JScrollPane(rightPanel));
                mainSplitPane.setResizeWeight(0.4);
                //JSplitPane leftSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, new JScrollPane(leftTopPanel),new JScrollPane(leftBotPanel));
                //rightPanel.add(leftSplitPane);
                rightPanel.add(leftTopPanel,leftBotPanel);
                newFrame.add(mainSplitPane);
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
