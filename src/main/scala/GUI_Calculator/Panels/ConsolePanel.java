package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConsolePanel extends JPanel {
    private JButton aboutButton = new JButton("ABOUT");

    public ConsolePanel(){
        this.setPreferredSize(new Dimension(250, 250));
        Draw();

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "This Matrix Calculator was built by Justin Boehnen and Tristen Mengis\n" +
                                "for our CST223 group project. There are two parts to this project:\n" +
                                "1) Matrix Math Library - Scala\n" +
                                "2) GUI Matrix Calculator for library demonstration - Java\n" +
                                "Many of the operations that are supported by our library are not\n" +
                                "implemented by our calculator because a calculator is not an ideal project\n" +
                                "to demonstrate our library on, however it is the most visual and interactive\n" +
                                "demonstration we could compose in a reasonable amount of time.\n" +
                                "Other operations supported by our library include:\n" +
                                "1) Transpose\n" +
                                "2) Traverse\n" +
                                "3) Transform\n\n" +
                                "PS: We understand that Java was covered in class, however we only used it\n" +
                                "for the GUI application that accompanies our primary project, the matrix library,\n" +
                                "which is written completely in scala and intended to be used in Scala and Java.", "About", JOptionPane.QUESTION_MESSAGE);
            }
        });
    }

    public void Draw(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0; gc.gridy = 0;
        this.add(aboutButton, gc);
    }
}