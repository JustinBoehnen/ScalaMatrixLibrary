package GUI_Calculator.Panels;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixSettingsPanel extends JPanel {
    private IMatrixPanel _panel;
    private JTextField rowsText = new JTextField(6);
    private JTextField colsText = new JTextField(6);
    private JButton resetButton = new JButton("RESET");

    public MatrixSettingsPanel(IMatrixPanel panel){
        _panel = panel;
        this.setPreferredSize(new Dimension(250, 250));
        Draw();

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rows = Integer.parseInt(rowsText.getText());
                    int cols = Integer.parseInt(colsText.getText());

                    if(rows > 100 || cols > 100)
                        JOptionPane.showMessageDialog(null, "resetButton.addActionListener:\n"
                                + "[Maximum matrix dimensions limited to 100x100 for app performace]",
                                "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                    else if(rows < 1 || cols < 1)
                        JOptionPane.showMessageDialog(null, "resetButton.addActionListener:\n"
                                        + "[Matrix dimension must be at least 1]",
                                "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                    else
                        _panel.ResetMatrix(rows, cols);
                }
                catch (Exception err){
                    JOptionPane.showMessageDialog(null, "resetButton.addActionListener:\n"
                            + "[" + err.getMessage() + "]", "Something is very wrong!",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void Draw(){
        this.setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.insets = new Insets(5,5,5,5);
        gc.anchor = GridBagConstraints.EAST;
        gc.gridx = 0; gc.gridy = 0;
        this.add(new JLabel("Rows  "),gc);

        gc.gridx = 0; gc.gridy = 1;
        this.add(new JLabel("Cols  "),gc);

        gc.gridx = 1; gc.gridy = 0;
        rowsText.setText(Integer.toString(_panel.GetMatrix().rows()));
        this.add(rowsText,gc);

        gc.gridx = 1; gc.gridy = 1;
        colsText.setText(Integer.toString(_panel.GetMatrix().cols()));
        this.add(colsText,gc);

        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.gridwidth = 2;
        gc.gridx = 0; gc.gridy = 2;
        this.add(resetButton,gc);
    }
}