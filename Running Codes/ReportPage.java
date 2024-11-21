package project;

import java.awt.*;
import java.sql.*;
import java.util.Random;
import javax.swing.*;

public class ReportPage {

    JFrame frame;
    private JTextField textFieldDepartmentTrack;
    private JTextField textFieldSkuId;
    private JTextField textFieldIssueType;
    private JTextField textFieldDescription;
    private JTextField textFieldDepartmentRegister;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ReportPage window = new ReportPage();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Create the application.
     */
    public ReportPage() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 860, 629);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setBounds(0, 0, 846, 591);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // Left panel with image
        JPanel panelLeft = new JPanel();
        panelLeft.setBackground(Color.GRAY);
        panelLeft.setBounds(0, 0, 375, 591);
        panel.add(panelLeft);
        panelLeft.setLayout(null);

        JLabel lblImage = new JLabel("");
        lblImage.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\complain.png"));
        lblImage.setBounds(42, 130, 296, 436);
        panelLeft.add(lblImage);

        JPanel panelLeftHeader = new JPanel();
        panelLeftHeader.setLayout(null);
        panelLeftHeader.setBackground(SystemColor.activeCaption);
        panelLeftHeader.setBounds(32, 98, 269, 49);
        panelLeft.add(panelLeftHeader);

        JLabel lblComplaintPage = new JLabel("COMPLAINT PAGE");
        lblComplaintPage.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblComplaintPage.setBounds(60, 0, 157, 49);
        panelLeftHeader.add(lblComplaintPage);

        // Right panel header
        JPanel panelTrackHeader = new JPanel();
        panelTrackHeader.setLayout(null);
        panelTrackHeader.setBackground(Color.YELLOW);
        panelTrackHeader.setBounds(485, 65, 215, 49);
        panel.add(panelTrackHeader);

        JLabel lblTrack = new JLabel("TRACK");
        lblTrack.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblTrack.setBounds(73, 0, 75, 49);
        panelTrackHeader.add(lblTrack);

        JLabel lblDepartmentTrack = new JLabel("Tracking ID:");
        lblDepartmentTrack.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentTrack.setBounds(460, 143, 113, 26);
        panel.add(lblDepartmentTrack);

        textFieldDepartmentTrack = createTextField(578, 145, 180, 26);
        panel.add(textFieldDepartmentTrack);

        JButton btnTrack = new JButton("Track");
        btnTrack.setBounds(578, 191, 85, 21);
        panel.add(btnTrack);

        // Right panel register header
        JPanel panelRegisterHeader = new JPanel();
        panelRegisterHeader.setLayout(null);
        panelRegisterHeader.setBackground(Color.YELLOW);
        panelRegisterHeader.setBounds(482, 237, 215, 49);
        panel.add(panelRegisterHeader);

        JLabel lblRegister = new JLabel("REGISTER");
        lblRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblRegister.setBounds(50, 0, 105, 49);
        panelRegisterHeader.add(lblRegister);

        JLabel lblSkuId = new JLabel("SKU ID:");
        lblSkuId.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSkuId.setBounds(460, 315, 65, 26);
        panel.add(lblSkuId);

        textFieldSkuId = createTextField(578, 317, 180, 26);
        panel.add(textFieldSkuId);

        JLabel lblIssueType = new JLabel("TYPE OF ISSUE:");
        lblIssueType.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblIssueType.setBounds(460, 361, 108, 26);
        panel.add(lblIssueType);

        textFieldIssueType = createTextField(578, 361, 180, 26);
        panel.add(textFieldIssueType);

        JLabel lblDescription = new JLabel("DESCRIPTION:");
        lblDescription.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDescription.setBounds(460, 409, 101, 26);
        panel.add(lblDescription);

        textFieldDescription = createTextField(578, 409, 180, 26);
        panel.add(textFieldDescription);

        JLabel lblDepartmentRegister = new JLabel("DEPARTMENT:");
        lblDepartmentRegister.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblDepartmentRegister.setBounds(460, 455, 121, 26);
        panel.add(lblDepartmentRegister);

        textFieldDepartmentRegister = createTextField(578, 455, 180, 26);
        panel.add(textFieldDepartmentRegister);

        JButton btnRegister = new JButton("REGISTER");
        btnRegister.setBounds(578, 520, 101, 21);
        panel.add(btnRegister);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\1000_F_864184956_rSc03GacaylIMR7RTQtc5T5eVhRSnfLP.jpg"));
        lblNewLabel.setBounds(375, 0, 471, 591);
        panel.add(lblNewLabel);

        // Button Actions
        btnTrack.addActionListener(e -> {
            String department = textFieldDepartmentTrack.getText();
            String result = trackComplaint(department);
            JOptionPane.showMessageDialog(frame, result);
        });

        btnRegister.addActionListener(e -> {
            String skuId = textFieldSkuId.getText();
            String issueType = textFieldIssueType.getText();
            String description = textFieldDescription.getText();
            String department = textFieldDepartmentRegister.getText();

            // Register complaint and display tracking ID
            String trackingId = registerComplaint(skuId, issueType, description, department);
            if (trackingId != null) {
                JOptionPane.showMessageDialog(frame, "Complaint Registered Successfully!\nTracking ID: " + trackingId);
            } else {
                JOptionPane.showMessageDialog(frame, "Failed to Register Complaint.");
            }
        });
    }

    /**
     * Helper method to create text fields.
     */
    private JTextField createTextField(int x, int y, int width, int height) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setBackground(SystemColor.info);
        textField.setColumns(10);
        return textField;
    }

    /**
     * Establish a connection to the MySQL database.
     */
    private Connection connectToDatabase() {
        String url = "jdbc:mysql://localhost:3306/stockmanagement";
        String user = "root";
        String password = "root";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Database connection failed.");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Generate a random alphanumeric string for Tracking ID.
     */
    private String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++) {
            result.append(characters.charAt(random.nextInt(characters.length())));
        }
        return result.toString();
    }

    /**
     * Register a complaint in the database and return the generated Tracking ID.
     */
    private String registerComplaint(String skuId, String issueType, String description, String department) {
        String trackingId = generateRandomString(10);  // Generate the tracking ID
        String query = "INSERT INTO issuetracker (SKU_ID, TYPE_OF_ISSUE, DESCRIPTION, DEPARTMENT, TrackingId) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = connectToDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, skuId);
            ps.setString(2, issueType);
            ps.setString(3, description);
            ps.setString(4, department);
            ps.setString(5, trackingId);

            int result = ps.executeUpdate();
            return result > 0 ? trackingId : null;  // Return the tracking ID if successful, else null
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Track complaints by department.
     */
    private String trackComplaint(String department) {
        String query = "SELECT * FROM issuetracker WHERE TrackingID = ?";
        try (Connection conn = connectToDatabase();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, department);
            ResultSet rs = ps.executeQuery();
            StringBuilder results = new StringBuilder();

            while (rs.next()) {
                results.append("Tracking ID: ").append(rs.getString("TrackingId"))
                        .append(", SKU ID: ").append(rs.getString("sku_id"))
                        .append(", Issue: ").append(rs.getString("TYPE_OF_ISSUE"))
                        .append("\n");
            }
            return results.toString().isEmpty() ? "No complaints found for this department." : results.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error tracking complaint.";
        }
    }
}
