package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StreamlitDashboard {
    public StreamlitDashboard() {
        // Create a GUI for your dashboard
        JFrame frame = new JFrame("Streamlit Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600); // Set the size of the window to 800x600 pixels
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false); // Make the window non-resizable
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png")); // Set the icon of the window
        frame.setLayout(new BorderLayout()); // Use a BorderLayout to arrange the components

        JLabel label = new JLabel("Streamlit Dashboard");
        label.setHorizontalAlignment(JLabel.CENTER);
        frame.add(label, BorderLayout.NORTH);

        JButton launchApp1Button = new JButton("Launch Streamlit App 1");
        JButton launchApp2Button = new JButton("Launch Streamlit App 2");

        launchApp1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("streamlit", "run", "streamlit_apps/app.py");
                    processBuilder.start();
                } catch (IOException ex) {
                    System.err.println("Error launching Streamlit App 1: " + ex.getMessage());
                }
            }
        });

        launchApp2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder("streamlit", "run", "streamlit_apps/cml.py");
                    processBuilder.start();
                } catch (IOException ex) {
                    System.err.println("Error launching Streamlit App 1: " + ex.getMessage());
                }
            }
        });

        frame.add(launchApp1Button, BorderLayout.CENTER);
        frame.add(launchApp2Button, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new StreamlitDashboard();
    }
}