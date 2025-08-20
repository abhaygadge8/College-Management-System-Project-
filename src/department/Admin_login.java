package department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class Admin_login extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_login frame = new Admin_login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Admin_login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1125, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin Login");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel.setBounds(442, 120, 277, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(360, 222, 98, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(360, 313, 98, 24);
		contentPane.add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(596, 220, 204, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = textField.getText();
				@SuppressWarnings("deprecation")
				String s2 = passwordField.getText();
				
				if (s1.isEmpty() || s2.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Username and Password fields cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    checkDetails(s1, s2);
                    textField.setText("");
    				passwordField.setText("");
                    EventQueue.invokeLater(new Runnable() {
            			public void run() {
            				try {
            					Admin_dashboard frame = new Admin_dashboard();
            					frame.setVisible(true);
            					dispose();
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            			}
            		});
                }
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnNewButton.setBounds(256, 429, 118, 36);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnClear.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnClear.setBounds(501, 429, 118, 36);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 17));
		btnExit.setBounds(753, 429, 118, 36);
		contentPane.add(btnExit);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		passwordField.setBounds(596, 307, 204, 31);
		contentPane.add(passwordField);
		
	}
	private static void checkDetails(String s1, String s2) {
		String dbURL = "jdbc:mysql://localhost:3306/Ideathon_db";
        String user = "root";
        String pass = "root";

        try {
        	boolean isvalid = false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, user, pass);
                 Statement stmt = conn.createStatement()) {
//            	String useDB = "USE Ideathon_db";
//                stmt.execute(useDB);
                
                PreparedStatement pstmt = conn.prepareStatement("select * from  Admin where s1=? AND s2=?");
                pstmt.setString(1,s1);
                pstmt.setString(2,s2);
                ResultSet rs = pstmt.executeQuery();
                
                if(rs.next()) {
                	isvalid = true;
                }
                if(isvalid = true) {
                	JOptionPane.showMessageDialog(null, "Login successfull");
                
                }
                else {
                	JOptionPane.showMessageDialog(null, "Login unsuccessfull");
                }
                
            }}catch (ClassNotFoundException e) {
                System.out.println("JDBC Driver not found");
                e.printStackTrace();
            }  catch (SQLException ex) {
                ex.printStackTrace();
            }
    }
	
}