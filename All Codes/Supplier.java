package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class Supplier extends JFrame {
    private JPanel contentPane;
    private JTable supplierTable;
    private DefaultTableModel tableModel;

    public Supplier() {
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

        // Fetch initial data
        fetchAllSuppliers();
    }

    private void fetchAllSuppliers() {
        try {
            // Explicit Driver Registration (optional)
            Class.forName("com.mysql.jdbc.Driver");

            // Connect to database
            String url = "jdbc:mysql://localhost:3306/stockmanagement?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
                String query = "SELECT * FROM db";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                // Clear existing data
                tableModel.setRowCount(0);

                // Populate table with data
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
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "MySQL Driver not found: " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error fetching suppliers: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Supplier frame = new Supplier();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
