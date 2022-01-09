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
                newFrame.setVisible(true);
                frame.setVisible(false);
                frame.dispose();
            }
        });
    }
}
