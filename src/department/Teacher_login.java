package department;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.JEditorPane;

public class Teacher_login extends JFrame {

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
					Teacher_login frame = new Teacher_login();
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
	public Teacher_login() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 920, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(159, 93, 236, 40);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(445, 93, 338, 40);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(159, 179, 236, 31);
		contentPane.add(lblNewLabel_1);

		passwordField = new JPasswordField();
		passwordField.setBounds(445, 172, 338, 38);
		contentPane.add(passwordField);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.setBounds(229, 265, 141, 31);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = new String(passwordField.getPassword());
				if (checkCredentials(username, password)) {
					JOptionPane.showMessageDialog(contentPane, "Login successful!");
					clearFields();
					EventQueue.invokeLater(() -> {
			            try {
			                Teacher_dashboard frame = new Teacher_dashboard();
			                frame.setVisible(true);
			            } catch (Exception ae) {
			                ae.printStackTrace();
			            }
			        });
					
				} else {
					JOptionPane.showMessageDialog(contentPane, "Invalid username or password.");
				}
			}
		});

		JButton btnNewButton_1 = new JButton("Clear");
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1.setBounds(445, 260, 148, 40);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearFields();
			}
		});

		JButton btnNewButton_2 = new JButton("Signup");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_2.setBounds(405, 373, 111, 31);
		contentPane.add(btnNewButton_2);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
		            public void run() {
		                try {
		                    Teacher_signup frame = new Teacher_signup();
		                    frame.setVisible(true);
		                    dispose();
		                } catch (Exception e) {
		                    e.printStackTrace();
		                }
		            }
		        });
			}
		});

		JLabel lblNewLabel_2 = new JLabel("Teacher Login");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblNewLabel_2.setBounds(133, 10, 664, 62);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("New User?");
		lblNewLabel_3.setFont(new Font("Sitka Text", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(306, 373, 89, 31);
		contentPane.add(lblNewLabel_3);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton_1_1.setBounds(659, 260, 148, 40);
		contentPane.add(btnNewButton_1_1);
	}

	private boolean checkCredentials(String username, String password) {
		String dbURL = "jdbc:mysql://localhost:3306/Ideathon_db";
		String user = "root";
		String pass = "Snehal@16";

		try (Connection conn = DriverManager.getConnection(dbURL, user, pass)) {
			String query = "SELECT * FROM Teacher WHERE Username = ? AND Password = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			return rs.next();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	private void clearFields() {
		textField.setText("");
		passwordField.setText("");
	}
}
