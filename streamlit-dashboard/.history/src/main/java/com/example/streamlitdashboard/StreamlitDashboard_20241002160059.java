package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StreamlitDashboard {
    public static void main(String[] args) {
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
       
               JPanel buttonPanel = new JPanel();
               buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
       
               JButton launchApp1Button = new JButton("Uplift Mental Health Chatbot");
               JButton launchApp2Button = new JButton("Breathing And Mindfulness By Uplift");
       
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
       
               buttonPanel.add(launchApp1Button);
               buttonPanel.add(launchApp2Button);
       
               frame.add(buttonPanel, BorderLayout.CENTER);
       
               frame.setVisible(true);
    }
}

/*javac -d . StreamlitDashboard.java
java -cp . com.example.streamlitdashboard.StreamlitDashboard*/