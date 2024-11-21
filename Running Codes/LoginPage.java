package project;

import java.awt.EventQueue;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class LoginPage {

    JFrame frame;
    private JTextField textFieldEmail;
    private JPasswordField passwordField;
    private JButton btnOk;
    private JButton btnCancel;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginPage window = new LoginPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public LoginPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 587, 640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel("Sign In Page");
        lblTitle.setForeground(new Color(0, 0, 0));
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitle.setBounds(109, 62, 443, 57);
        frame.getContentPane().add(lblTitle);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setForeground(new Color(0, 0, 0));
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEmail.setBounds(109, 191, 107, 24);
        frame.getContentPane().add(lblEmail);

        textFieldEmail = new JTextField();
        textFieldEmail.setBounds(109, 237, 336, 24);
        frame.getContentPane().add(textFieldEmail);
        textFieldEmail.setColumns(10);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setBounds(109, 345, 107, 24);
        frame.getContentPane().add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(109, 379, 336, 24);
        frame.getContentPane().add(passwordField);

        btnOk = new JButton("OK");
        btnOk.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnOk.setBounds(109, 483, 336, 29);
        frame.getContentPane().add(btnOk);

        btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancel.setBounds(109, 543, 336, 29);
        frame.getContentPane().add(btnCancel);

        // Action listener for the OK button
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = textFieldEmail.getText();
                String password = String.valueOf(passwordField.getPassword());

                User user = getAuthenticatedUser(email, password);

                if (user != null) {
                    JOptionPane.showMessageDialog(frame,
                            "Welcome, " + user.name + "!",
                            "Login Successful",
                            JOptionPane.INFORMATION_MESSAGE);

                    // Open Dash page and close the LoginPage
                    frame.dispose(); // Close LoginPage
                    Dash dashWindow = new Dash();
                    dashWindow.frame.setVisible(true); // Show Dash page
                } else {
                    JOptionPane.showMessageDialog(frame,
                            "Invalid email or password.",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Action listener for the Cancel button
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    /**
     * Authenticate user against the database.
     */
    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        final String DB_URL = "jdbc:mysql://localhost:3306/stockmanagement";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection to the database
            Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);

            String sql = "SELECT * FROM customer WHERE email = ? AND password = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.password = resultSet.getString("password");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }
}

/**
 * User class to hold user information.
 */
class User {
    String name;
    String email;
    String phone;
    String password;
}
