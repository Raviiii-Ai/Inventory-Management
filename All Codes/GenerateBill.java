package project; 

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class GenerateBill {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GenerateBill window = new GenerateBill();
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
	public GenerateBill() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1094, 729);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1080, 692);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 441, 692);
		panel_1.setBackground(Color.GRAY);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(SystemColor.activeCaption);
		panel_3.setBounds(70, 164, 240, 49);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblBillingPage = new JLabel("BILLING PAGE");
		lblBillingPage.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblBillingPage.setBounds(34, 10, 196, 39);
		panel_3.add(lblBillingPage);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\bill.png"));
		lblNewLabel_1.setBounds(54, 153, 268, 406);
		panel_1.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		textField.setBounds(917, 26, 119, 19);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("BILLER ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblNewLabel.setBounds(845, 29, 62, 13);
		panel.add(lblNewLabel);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(255, 255, 153));
		panel_3_1.setBounds(644, 82, 240, 41);
		panel.add(panel_3_1);
		
		JLabel lblGenerateInvoice = new JLabel("GENERATE INVOICE");
		lblGenerateInvoice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGenerateInvoice.setBounds(23, 0, 196, 39);
		panel_3_1.add(lblGenerateInvoice);
		
		JLabel lblNewLabel_2 = new JLabel("SKU ID: ");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(476, 189, 119, 35);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_2_1 = new JLabel("DEPARTEMENT:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_1.setBounds(476, 252, 139, 35);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblNewLabel_2_2 = new JLabel("BATCH NO.");
		lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_2.setBounds(476, 317, 119, 35);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_3 = new JLabel("QUANTITY:");
		lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_3.setBounds(476, 380, 119, 35);
		panel.add(lblNewLabel_2_3);
		
		JLabel lblNewLabel_2_4 = new JLabel("SUPPLIER: ");
		lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2_4.setBounds(476, 446, 119, 35);
		panel.add(lblNewLabel_2_4);
		
		textField_1 = new JTextField();
		textField_1.setBackground(UIManager.getColor("ToolTip.background"));
		textField_1.setBounds(644, 191, 228, 35);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("TECHNICAL");
		rdbtnNewRadioButton.setBounds(644, 261, 103, 21);
		panel.add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("LOGISTICS");
		rdbtnNewRadioButton_1.setBounds(781, 261, 103, 21);
		panel.add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("TRANSPORTATION");
		rdbtnNewRadioButton_2.setBounds(898, 261, 157, 21);
		panel.add(rdbtnNewRadioButton_2);
		
		textField_2 = new JTextField();
		textField_2.setBackground(UIManager.getColor("ToolTip.background"));
		textField_2.setColumns(10);
		textField_2.setBounds(644, 317, 228, 35);
		panel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setBackground(UIManager.getColor("ToolTip.background"));
		textField_3.setColumns(10);
		textField_3.setBounds(644, 380, 228, 35);
		panel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setBackground(UIManager.getColor("ToolTip.background"));
		textField_4.setColumns(10);
		textField_4.setBounds(644, 446, 228, 35);
		panel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBackground(UIManager.getColor("CheckBoxMenuItem.selectionBackground"));
		textField_5.setBounds(577, 26, 119, 19);
		panel.add(textField_5);
		
		JLabel lblDate = new JLabel("DATE & TIME: ");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 10));
		lblDate.setBounds(476, 29, 91, 13);
		panel.add(lblDate);
		
		JButton btnNewButton = new JButton("GENERATE");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(644, 535, 132, 41);
		panel.add(btnNewButton);
		
		JButton btnClear = new JButton("CLEAR");
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnClear.setBounds(826, 535, 132, 41);
		panel.add(btnClear);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\Risha\\Downloads\\field-1728099_1280.jpg"));
		lblNewLabel_3.setBounds(442, 0, 638, 692);
		panel.add(lblNewLabel_3);
	}
}
