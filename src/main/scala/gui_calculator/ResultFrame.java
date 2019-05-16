package gui_calculator;

import gui_calculator.panels.MatrixPanelWithLabels;
import matrix_source.Matrix;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {
    public ResultFrame(Matrix matrix, String opType) {
        super("Result: " + opType);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        MatrixPanelWithLabels resPan = new MatrixPanelWithLabels();
        resPan.setMatrix(matrix);
        this.add(resPan);
    }

    public void Show() {
        this.setVisible(true);
    }
}

