package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StreamlitDashboard {
    public static void main(String[] args) {
               // Create a GUI for your dashboard
               JFrame frame = new JFrame("Streamlit Dashboard");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setSize(1024, 800); // Set the size of the window to 800x600 pixels
               frame.setLocationRelativeTo(null); // Center the window on the screen
               frame.setResizable(false); // Make the window non-resizable
               frame.setIconImage(Toolkit.getDefaultToolkit().getImage("icon.png")); // Set the icon of the window
               frame.setLayout(new BorderLayout()); // Use a BorderLayout to arrange the components
       
               JLabel label = new JLabel("Streamlit Dashboard");
               label.setHorizontalAlignment(JLabel.CENTER);
               label.setFont(new Font("Arial", Font.BOLD, 24));
               frame.add(label, BorderLayout.NORTH);
       
               JPanel buttonPanel = new JPanel();
               buttonPanel.setLayout(new BorderLayout());
               buttonPanel.setBorder(new EmptyBorder(100, 200, 100, 200));
       
               JPanel innerButtonPanel = new JPanel();
               innerButtonPanel.setLayout(new BorderLayout());
       
               JButton launchApp1Button = new JButton("Launch Streamlit App 1");
               launchApp1Button.setFont(new Font("Arial", Font.BOLD, 18));
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
       
               JButton launchApp2Button = new JButton("Launch Streamlit App 2");
               launchApp2Button.setFont(new Font("Arial", Font.BOLD, 18));
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
       
               innerButtonPanel.add(launchApp1Button, BorderLayout.CENTER);
               innerButtonPanel.add(launchApp2Button, BorderLayout.SOUTH);
       
               buttonPanel.add(innerButtonPanel, BorderLayout.CENTER);
       
               frame.add(buttonPanel, BorderLayout.CENTER);
       
               JLabel upliftLabel = new JLabel("Uplift");
               upliftLabel.setHorizontalAlignment(JLabel.CENTER);
               upliftLabel.setFont(new Font("Arial", Font.BOLD, 24));
               frame.add(upliftLabel, BorderLayout.SOUTH);
       
               frame.setVisible(true);
    }
}

/*javac -d . StreamlitDashboard.java
java -cp . com.example.streamlitdashboard.StreamlitDashboard*/