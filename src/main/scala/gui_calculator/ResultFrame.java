package gui_calculator;

import gui_calculator.panels.MatrixPanelWithLabels;
import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;

public class ResultFrame extends JFrame {
    private Matrix _matrix;

    public ResultFrame(Matrix matrix, String opType) {
        super("Result: " + opType);
        _matrix = matrix;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        MatrixPanelWithLabels resPan = new MatrixPanelWithLabels();
        resPan.setMatrix(_matrix);
        this.add(resPan);
        this.setVisible(true);
    }
}

