package GUI_Calculator.Panels;

import MatrixSource.Matrix;
import scala.Int;

import javax.swing.*;
import java.awt.*;

public class MatrixPanelWithLabels extends ScrollPane {
    private Matrix _matrix;
    private int _xSize = 250;
    private int _ySize = 250;
    JPanel matrixPanel = new JPanel();
    GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanelWithLabels(){
        _matrix = new Matrix(3,3);
        this.setPreferredSize(new Dimension(_xSize, _ySize));
        Draw();
    }

    public void Draw(){
        matrixPanel.setLayout(new GridBagLayout());
        matrixPanel.removeAll();
        gc.insets = new Insets(1,1,1,1);
        for(int i = 0; i < _matrix.rows() + 1; i++){
            for (int j = 0; j < _matrix.cols() + 1; j++){
                gc.weightx = 1.0 / _matrix.cols();
                gc.weighty = 1.0 / _matrix.rows();
                gc.gridx = j;
                gc.gridy = i;

                if(j != 0 && i != 0)
                    matrixPanel.add(new JTextField(Double.toString(_matrix.index(i-1,j-1)),4), gc);
                else if (j == 0 && i == 0) matrixPanel.add(new JLabel(""), gc);
                else if (i == 0) matrixPanel.add(new JLabel("" + (j-1)), gc);
                else if (j == 0) matrixPanel.add(new JLabel("" + (i-1)), gc);

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
            _matrix = new Matrix(rows, cols);
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
            _matrix.data_$eq(data);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "StoreData:\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
    }
}

