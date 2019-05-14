package GUI_Calculator;

import GUI_Calculator.Panels.MatrixPanelNoLabels;
import GUI_Calculator.Panels.MatrixPanelWithLabels;
import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame {
    private Matrix _matrix;

    public ResultFrame(Matrix matrix, String opType) {
        super("Result: " + opType);
        _matrix = matrix;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(400, 400));
        MatrixPanelWithLabels resPan = new MatrixPanelWithLabels();
        resPan.SetMatrix(_matrix);
        this.add(resPan);
        this.setVisible(true);
    }
}

