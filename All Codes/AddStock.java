package project;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddStock {

    JFrame frame;
    private JTable table;
    private JTextField textFieldSKU;
    private JTextField textFieldBatchNumber;
    private JTextField textFieldQuantity;
    private DefaultTableModel model;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AddStock window = new AddStock();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public AddStock() {
        initialize();
        showAllProducts();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1087, 694);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 1073, 657);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JPanel panelHeader = new JPanel();
        panelHeader.setBackground(SystemColor.info);
        panelHeader.setBounds(45, 39, 169, 61);
        panel.add(panelHeader);
        panelHeader.setLayout(null);

        JLabel lblNewLabel = new JLabel("UPDATE STOCK");
        lblNewLabel.setFont(new Font("Wide Latin", Font.PLAIN, 10));
        lblNewLabel.setBounds(0, 0, 169, 61);
        panelHeader.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(578, 152, 469, 249);
        panel.add(scrollPane);

        // Set up the JTable and its model
        table = new JTable();
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new Object[]{"SKU", "BatchNumber", "ProductName", "StockRemaining"});
        table.setModel(model);
        scrollPane.setViewportView(table);

        JLabel lblSKU = new JLabel("SKU: ");
        lblSKU.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        lblSKU.setBounds(45, 190, 64, 50);
        panel.add(lblSKU);

        JLabel lblBatchNumber = new JLabel("BATCH NO: ");
        lblBatchNumber.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        lblBatchNumber.setBounds(45, 250, 129, 50);
        panel.add(lblBatchNumber);

        JLabel lblQuantity = new JLabel("QUANTITY: ");
        lblQuantity.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
        lblQuantity.setBounds(45, 310, 108, 50);
        panel.add(lblQuantity);

        textFieldSKU = new JTextField();
        textFieldSKU.setBackground(SystemColor.info);
        textFieldSKU.setBounds(168, 199, 187, 38);
        panel.add(textFieldSKU);
        textFieldSKU.setColumns(10);

        textFieldBatchNumber = new JTextField();
        textFieldBatchNumber.setBackground(SystemColor.info);
        textFieldBatchNumber.setColumns(10);
        textFieldBatchNumber.setBounds(168, 262, 187, 38);
        panel.add(textFieldBatchNumber);

        textFieldQuantity = new JTextField();
        textFieldQuantity.setBackground(SystemColor.info);
        textFieldQuantity.setColumns(10);
        textFieldQuantity.setBounds(168, 322, 187, 38);
        panel.add(textFieldQuantity);

        // Sold Button - Subtract from stock
        JButton btnSold = new JButton("SOLD");
        btnSold.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnSold.setBounds(45, 396, 83, 38);
        btnSold.addActionListener(e -> sellProduct());
        panel.add(btnSold);

        // Order Button - Add to stock
        JButton btnOrder = new JButton("ORDER");
        btnOrder.setFont(new Font("Tahoma", Font.BOLD, 10));
        btnOrder.setBounds(178, 396, 83, 38);
        btnOrder.addActionListener(e -> orderProduct());
        panel.add(btnOrder);
        
        textField = new JTextField();
        textField.setBounds(839, 63, 144, 19);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblNewLabel_1.setBounds(0, 0, 1073, 657);
        panel.add(lblNewLabel_1);
    }

    /**
     * Show all products in the JTable.
     */
    private void showAllProducts() {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Products";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            // Clear the table model
            model.setRowCount(0);

            // Add each row from the result set to the table model
            while (rs.next()) {
                Object[] row = { rs.getString("SKU"), rs.getString("BatchNumber"), rs.getString("ProductName"), rs.getInt("StockRemaining") };
                model.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching data from database!");
        }
    }

    /**
     * Order product and add to the stock in the database.
     */
    private void orderProduct() {
        String sku = textFieldSKU.getText();
        String batchNumber = textFieldBatchNumber.getText();
        int quantityOrdered;

        try {
            quantityOrdered = Integer.parseInt(textFieldQuantity.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity!");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String queryCheck = "SELECT StockRemaining FROM Products WHERE SKU = ? AND BatchNumber = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(queryCheck);
            stmtCheck.setString(1, sku);
            stmtCheck.setString(2, batchNumber);
            ResultSet rs = stmtCheck.executeQuery();

            if (rs.next()) {
                int currentStock = rs.getInt("StockRemaining");
                int updatedStock = currentStock + quantityOrdered;
                

                String queryUpdate = "UPDATE Products SET StockRemaining = ? WHERE SKU = ? AND BatchNumber = ?";
                PreparedStatement stmtUpdate = conn.prepareStatement(queryUpdate);
                stmtUpdate.setInt(1, updatedStock);
                stmtUpdate.setString(2, sku);
                stmtUpdate.setString(3, batchNumber);
                stmtUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Stock ordered successfully!");
                if (updatedStock < 50) {
                	JOptionPane.showInternalMessageDialog(null, "Stock is Less than the Specified limit recommend to Place new order!");
                }
                showAllProducts();
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in the inventory!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating stock!");
        }
    }

    /**
     * Sell product and subtract from the stock in the database.
     */
    private void sellProduct() {
        String sku = textFieldSKU.getText();
        String batchNumber = textFieldBatchNumber.getText();
        int quantitySold;

        try {
            quantitySold = Integer.parseInt(textFieldQuantity.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter a valid quantity!");
            return;
        }

        try (Connection conn = DBConnection.getConnection()) {
            String queryCheck = "SELECT StockRemaining FROM Products WHERE SKU = ? AND BatchNumber = ?";
            PreparedStatement stmtCheck = conn.prepareStatement(queryCheck);
            stmtCheck.setString(1, sku);
            stmtCheck.setString(2, batchNumber);
            ResultSet rs = stmtCheck.executeQuery();

            if (rs.next()) {
                int currentStock = rs.getInt("StockRemaining");
                int updatedStock = currentStock - quantitySold;
                
                
                if (updatedStock < 0) {
                    JOptionPane.showMessageDialog(null, "Not enough stock available!");
                    return;
                }

                String queryUpdate = "UPDATE Products SET StockRemaining = ? WHERE SKU = ? AND BatchNumber = ?";
                PreparedStatement stmtUpdate = conn.prepareStatement(queryUpdate);
                stmtUpdate.setInt(1, updatedStock);
                stmtUpdate.setString(2, sku);
                stmtUpdate.setString(3, batchNumber);
                stmtUpdate.executeUpdate();

                JOptionPane.showMessageDialog(null, "Stock sold successfully!");
                if (updatedStock < 50) {
                	JOptionPane.showInternalMessageDialog(null, "Stock is Less than the Specified limit recommend to Place new order!");
                }
                showAllProducts();
            } else {
                JOptionPane.showMessageDialog(null, "Product not found in the inventory!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating stock!");
        }
    }
}
