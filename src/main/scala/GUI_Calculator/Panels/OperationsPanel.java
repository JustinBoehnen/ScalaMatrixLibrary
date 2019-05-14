package GUI_Calculator.Panels;

import GUI_Calculator.ResultFrame;
import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperationsPanel extends JPanel {
    private JButton addButton = new JButton("ADD");
    private JButton subButton = new JButton("SUB");
    private JButton multiplyButton = new JButton("MULTIPLY");

    public OperationsPanel(IMatrixPanel left, IMatrixPanel right){
        this.setPreferredSize(new Dimension(250, 250));
        Draw();

        //Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    left.StoreData(); right.StoreData();
                    ResultFrame res = new ResultFrame(Matrix.add(left.GetMatrix(), right.GetMatrix()), "Addition");
                }
                catch (Exception err){
                    JOptionPane.showMessageDialog(null, "addButton.addActionListener:\n"
                            + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        subButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    left.StoreData(); right.StoreData();
                    ResultFrame res = new ResultFrame(Matrix.subtract(left.GetMatrix(), right.GetMatrix()), "Subtraction");
                }
                catch (Exception err){
                    JOptionPane.showMessageDialog(null, "subButton.addActionListener:\n"
                            + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        multiplyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    left.StoreData(); right.StoreData();
                    ResultFrame res = new ResultFrame(Matrix.multiply(left.GetMatrix(), right.GetMatrix()), "Cross Product");
                }
                catch (Exception err){
                    JOptionPane.showMessageDialog(null, "crossButton.addActionListener:\n"
                            + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void Draw(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.insets = new Insets(5,5,5,5);
        gc.fill = GridBagConstraints.BOTH;
        gc.gridx = 0; gc.gridy = 0;
        gc.anchor = GridBagConstraints.CENTER;
        this.add(addButton,gc);

        gc.gridx = 0; gc.gridy = 1;
        this.add(subButton,gc);

        gc.gridx = 0; gc.gridy = 2;
        this.add(multiplyButton,gc);
    }
}