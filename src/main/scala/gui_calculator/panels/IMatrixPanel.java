package gui_calculator.panels;

import matrix_source.Matrix;

public interface IMatrixPanel {
    void draw();
    Matrix getMatrix();
    void setMatrix(Matrix matrix);
    void resetMatrix(int rows, int cols);
    void storeData();
}