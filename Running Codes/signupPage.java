package project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignupPage {
    JFrame frame;
    private JTextField tfName;
    private JTextField tfEmail;
    private JTextField tfPhone;
    private JPasswordField tfPassword;
    private JPasswordField tfConfirmPassword;

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/stockmanagement";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignupPage window = new SignupPage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignupPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(Color.WHITE);
        frame.setBounds(100, 100, 750, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblHeader = new JLabel("Sign Up");
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblHeader.setBounds(300, 20, 200, 50);
        frame.getContentPane().add(lblHeader);

        JLabel lblName = new JLabel("Name:");
        lblName.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblName.setBounds(100, 100, 200, 30);
        frame.getContentPane().add(lblName);

        tfName = new JTextField();
        tfName.setBounds(300, 100, 300, 30);
        frame.getContentPane().add(tfName);
        tfName.setColumns(10);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblEmail.setBounds(100, 160, 200, 30);
        frame.getContentPane().add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(300, 160, 300, 30);
        frame.getContentPane().add(tfEmail);
        tfEmail.setColumns(10);

        JLabel lblPhone = new JLabel("Phone:");
        lblPhone.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPhone.setBounds(100, 220, 200, 30);
        frame.getContentPane().add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(300, 220, 300, 30);
        frame.getContentPane().add(tfPhone);
        tfPhone.setColumns(10);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPassword.setBounds(100, 280, 200, 30);
        frame.getContentPane().add(lblPassword);

        tfPassword = new JPasswordField();
        tfPassword.setBounds(300, 280, 300, 30);
        frame.getContentPane().add(tfPassword);

        JLabel lblConfirmPassword = new JLabel("Confirm Password:");
        lblConfirmPassword.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblConfirmPassword.setBounds(100, 340, 200, 30);
        frame.getContentPane().add(lblConfirmPassword);

        tfConfirmPassword = new JPasswordField();
        tfConfirmPassword.setBounds(300, 340, 300, 30);
        frame.getContentPane().add(tfConfirmPassword);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnRegister.setBounds(200, 420, 150, 40);
        frame.getContentPane().add(btnRegister);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnCancel.setBounds(400, 420, 150, 40);
        frame.getContentPane().add(btnCancel);

        // Register button action
        btnRegister.addActionListener(e -> registerUser());

        // Cancel button action
        btnCancel.addActionListener(e -> frame.dispose());
    }

    private void registerUser() {
        String name = tfName.getText();
        String email = tfEmail.getText();
        String phone = tfPhone.getText();
        String password = new String(tfPassword.getPassword());
        String confirmPassword = new String(tfConfirmPassword.getPassword());

        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Register MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Establish connection
            Connection con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "INSERT INTO customer (name, email, phone, password) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, password);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(frame, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();  // Close the signup form
            } else {
                JOptionPane.showMessageDialog(frame, "Error occurred during registration.", "Error", JOptionPane.ERROR_MESSAGE);
            }

            con.close();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
