package GUI_Calculator.Panels;

import MatrixSource.Matrix;
import scala.Int;

import javax.swing.*;
import java.awt.*;

public interface IMatrixPanel {
    void Draw();
    Matrix GetMatrix();
    void SetMatrix(Matrix matrix);
    void ResetMatrix(int rows, int cols);
    void StoreData();
}

