package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ProductPage {

    JFrame frame;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&serverTimezone=UTC"; 
    private static final String USER = "root"; 
    private static final String PASSWORD ="root"; 

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ProductPage window = new ProductPage();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ProductPage() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1051, 637);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 1037, 600);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\material-management (1).png"));
        lblNewLabel.setBounds(42, 163, 292, 301);
        panel.add(lblNewLabel);

        JPanel panel_2 = new JPanel();
        panel_2.setBackground(SystemColor.textHighlightText);
        panel_2.setBounds(368, 0, 693, 600);
        panel.add(panel_2);
        panel_2.setLayout(null);

        JLabel lblNewLabel_2 = new JLabel("PRODUCT ID: ");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2.setBounds(21, 67, 157, 31);
        panel_2.add(lblNewLabel_2);

        textField = new JTextField();
        textField.setBackground(SystemColor.info);
        textField.setBounds(259, 67, 226, 31);
        panel_2.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_2_1 = new JLabel("PRODUCT NAME: ");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_1.setBounds(21, 137, 157, 31);
        panel_2.add(lblNewLabel_2_1);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBackground(SystemColor.info);
        textField_1.setBounds(259, 137, 226, 31);
        panel_2.add(textField_1);

        JLabel lblNewLabel_2_2 = new JLabel("SKU:");
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_2.setBounds(21, 208, 157, 31);
        panel_2.add(lblNewLabel_2_2);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBackground(SystemColor.info);
        textField_2.setBounds(259, 208, 226, 31);
        panel_2.add(textField_2);

        JLabel lblNewLabel_2_3 = new JLabel("BATCH NUMBER:");
        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_3.setBounds(21, 273, 157, 31);
        panel_2.add(lblNewLabel_2_3);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBackground(SystemColor.info);
        textField_3.setBounds(259, 273, 226, 31);
        panel_2.add(textField_3);

        JLabel lblNewLabel_2_4 = new JLabel("SUPPLIER: ");
        lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel_2_4.setBounds(21, 345, 157, 31);
        panel_2.add(lblNewLabel_2_4);

        textField_4 = new JTextField();
        textField_4.setColumns(10);
        textField_4.setBackground(SystemColor.info);
        textField_4.setBounds(259, 345, 226, 31);
        panel_2.add(textField_4);

        JButton btnAdd = new JButton("ADD");
        btnAdd.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnAdd.setBounds(259, 433, 147, 51);
        btnAdd.addActionListener(e -> addProductToDatabase());
        panel_2.add(btnAdd);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnDelete.setBounds(259, 502, 147, 51);
        btnDelete.addActionListener(e -> deleteProductFromDatabase());
        panel_2.add(btnDelete);
        
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblNewLabel_3.setBounds(0, 0, 666, 600);
        panel_2.add(lblNewLabel_3);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(SystemColor.activeCaption);
        panel_1.setBounds(24, 99, 225, 54);
        panel.add(panel_1);

        JLabel lblNewLabel_1 = new JLabel("PRODUCT ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel_1.setBounds(52, 0, 132, 54);
        panel_1.add(lblNewLabel_1);
    }

    // Method to add product to the database
    private void addProductToDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "INSERT INTO Products (ProductID, ProductName, SKU, BatchNumber, Supplier) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, textField.getText());
            stmt.setString(2, textField_1.getText());
            stmt.setString(3, textField_2.getText());
            stmt.setString(4, textField_3.getText());
            stmt.setString(5, textField_4.getText());

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
//                System.out.println("Product added successfully!");
            	JOptionPane.showMessageDialog(null, "Product added successfully!");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to delete product from the database
    private void deleteProductFromDatabase() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            String sql = "DELETE FROM Products WHERE ProductID = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, textField.getText());

            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
//                System.out.println("Product deleted successfully!");
            	JOptionPane.showMessageDialog(null, "Product DELETED successfully!");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
