package GUI_Calculator;

import GUI_Calculator.Panels.*;
import MatrixSource.Matrix;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame extends JFrame {
    public MainFrame(String title) {
        super(title);

        //set layout manager
        setLayout(new BorderLayout());

        //create swing component
        JPanel calcDash = new JPanel();
        calcDash.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        //panels
        //MatrixPanelNoLabels leftMatrix = new MatrixPanelNoLabels();
        //MatrixPanelNoLabels rightMatrix = new MatrixPanelNoLabels();
        MatrixPanelWithLabels leftMatrix = new MatrixPanelWithLabels();
        MatrixPanelWithLabels rightMatrix = new MatrixPanelWithLabels();
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