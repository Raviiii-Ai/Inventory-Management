
package project;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class homePage {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    homePage window = new homePage();
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
    public homePage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.getContentPane().setLayout(null);

        // Label: Inventory Management System
        JLabel lblNewLabel = new JLabel("Inventory Management System");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 25));
        lblNewLabel.setBounds(124, 10, 373, 53);
        frame.getContentPane().add(lblNewLabel);

        // Button: SIGN IN
        JButton btnSignIn = new JButton("SIGN IN");
        btnSignIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for SIGN IN button
                // Opens the LoginPage when the Sign In button is clicked
                try {
                    LoginPage window = new LoginPage();
                    window.frame.setVisible(true);  // Display Login Page
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSignIn.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnSignIn.setBounds(203, 104, 211, 44);
        frame.getContentPane().add(btnSignIn);

        // Button: SIGN UP
        JButton btnSignUp = new JButton("SIGN UP");
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action for SIGN UP button
                // Opens the SignupPage when the Sign Up button is clicked
                try {
                    SignupPage window = new SignupPage();
                    window.frame.setVisible(true);  // Display Signup Page
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnSignUp.setFont(new Font("Segoe UI", Font.BOLD, 24));
        btnSignUp.setBounds(203, 200, 211, 44);
        frame.getContentPane().add(btnSignUp);

        // Frame properties
        frame.setBounds(100, 100, 627, 428);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}