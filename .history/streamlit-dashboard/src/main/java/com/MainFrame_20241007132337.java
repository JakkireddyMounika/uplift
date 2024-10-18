package com;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainFrame extends JFrame {
    public MainFrame() {
        // Set up the frame
        setTitle("Main Frame");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add a label to the frame
        JLabel label = new JLabel("Welcome to the main frame!");
        add(label);
    }
}
