package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
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

public class MainFrame extends JFrame {
    JPanel mainPanel;
    CardLayout cardLayout;

    private JTextField loginUsernameField;
    private JPasswordField loginPasswordField;
    private JTextField registerUsernameField;
    private JPasswordField registerPasswordField;

    private JButton loginButton;
    private JButton registerButton;
    private JButton switchToRegisterButton;
    private JButton switchToLoginButton;

    private Connection conn;

    public MainFrame() {
        // Set up the frame
        setTitle("Authentication");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // CardLayout for switching between login and register panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Connect to the database
        connectToDatabase();

        // Add login and register panels to the card layout
        mainPanel.add(createLoginPanel(), "loginPanel");
        mainPanel.add(createRegisterPanel(), "registerPanel");

        // Add the main panel to the frame
        add(mainPanel, BorderLayout.CENTER);

        // Show login panel initially
        cardLayout.show(mainPanel, "loginPanel");
    }

    // Method to create the login panel
    private JPanel createLoginPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Login form fields
        JLabel loginUsernameLabel = new JLabel("Username:");
        loginUsernameField = new JTextField();

        JLabel loginPasswordLabel = new JLabel("Password:");
        loginPasswordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        switchToRegisterButton = new JButton("Sign Up");
        switchToRegisterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "registerPanel");
            }
        });

        // Add components to the panel
        panel.add(loginUsernameLabel);
        panel.add(loginUsernameField);
        panel.add(loginPasswordLabel);
        panel.add(loginPasswordField);
        panel.add(loginButton);
        panel.add(switchToRegisterButton);

        return panel;
    }

    // Method to create the register panel
    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        // Register form fields
        JLabel registerUsernameLabel = new JLabel("Username:");
        registerUsernameField = new JTextField();

        JLabel registerPasswordLabel = new JLabel("Password:");
        registerPasswordField = new JPasswordField();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });

        switchToLoginButton = new JButton("Back to Login");
        switchToLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "loginPanel");
            }
        });

        // Add components to the panel
        panel.add(registerUsernameLabel);
        panel.add(registerUsernameField);
        panel.add(registerPasswordLabel);
        panel.add(registerPasswordField);
        panel.add(registerButton);
        panel.add(switchToLoginButton);

        return panel;
    }

    private void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error connecting to database");
            System.exit(1);
        }
    }

    private void login() {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        if (authenticateUser(username, password)) {
            JOptionPane.showMessageDialog(this, "Login successful!");
            ProcessBuilder pb = new ProcessBuilder("javac StreamlitDashboard.java"+ "java -cp . com.example.streamlitdashboard.StreamlitDashboard")
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void register() {
        String username = registerUsernameField.getText();
        String password = new String(registerPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username is already taken");
        } else {
            registerUser(username, password);
            JOptionPane.showMessageDialog(this, "Registration successful! You can now log in.");
            cardLayout.show(mainPanel, "loginPanel");
        }
    }

    private boolean authenticateUser(String username, String password) {
        try {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return false;
        }
    }

    private boolean isUsernameTaken(String username) {
        try {
            String query = "SELECT * FROM users WHERE username = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);

            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking if username is taken: " + e.getMessage());
            return false;
        }
    }

    private void registerUser(String username, String password) {
        try {
            String query = "INSERT INTO users (username, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}
