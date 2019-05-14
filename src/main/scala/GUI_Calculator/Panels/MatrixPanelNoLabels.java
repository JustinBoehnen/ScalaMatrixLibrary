package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;

public class MatrixPanelNoLabels extends ScrollPane implements IMatrixPanel {
    private Matrix _matrix;
    private JPanel matrixPanel = new JPanel();
    private GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanelNoLabels(){
        _matrix = new Matrix(3,3);
        this.setPreferredSize(new Dimension(250, 250));
        Draw();
    }

    public void Draw(){
        matrixPanel.setLayout(new GridBagLayout());
        matrixPanel.removeAll();
        gc.insets = new Insets(1,1,1,1);
        for(int i = 0; i < _matrix.rows(); i++){
            for (int j = 0; j < _matrix.cols(); j++){
                gc.weightx = 1.0 / _matrix.cols();
                gc.weighty = 1.0 / _matrix.rows();
                gc.gridx = j;
                gc.gridy = i;

                matrixPanel.add(new JTextField(Double.toString(_matrix.index(i,j)),4), gc);
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
            JOptionPane.showMessageDialog(null, "ResetMatrix\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
        Draw();
    }

    public void StoreData(){
        try {
            double[][] data = new double[_matrix.rows()][_matrix.cols()];
            int count = 0;
            for (int i = 0; i < _matrix.rows(); i++) {
                for (int j = 0; j < _matrix.cols(); j++) {
                    data[i][j] = Double.parseDouble(((JTextField) matrixPanel.getComponent(count)).getText());
                    count++;
                }
            }
            _matrix.data_$eq(data);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "StoreData\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
    }
}

