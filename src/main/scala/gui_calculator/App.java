package gui_calculator;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class App{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                JFrame frame = new MainFrame("Matrix Calculator");
                frame.setSize(800,600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}