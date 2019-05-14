package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResultFrame extends JFrame {
    Matrix _matrix;
    private int _xSize = 400;
    private int _ySize = 400;

    public ResultFrame(Matrix matrix, String opType) {
        super("Result: " + opType);
        _matrix = matrix;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(_xSize, _ySize));
        Draw();
    }

    public void Draw(){
        MatrixPanelWithLabels resPan = new MatrixPanelWithLabels();
        resPan.SetMatrix(_matrix);
        this.add(resPan);
        this.setVisible(true);
    }
}

