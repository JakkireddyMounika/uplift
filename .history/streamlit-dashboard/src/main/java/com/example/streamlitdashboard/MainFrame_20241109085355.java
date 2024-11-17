package com.example.streamlitdashboard;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
                try {
                    login();
                } catch (IOException ex) {
                }
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
            String dbUrl = "jdbc:sqlite:C:\\Users\\syeda\\OneDrive\\Desktop\\pas\\Uplift\\streamlit-dashboard\\db\\mydb.db";
            conn = DriverManager.getConnection(dbUrl);
            
            if (conn != null) {
                System.out.println("Connected to SQLite database.");
 // Call table creation here after successful connection
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error connecting to database");
            System.exit(1);
        }
    }
    

    /* Create 'users' table if it doesn't exist
    private void createUsersTable() {
        try {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "username TEXT NOT NULL UNIQUE, "
                    + "password TEXT NOT NULL)";
            try (PreparedStatement pstmt = conn.prepareStatement(createTableSQL)) {
                pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error creating table: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error setting up database table");
            System.exit(1);
        }
    }*/

    private void login() throws IOException {
        String username = loginUsernameField.getText();
        String password = new String(loginPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both username and password");
            return;
        }

        if (authenticateUser(username, password)) {
            // Show login success message and wait for "OK" press
            int response = JOptionPane.showConfirmDialog(
                    this,
                    "Login successful!",
                    "Success",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE
            );
    
            // If the user presses "OK" (response == JOptionPane.OK_OPTION), start the Java process
            if (response == JOptionPane.OK_OPTION) {
                // Run the Java file compilation and execution in a new thread
                new Thread(this::compileAndRunJava).start();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password");
        }
    }

    private void compileAndRunJava() {
        try {
            // Compile the Java file
            ProcessBuilder compilePb = new ProcessBuilder("javac", "StreamlitDashboard.java");
            compilePb.redirectErrorStream(true); // Combine stdout and stderr
            Process compileProcess = compilePb.start();
            
            // Wait for the compilation to finish
            int compileExitCode = compileProcess.waitFor();
            if (compileExitCode != 0) {
                // Compilation failed, read the error output
                String errorOutput = new String(compileProcess.getInputStream().readAllBytes());
                JOptionPane.showMessageDialog(this, "Compilation failed:\n" + errorOutput);
                return;
            }
    
            // Run the compiled Java class
            ProcessBuilder runPb = new ProcessBuilder("java", "-cp", ".", "com.example.streamlitdashboard.StreamlitDashboard");
            runPb.redirectErrorStream(true); // Combine stdout and stderr
            Process runProcess = runPb.start();
            
            // Wait for the execution to finish
            int runExitCode = runProcess.waitFor();
            if (runExitCode != 0) {
                // Execution failed, read the error output
                String errorOutput = new String(runProcess.getInputStream().readAllBytes());
                JOptionPane.showMessageDialog(this, "Execution failed:\n" + errorOutput);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Error compiling or running Java file: " + e.getMessage());
            JOptionPane.showMessageDialog(this, "Error executing Java file");
        }
    }
    private void register() {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();

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
            String query = "SELECT * FROM user WHERE username = ? AND password = ?";
            boolean isAuthenticated;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                try (ResultSet rs = pstmt.executeQuery()) {
                    isAuthenticated = rs.next();
                }
            }
            return isAuthenticated;
        } catch (SQLException e) {
            System.out.println("Error authenticating user: " + e.getMessage());
            return false;
        }
    }

    private boolean isUsernameTaken(String username) {
        try {
            String query = "SELECT * FROM user WHERE username = ?";
            boolean isTaken;
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, username);
                try (ResultSet rs = pstmt.executeQuery()) {
                    isTaken = rs.next();
                }
            }
            return isTaken;
        } catch (SQLException e) {
            System.out.println("Error checking if username is taken: " + e.getMessage());
            return false;
        }
    }

    private void registerUser(String username, String password) {
        try {
            String query = "INSERT INTO user (username, password) VALUES (?, ?)";
          
                PreparedStatement pstmt1 = conn.prepareStatement(query);
                pstmt1.setString(1, username);
                pstmt1.setString(2, password);
                pstmt1.executeUpdate();
            }
        catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }
}


/*C:\Users\syeda\OneDrive\Desktop\pas\Uplift\streamlit-dashboard\src\main\java\com\example\streamlitdashboard
java -cp "bin;lib/sqlite-jdbc-3.46.1.3.jar" com.example.streamlitdashboard.MainFrame */