package com.example.streamlitdashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StreamlitDashboard extends JFrame {

    public StreamlitDashboard() {
        // Create a GUI for your dashboard
        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));

        root.add(new JLabel("Streamlit Dashboard"));
        JButton launchApp1Button = new JButton("Launch Streamlit App 1");
        JButton launchApp2Button = new JButton("Launch Streamlit App 2");

        launchApp1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to launch Streamlit App 1 here
                System.out.println("Launching Streamlit App 1...");
            }
        });

        launchApp2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add code to launch Streamlit App 2 here
                System.out.println("Launching Streamlit App 2...");
            }
        });

        root.add(launchApp1Button);
        root.add(launchApp2Button);

        // Create a frame and set the content pane
        this.setTitle("Streamlit Dashboard");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(root);
        this.setSize(300, 250);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new StreamlitDashboard();
    }
}