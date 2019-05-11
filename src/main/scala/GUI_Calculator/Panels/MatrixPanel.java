package GUI_Calculator.Panels;

import MatrixSource.Matrix;
import scala.Int;

import javax.swing.*;
import java.awt.*;

public class MatrixPanel extends ScrollPane {
    private Matrix _matrix;
    private int _xSize = 250;
    private int _ySize = 250;
    JPanel matrixPanel = new JPanel();
    GridBagConstraints gc = new GridBagConstraints();

    public MatrixPanel(Matrix matrix){
        _matrix = matrix;
        this.setPreferredSize(new Dimension(_xSize, _ySize));
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

                matrixPanel.add(new JTextField(Double.toString(_matrix.index(j,i)),4), gc);
            }
        }
        this.add(matrixPanel);
    }

    public Matrix GetMatrix(){
        return _matrix;
    }

    public void ResetMatrix(int rows, int cols){
        try {
            _matrix = new Matrix(rows, cols);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "You just had to go and break our stuff...\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
        Draw();
    }

    public void StoreData(){
        try {
            double[][] data = new double[_matrix.cols()][_matrix.rows()];
            int count = 0;
            for (int i = 0; i < _matrix.rows(); i++) {
                for (int j = 0; j < _matrix.cols(); j++) {
                    data[j][i] = Double.parseDouble(((JTextField) matrixPanel.getComponent(count)).getText());
                    count++;
                }
            }
            _matrix.data_$eq(data);
        }
        catch (Exception err){
            JOptionPane.showMessageDialog(null, "You just had to go and break our stuff...\n"
                    + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
        }
    }
}

