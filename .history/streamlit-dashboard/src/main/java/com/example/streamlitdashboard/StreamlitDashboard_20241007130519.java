package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StreamlitDashboard {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public StreamlitDashboard() {
        // Create a GUI for your dashboard
        frame = new JFrame("Your Personalised Mental Health Assistant");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300); 
        frame.setLocationRelativeTo(null); // Center the window on the screen
        frame.setResizable(false); // Make the window non-resizable
        frame.setLayout(new BorderLayout()); // Use a BorderLayout to arrange the components

        JLabel label = new JLabel("Streamlit Dashboard");
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        frame.add(label, BorderLayout.NORTH);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2)); // Use a GridLayout to arrange the components

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Arial", Font.BOLD, 18));
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 18));
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        loginPanel.add(loginButton);

        JButton signupButton = new JButton("Signup");
        signupButton.setFont(new Font("Arial", Font.BOLD, 18));
        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup();
            }
        });
        loginPanel.add(signupButton);

        frame.add(loginPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mydb.db")) {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                JOptionPane.showMessageDialog(frame, "Login successful!");
                // Open the main dashboard
                openDashboard();
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid username or password!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error connecting to database: " + e.getMessage());
        }
    }

    private void signup() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:mydb.db")) {
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(frame, "Signup successful!");
            // Open the main dashboard
            openDashboard();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Error connecting to database: " + e.getMessage());
        }
    }

    private void openDashboard() {
        // Create a new dashboard frame
        JFrame dashboardFrame = new JFrame("Your Personalised Mental Health Assistant");
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(500, 300); 
        dashboardFrame.setLocationRelativeTo(null); // Center the window on the screen
        dashboardFrame.setResizable(false); // Make the window non-resizable
        dashboardFrame.setLayout(new BorderLayout()); // Use a BorderLayout to arrange the components

        // Add dashboard components here...

        dashboardFrame.setVisible(true);
        frame.dispose();
    }

    public static void main(String[] args) {
        new StreamlitDashboard();
    }
}

/*
C:\Users\syeda\OneDrive\Desktop\pas\Uplift\streamlit-dashboard\src\main\java\com\example\streamlitdashboard

javac StreamlitDashboard.java
java -cp . com.example.streamlitdashboard.StreamlitDashboard
*/