package project;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Window;

public class Dash {

    JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dash window = new Dash();
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
    public Dash() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1103, 719);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GRAY);
        panel.setBounds(0, 0, 1157, 756);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        // BUTTON FOR COMPLAINT MANAGEMENT
        JButton btnComplaint = new JButton("COMPLAINT");
        btnComplaint.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnComplaint.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Launch the ComplaintPage when clicked
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            ComplaintPage complaintPage = new ComplaintPage();
                            ((Window) complaintPage.frame).setVisible(true); // Open the ComplaintPage frame
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        
        JButton btnProduct = new JButton("PRODUCT");
        btnProduct.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnProduct.setBounds(761, 362, 140, 54);
        panel.add(btnProduct);
        btnComplaint.setBounds(761, 453, 140, 54);
        panel.add(btnComplaint);

        // BUTTON FOR SUPPLIER MANAGEMENT
        JButton btnSupplier = new JButton("SUPPLIER");
        btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSupplier.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the SupplierManagement frame
                SupplierManagement supplierManagementFrame = new SupplierManagement();
                supplierManagementFrame.setVisible(true);
            }
        });
        btnSupplier.setBounds(761, 538, 140, 54);
        panel.add(btnSupplier);

        // BUTTON FOR DISTRIBUTOR MANAGEMENT
        JButton btnDistributor = new JButton("DISTRIBUTOR");
        btnDistributor.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDistributor.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create and display the DistributorManagement frame
                DistributorManagement distributorManagement = new DistributorManagement();
                distributorManagement.setVisible(true);
            }
        });
        btnDistributor.setBounds(761, 613, 140, 54);
        panel.add(btnDistributor);

        // BUTTON FOR STOCK UPDATE
        JButton btnStockUpdate = new JButton("STOCK UPDATE");
        btnStockUpdate.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStockUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the AddStock page
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            AddStock window = new AddStock();
                            window.frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        btnStockUpdate.setBounds(761, 265, 140, 54);
        panel.add(btnStockUpdate);

        // BUTTON FOR STOCK STATUS
        JButton btnStockStatus = new JButton("STOCK STATUS");
        btnStockStatus.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnStockStatus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the RemainingStock page
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        try {
                            RemainingStock window = new RemainingStock();
                            window.frame.setVisible(true);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        btnStockStatus.setBounds(761, 174, 140, 54);
        panel.add(btnStockStatus);

        // DASHBOARD TITLE LABEL
        JLabel lblDashboard = new JLabel("Dashboard");
        lblDashboard.setFont(new Font("Verdana", Font.BOLD, 40));
        lblDashboard.setBounds(347, 26, 258, 58);
        panel.add(lblDashboard);

        // IMAGE ICON FOR DASHBOARD
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\inventory.png"));
        lblNewLabel.setBounds(35, 138, 292, 369);
        panel.add(lblNewLabel);

        // BACKGROUND IMAGE
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
        lblNewLabel_1.setBounds(0, 0, 1089, 681);
        panel.add(lblNewLabel_1);

        // SIDE PANEL WITH INVENTORY MANAGEMENT TITLE
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(SystemColor.info);
        panel_1.setBounds(732, 59, 194, 64);
        panel.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblWelcomeToInventory_1 = new JLabel("DASHBOARD");
        lblWelcomeToInventory_1.setBounds(10, 10, 174, 45);
        panel_1.add(lblWelcomeToInventory_1);
        lblWelcomeToInventory_1.setForeground(SystemColor.textHighlight);
        lblWelcomeToInventory_1.setFont(new Font("Segoe UI Black", Font.BOLD, 23));

        JLabel lblWelcomeToInventory = new JLabel("INVENTORY MANAGEMENT");
        lblWelcomeToInventory.setForeground(SystemColor.textHighlight);
        lblWelcomeToInventory.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
        lblWelcomeToInventory.setBounds(25, 144, 260, 37);
        panel.add(lblWelcomeToInventory);
    }
}
