package GUI_Calculator.Panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;

public class MatrixPanelNoLabels extends ScrollPane implements IMatrixPanel {
    private Matrix _matrix;
    private JPanel matrixPanel = new JPanel();
    private GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanelNoLabels(){
        _matrix = new Matrix(3,3, new double[][]{});
        this.setPreferredSize(new Dimension(250, 250));
        Draw();
    }

    public void Draw(){
        matrixPanel.setLayout(new GridBagLayout());
        matrixPanel.removeAll();
        gc.insets = new Insets(1,1,1,1);
        for(int row = 0; row < _matrix.rows(); row++){
            for (int col = 0; col < _matrix.cols(); col++){
                gc.weightx = 1.0 / _matrix.cols();
                gc.weighty = 1.0 / _matrix.rows();
                gc.gridx = col;
                gc.gridy = row;

                matrixPanel.add(new JTextField(Double.toString(_matrix.index(row,col)),4), gc);
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
            _matrix = new Matrix(rows, cols, new double[][]{});
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
            for (int row = 0; row < _matrix.rows(); row++) {
                for (int col = 0; col < _matrix.cols(); col++) {
                    data[row][col] = Double.parseDouble(((JTextField) matrixPanel.getComponent(count)).getText());
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