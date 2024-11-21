package project;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.SystemColor;

public class RemainingStock {

    JFrame frame;
    private JTextField textField;        // Additional input field if needed
    private JTextField textField_1;      // SKU input field
    private JTextField textField_2;      // Batch Number field to display result
    private JTable table;
    private JTextField textField_3;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    RemainingStock window = new RemainingStock();
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
    public RemainingStock() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1063, 620);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 1049, 583);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("SKU: ");
        lblNewLabel_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        lblNewLabel_1.setBounds(75, 270, 71, 50);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Batch Number: ");
        lblNewLabel_1_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        lblNewLabel_1_1.setBounds(75, 342, 155, 35);
        panel.add(lblNewLabel_1_1);

        textField_1 = new JTextField(); // SKU input field
        textField_1.setBackground(SystemColor.info);
        textField_1.setBounds(252, 260, 226, 50);
        panel.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField(); // Batch Number display field
        textField_2.setBackground(SystemColor.info);
        textField_2.setColumns(10);
        textField_2.setBounds(252, 327, 226, 50);
        panel.add(textField_2);

        JButton btnNewButton = new JButton("SEARCH");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sku = textField_1.getText(); // Get SKU from textField_1

                // Connect to database and retrieve product details
                try (Connection conn = DBConnection.getConnection()) {
                    String query = "SELECT ProductName, BatchNumber, StockRemaining FROM Products WHERE SKU = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, sku);
                    ResultSet rs = stmt.executeQuery();

                    if (rs.next()) {
                        // Retrieve data from the result set
                        String productName = rs.getString("ProductName");
                        String batchNumber = rs.getString("BatchNumber");
                        int stockRemaining = rs.getInt("StockRemaining");

                        // Display data in text fields
                        textField_1.setText(productName); // Product Name
                        textField_2.setText(batchNumber); // Batch Number
                        JOptionPane.showMessageDialog(null, "Stock Remaining: " + stockRemaining);
                    } else {
                        JOptionPane.showMessageDialog(null, "Product not found!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error connecting to database!");
                }
            }
        });
        btnNewButton.setBounds(75, 435, 106, 35);
        panel.add(btnNewButton);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(528, 204, 498, 232);
        panel.add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(UIManager.getColor("ToolTip.background"));
        panel_1.setBounds(44, 45, 186, 50);
        panel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("STOCK STATUS");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblNewLabel.setBounds(22, 10, 141, 30);
        panel_1.add(lblNewLabel);
        
        textField_3 = new JTextField();
        textField_3.setBounds(832, 56, 168, 27);
        panel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblNewLabel_2.setBounds(0, 0, 1049, 583);
        panel.add(lblNewLabel_2);

        // Load the table with all products by default
        showAllProducts();
    }

    // Method to display all products in JTable
    private void showAllProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT SKU, ProductName, BatchNumber, StockRemaining FROM Products";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Table model to display data
            DefaultTableModel model = new DefaultTableModel(new String[]{"SKU", "Product Name", "Batch Number", "Stock Remaining"}, 0);

            // Add rows to the model from the ResultSet
            while (rs.next()) {
                String sku = rs.getString("SKU");
                String productName = rs.getString("ProductName");
                String batchNumber = rs.getString("BatchNumber");
                int stockRemaining = rs.getInt("StockRemaining");
                model.addRow(new Object[]{sku, productName, batchNumber, stockRemaining});
            }

            // Set the model to the table
            table.setModel(model);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database!");
        }
    }
}
