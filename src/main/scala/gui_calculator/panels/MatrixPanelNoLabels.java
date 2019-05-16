package gui_calculator.panels;

import MatrixSource.Matrix;

import javax.swing.*;
import java.awt.*;

public class MatrixPanelNoLabels extends ScrollPane implements IMatrixPanel {
    private Matrix _matrix;
    private JPanel matrixPanel = new JPanel();
    private GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanelNoLabels(){
        _matrix = new Matrix(3,3, new double[3][3]);

        this.setPreferredSize(new Dimension(250, 250));
        draw();
    }

    public void draw(){
        matrixPanel.setLayout(new GridBagLayout());
        matrixPanel.removeAll();
        gc.insets = new Insets(1,1,1,1);
        for(int row = 0; row < _matrix.Rows(); row++){
            for (int col = 0; col < _matrix.Cols(); col++){
                gc.weightx = 1.0 / _matrix.Cols();
                gc.weighty = 1.0 / _matrix.Rows();
                gc.gridx = col;
                gc.gridy = row;

                matrixPanel.add(new JTextField(Double.toString(_matrix.Index(row,col)),4), gc);
            }
        }
        this.add(matrixPanel);
    }

    public Matrix getMatrix(){
        return _matrix;
    }

    public void setMatrix(Matrix matrix){
        _matrix = matrix;
        draw();
    }

    public void resetMatrix(int rows, int cols){
        try {
            _matrix = new Matrix(rows, cols, new double[rows][cols]);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "resetMatrix\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
        draw();
    }

    public void storeData(){
        try {
            double[][] data = new double[_matrix.Rows()][_matrix.Cols()];
            int count = 0;
            for (int row = 0; row < _matrix.Rows(); row++) {
                for (int col = 0; col < _matrix.Cols(); col++) {
                    data[row][col] = Double.parseDouble(((JTextField) matrixPanel.getComponent(count)).getText());
                    count++;
                }
            }
            _matrix = new Matrix(_matrix.Rows(), _matrix.Cols(), data);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "storeData\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
    }
}