package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;

public class MatrixPanelWithLabels extends ScrollPane implements IMatrixPanel {
    private Matrix _matrix;
    private JPanel matrixPanel = new JPanel();
    private GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanelWithLabels(){
        _matrix = new Matrix(3,3, new double[3][3]);

        this.setPreferredSize(new Dimension(250, 250));
        Draw();
    }

    public void Draw(){
        matrixPanel.setLayout(new GridBagLayout());
        matrixPanel.removeAll();
        gc.insets = new Insets(1,1,1,1);
        for(int row = 0; row < _matrix.rows() + 1; row++){
            for (int col = 0; col < _matrix.cols() + 1; col++){
                gc.weightx = 1.0 / _matrix.cols();
                gc.weighty = 1.0 / _matrix.rows();
                gc.gridx = col;
                gc.gridy = row;

                if(col != 0 && row != 0)
                    matrixPanel.add(new JTextField(Double.toString(_matrix.index(row-1,col-1)),4), gc);
                else if (col == 0 && row == 0) matrixPanel.add(new JLabel(""), gc);
                else if (row == 0) matrixPanel.add(new JLabel("" + (col-1)), gc);
                else if (col == 0) matrixPanel.add(new JLabel("" + (row-1)), gc);

            }
        }
        this.add(matrixPanel);
    }

    public Matrix GetMatrix(){
        return _matrix;
    }

    public void SetMatrix(Matrix matrix){
        _matrix = matrix;
        Draw();
    }

    public void ResetMatrix(int rows, int cols){
        try {
            _matrix = new Matrix(rows, cols, new double[rows][cols]);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "ResetMatrix:\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
        Draw();
    }

    public void StoreData(){
        try {
            double[][] data = new double[_matrix.rows()][_matrix.cols()];
            int count = 0;
            for (int i = 0; i < _matrix.rows() + 1; i++) {
                for (int j = 0; j < _matrix.cols() + 1; j++) {
                    if(i != 0 && j != 0)
                        data[i-1][j-1] = Double.parseDouble(((JTextField) matrixPanel.getComponent(count)).getText());
                    count++;
                }
            }
            _matrix = new Matrix(_matrix.rows(), _matrix.cols(), data);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "StoreData:\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
    }
}