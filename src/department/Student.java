package department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Student extends JFrame {

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
					Student frame = new Student();
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
	public Student() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1033, 567);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Roll number :");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblNewLabel.setBounds(340, 120, 135, 46);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblPassword.setBounds(340, 231, 135, 46);
		contentPane.add(lblPassword);
		
		textField = new JTextField();
		textField.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField.setBounds(573, 121, 156, 46);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(573, 221, 156, 46);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = textField.getText();
				String s2 = passwordField.getText();
				
				if (s1.isEmpty() || s2.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Roll no and Password fields cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    checkDetails(s1, s2);
                }				EventQueue.invokeLater(() -> {
		        try {
		                Student_dashboard frame = new Student_dashboard(s1);
		                frame.setVisible(true);
		                dispose();
		            } catch (Exception ae) {
		                ae.printStackTrace();
		            }
		        });
			}
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnNewButton.setBounds(272, 363, 108, 46);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnClear.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnClear.setBounds(504, 363, 108, 46);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnExit.setBounds(713, 363, 108, 46);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel_1 = new JLabel("New User ?");
		lblNewLabel_1.setFont(new Font("Sitka Text", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(465, 466, 86, 25);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							Student_signup frame = new Student_signup();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnNewButton_1.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		btnNewButton_1.setBounds(573, 469, 85, 21);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_2 = new JLabel("Student Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel_2.setBounds(400, 20, 237, 46);
		contentPane.add(lblNewLabel_2);
	}
	
	private static void checkDetails(String s1, String s2) {
		String dbURL = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "Snehal@16";

        try {
        	boolean isvalid = false;
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, user, pass);
                 Statement stmt = conn.createStatement()) {
            	String useDB = "USE Ideathon_db";
                stmt.execute(useDB);
                
                PreparedStatement pstmt = conn.prepareStatement("select * from  Student where Roll_no=? AND password =?");
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
