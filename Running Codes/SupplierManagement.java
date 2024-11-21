package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SupplierManagement extends JFrame {
    private JPanel contentPane;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public SupplierManagement() {
        setTitle("Supplier Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        // Content Pane
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title Label
        JLabel titleLabel = new JLabel("Supplier Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBounds(0, 20, 900, 30);
        contentPane.add(titleLabel);

        // Table for displaying suppliers
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
        supplierTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(supplierTable);
        scrollPane.setBounds(50, 150, 800, 350);
        contentPane.add(scrollPane);
        
        JButton btnNewButton = new JButton("Search Supplier");
        btnNewButton.setForeground(Color.WHITE);
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnNewButton.setBackground(new Color(255, 140, 0));
        btnNewButton.setBounds(55, 100, 200, 40);
        contentPane.add(btnNewButton);
        
        JButton btnAddSupplier = new JButton("Add Supplier");
        btnAddSupplier.setForeground(Color.WHITE);
        btnAddSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAddSupplier.setBackground(new Color(50, 205, 50));
        btnAddSupplier.setBounds(353, 100, 200, 40);
        contentPane.add(btnAddSupplier);
        
        JButton btnRemoveSupplier = new JButton("Remove Supplier");
        btnRemoveSupplier.setForeground(Color.WHITE);
        btnRemoveSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRemoveSupplier.setBackground(new Color(255, 69, 0));
        btnRemoveSupplier.setBounds(650, 100, 200, 40);
        contentPane.add(btnRemoveSupplier);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblNewLabel.setBounds(0, 0, 886, 563);
        contentPane.add(lblNewLabel);

        // Add action listeners to buttons
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchSupplier(); // Call search supplier method when clicked
            }
        });

        btnAddSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addSupplier(); // Call add supplier method when clicked
            }
        });

        btnRemoveSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLastSupplier(); // Call remove last supplier method when clicked
            }
        });

        // Fetch initial data
        fetchAllSuppliers();
    }

    private void fetchAllSuppliers() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
                String query = "SELECT * FROM db";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                tableModel.setRowCount(0);

                while (rs.next()) {
                    tableModel.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("phone"),
                            rs.getString("address")
                    });
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching suppliers: " + ex.getMessage());
        }
    }

    private void searchSupplier() {
        String supplierName = JOptionPane.showInputDialog("Enter Supplier Name:");
        if (supplierName != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root")) {
                String query = "SELECT * FROM db WHERE LOWER(name) = LOWER(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, supplierName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String details = "Name: " + rs.getString("name") + "\n"
                            + "Email: " + rs.getString("email") + "\n"
                            + "Phone: " + rs.getString("phone") + "\n"
                            + "Address: " + rs.getString("address");
                    JOptionPane.showMessageDialog(null, details, "Supplier Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Supplier not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error searching supplier: " + ex.getMessage());
            }
        }
    }

    private void addSupplier() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField addressField = new JTextField();

        Object[] fields = {
                "ID:", idField,
                "Name:", nameField,
                "Email:", emailField,
                "Phone:", phoneField,
                "Address:", addressField
        };

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Supplier", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root")) {
                String query = "INSERT INTO db (id, name, email, phone, address) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, idField.getText());
                stmt.setString(2, nameField.getText());
                stmt.setString(3, emailField.getText());
                stmt.setString(4, phoneField.getText());
                stmt.setString(5, addressField.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Supplier added successfully!");
                fetchAllSuppliers();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error adding supplier: " + ex.getMessage());
            }
        }
    }

    private void removeLastSupplier() {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root")) {
            String query = "SELECT id FROM db ORDER BY id DESC LIMIT 1";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                int lastId = rs.getInt("id");

                String deleteQuery = "DELETE FROM db WHERE id = ?";
                PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery);
                deleteStmt.setInt(1, lastId);
                deleteStmt.executeUpdate();

                JOptionPane.showMessageDialog(null, "Last supplier removed successfully!");
                fetchAllSuppliers();
            } else {
                JOptionPane.showMessageDialog(null, "No supplier found to remove!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error removing supplier: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SupplierManagement frame = new SupplierManagement();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
