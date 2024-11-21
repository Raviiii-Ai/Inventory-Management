package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class DistributorManagement extends JFrame {
    private JPanel contentPane;
    private JTable distributorTable;
    private DefaultTableModel tableModel;

    public DistributorManagement() {
        setTitle("Distributor Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 600);

        // Content Pane
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Title Label
        JLabel titleLabel = new JLabel("Distributor Management System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBounds(0, 20, 900, 30);
        contentPane.add(titleLabel);

        // Table for displaying distributors
        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Email", "Phone", "Address"}, 0);
        distributorTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(distributorTable);
        scrollPane.setBounds(50, 150, 800, 350);
        contentPane.add(scrollPane);
        
        JButton btnSearchDistributor = new JButton("Search Distributor");
        btnSearchDistributor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDistributor(); // Search distributor method when clicked
            }
        });
        btnSearchDistributor.setForeground(Color.WHITE);
        btnSearchDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnSearchDistributor.setBackground(new Color(255, 140, 0));
        btnSearchDistributor.setBounds(50, 100, 200, 40);
        contentPane.add(btnSearchDistributor);
        
        JButton btnAddDistributor = new JButton("Add Distributor");
        btnAddDistributor.setForeground(Color.WHITE);
        btnAddDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnAddDistributor.setBackground(new Color(50, 205, 50));
        btnAddDistributor.setBounds(352, 100, 200, 40);
        contentPane.add(btnAddDistributor);
        
        JButton btnRemoveDistributor = new JButton("Remove Distributor");
        btnRemoveDistributor.setForeground(Color.WHITE);
        btnRemoveDistributor.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnRemoveDistributor.setBackground(new Color(255, 69, 0));
        btnRemoveDistributor.setBounds(649, 100, 200, 40);
        contentPane.add(btnRemoveDistributor);
        
        JLabel lblBackground = new JLabel("");
        lblBackground.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblBackground.setBounds(0, 0, 886, 563);
        contentPane.add(lblBackground);

        // Add action listeners for buttons
        btnSearchDistributor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchDistributor(); // Call search distributor method when clicked
            }
        });

        btnAddDistributor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addDistributor(); // Call add distributor method when clicked
            }
        });

        btnRemoveDistributor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeLastDistributor(); // Call remove last distributor method when clicked
            }
        });

        // Fetch initial data
        fetchAllDistributors();
    }

    private void fetchAllDistributors() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
                String query = "SELECT * FROM db"; // Assuming table is named 'distributors'
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
            JOptionPane.showMessageDialog(null, "Error fetching distributors: " + ex.getMessage());
        }
    }

    private void searchDistributor() {
        String distributorName = JOptionPane.showInputDialog("Enter Distributor Name:");
        if (distributorName != null) {
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "root")) {
                String query = "SELECT * FROM db WHERE LOWER(name) = LOWER(?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, distributorName);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    String details = "Name: " + rs.getString("name") + "\n"
                            + "Email: " + rs.getString("email") + "\n"
                            + "Phone: " + rs.getString("phone") + "\n"
                            + "Address: " + rs.getString("address");
                    JOptionPane.showMessageDialog(null, details, "Distributor Details", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Distributor not found!");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error searching distributor: " + ex.getMessage());
            }
        }
    }

    private void addDistributor() {
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

        int option = JOptionPane.showConfirmDialog(null, fields, "Add Distributor", JOptionPane.OK_CANCEL_OPTION);
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
                JOptionPane.showMessageDialog(null, "Distributor added successfully!");
                fetchAllDistributors();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error adding distributor: " + ex.getMessage());
            }
        }
    }

    private void removeLastDistributor() {
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

                JOptionPane.showMessageDialog(null, "Last distributor removed successfully!");
                fetchAllDistributors();
            } else {
                JOptionPane.showMessageDialog(null, "No distributor found to remove!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error removing distributor: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DistributorManagement frame = new DistributorManagement();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
