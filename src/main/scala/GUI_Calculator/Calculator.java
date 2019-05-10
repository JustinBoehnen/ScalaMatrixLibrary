package GUI_Calculator;
import MatrixSource.Matrix;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;

public class Calculator extends Frame implements WindowListener, ActionListener{

    private JPanel panelMain = new JPanel();
    private JButton clearButton;
    private JButton resizeButton;
    private JButton aboutButton;
    private JTextArea yeetTextArea;

    TextField text = new TextField(20);
    Button b;
    private int numClicks = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Matrix Calculator");
        frame.setContentPane(new Calculator().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //Calculator myWindow = new Calculator("Matrix Calculator");
        //myWindow.setSize(500,500);
        //myWindow.setVisible(true);
    }

    public Calculator() {

        super("Yeet");
        setLayout(new FlowLayout());
        addWindowListener(this);
        b = new Button("Click me");
        add(b);
        add(text);
        b.addActionListener(this);

        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Matrix library and calculator " +
                        "(for demonstration) created by Justin Boehnen and Tristen Mengis");
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        numClicks++;
        text.setText("Button Clicked " + numClicks + " times");
    }

    public void windowClosing(WindowEvent e) {
        dispose();
        System.exit(0);
    }

    public void windowOpened(WindowEvent e) {}
    public void windowActivated(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowDeactivated(WindowEvent e) {}
    public void windowClosed(WindowEvent e) {}
}
