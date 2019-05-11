package GUI_Calculator;

import GUI_Calculator.Panels.ConsolePanel;
import GUI_Calculator.Panels.MatrixPanel;
import GUI_Calculator.Panels.MatrixSettingsPanel;
import GUI_Calculator.Panels.OperationsPanel;
import MatrixSource.Matrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String title) {
        super(title);

        //double[][] testData = {{1,4,7},{2,5,8},{3,6,9}};

        //data
        Matrix _left = new Matrix(3,3);
        Matrix _right = new Matrix(3,3);

        //_left.data_$eq(testData);
        //_right.data_$eq(testData);

        //set layout manager
        setLayout(new BorderLayout());

        //create swing component
        JPanel calcDash = new JPanel();
        calcDash.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //panels
        MatrixPanel leftMatrix = new MatrixPanel(_left);
        MatrixPanel rightMatrix = new MatrixPanel(_right);
        MatrixSettingsPanel leftSettings = new MatrixSettingsPanel(leftMatrix);
        MatrixSettingsPanel rightSettings = new MatrixSettingsPanel(rightMatrix);
        OperationsPanel opPanel = new OperationsPanel(leftMatrix, rightMatrix);
        ConsolePanel aboutPanel = new ConsolePanel();

        //layout
        gc.gridx = 0; gc.gridy = 0;
        calcDash.add(leftMatrix, gc);
        gc.gridx = 2; gc.gridy = 0;
        calcDash.add(rightMatrix, gc);
        gc.gridx = 0; gc.gridy = 1;
        calcDash.add(leftSettings, gc);
        gc.gridx = 2; gc.gridy = 1;
        calcDash.add(rightSettings, gc);
        gc.gridx = 1; gc.gridy = 0;
        calcDash.add(opPanel, gc);
        gc.gridx = 1; gc.gridy = 1;
        calcDash.add(aboutPanel, gc);

        this.add(calcDash);
    }
}