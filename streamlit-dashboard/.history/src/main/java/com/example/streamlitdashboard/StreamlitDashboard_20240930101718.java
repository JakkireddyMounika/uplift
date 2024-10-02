package com.example.streamlitdashboard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StreamlitDashboard {
    public StreamlitDashboard() {
        // Create a GUI for your dashboard
        JFrame frame = new JFrame("Streamlit Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(new JLabel("Streamlit Dashboard"));
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

        frame.add(launchApp1Button);
        frame.add(launchApp2Button);

        frame.setSize(300, 250);
        frame.setVisible(true);
    }
    public static void main(String[] args) {
        new StreamlitDashboard();
    }
}