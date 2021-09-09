package management;

public class idcard {

}

import management.*;
import javax.swing.*;
import javafx.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;

class Main {
    private JFrame window = new JFrame("college Management v0.1");
    private JButton[] btn;

    private void run() {
        Font ft = new Font("Comic Sans MS", Font.BOLD, 18);
        // setting window sepecification

        window.setVisible(true);
        window.setLayout(null);
        window.setSize(2080, 820);
        // window.setExtendedState(JFrame.MAXIMIZED_BOTH);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel head = new JPanel();
        head.setBackground(new Color(123, 250, 200));
        head.setBounds(0, 0, 360, 820);
        head.setLayout(null);

        // head components

        JLabel label1 = new JLabel("College Management");
        label1.setBounds(80, 120, 240, 40);
        label1.setForeground(Color.black);
        label1.setFont(ft);

        btn = new JButton[5];
        btn[0] = new JButton("Login");
        btn[1] = new JButton("Documentation");
        btn[2] = new JButton("test1");
        btn[3] = new JButton("test2");
        btn[4] = new JButton("test3");
        int b = 120;
        for (int i = 0; i < 5; i++) {
            b += 50;
            btn[i].setBounds(30, b, 260, 30);
            btn[i].setForeground(Color.white);
            btn[i].setBackground(Color.BLACK);
            btn[i].setFont(new Font("Adobe Garamond Pro Bold", Font.ITALIC, 15));
            btn[i].addActionListener(actions);
            head.add(btn[i]);
        }
        JLabel label2 = new JLabel("Hi there this is a beta Testing Java swing \n Genrating program");
        label2.setBounds(30, 320, 260, 720);
        label2.setForeground(Color.black);
        head.add(label1);
        head.add(label2);
        window.add(head);

        JPanel body = new JPanel();
        body.setBackground(Color.black);
        body.setBounds(360, 0, 1720, 820);
        window.add(body);
    }

    public void windowClosing(WindowEvent e) {
        // dispose();
        System.exit(0);
    }

    private ActionListener actions=new ActionListener(){@Override public void actionPerformed(ActionEvent e){if(e.getSource()==btn[0]){JOptionPane.showMessageDialog(null,"h");}}};

    public static void main(String args[]) {
        try {
            Main run = new Main();
            run.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
