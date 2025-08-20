package department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import com.toedter.calendar.JDateChooser;



public class Student_signup extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblClass;
	private JLabel lblPassword;
	private JLabel lblEmail;
	private JLabel lblDateOfBirth;
	private JLabel lblGender;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JComboBox comboBox;
	private JComboBox comboBox_1;
	private JDateChooser dateChooser;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student_signup frame = new Student_signup();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public Student_signup() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 468, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField.setBounds(224, 24, 167, 40);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(224, 79, 167, 40);
		contentPane.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(224, 139, 167, 40);
		contentPane.add(textField_2);
		
		JButton btnNewButton = new JButton("Sign up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s1 = textField.getText();
				String s2 = textField_1.getText();
				String s3 = textField_2.getText();
				String s4 = (String) comboBox_1.getSelectedItem();
				String s5 = textField_3.getText();
				Date s6 = dateChooser.getDate();
				String s7 = (String) comboBox.getSelectedItem();
				String s8 = passwordField.getText();
				if (!s1.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && !s4.isEmpty() && !s5.isEmpty()) {
	                saveDetails(s1,s2,s3,s4,s5,s6,s7,s8);
	                JOptionPane.showMessageDialog(contentPane, "Details saved");
	                textField.setText("");
					textField_1.setText("");
					textField_2.setText("");
					comboBox_1.setSelectedIndex(0);
					textField_3.setText("");
					dateChooser.setDate(new Date());
					comboBox.setSelectedIndex(0);
					passwordField.setText("");
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Student frame = new Student();
								frame.setVisible(true);
								dispose();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
	            } else {
	                JOptionPane.showMessageDialog(contentPane, "Enter a valid details.");
	            }
			}
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnNewButton.setBounds(22, 458, 110, 40);
		contentPane.add(btnNewButton);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				comboBox_1.setSelectedIndex(0);
				textField_3.setText("");
				dateChooser.setDate(new Date());
				comboBox.setSelectedIndex(0);
				passwordField.setText("");
			}
		});
		btnClear.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnClear.setBounds(170, 458, 110, 40);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		btnExit.setBounds(323, 458, 110, 40);
		contentPane.add(btnExit);
		
		JLabel lblNewLabel = new JLabel("Name :");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblNewLabel.setBounds(36, 24, 121, 34);
		contentPane.add(lblNewLabel);
		
		JLabel lblRollno = new JLabel("Roll_No :");
		lblRollno.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblRollno.setBounds(36, 81, 121, 34);
		contentPane.add(lblRollno);
		
		JLabel lblMobileNo = new JLabel("Mobile no :");
		lblMobileNo.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblMobileNo.setBounds(36, 141, 121, 34);
		contentPane.add(lblMobileNo);
		
		lblClass = new JLabel("Class :");
		lblClass.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblClass.setBounds(36, 192, 121, 34);
		contentPane.add(lblClass);
		
		lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblPassword.setBounds(36, 403, 121, 34);
		contentPane.add(lblPassword);
		
		lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblEmail.setBounds(36, 244, 121, 34);
		contentPane.add(lblEmail);
		
		lblDateOfBirth = new JLabel("Date of Birth  :");
		lblDateOfBirth.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(36, 288, 167, 34);
		contentPane.add(lblDateOfBirth);
		
		lblGender = new JLabel("Gender :");
		lblGender.setFont(new Font("Sitka Text", Font.PLAIN, 20));
		lblGender.setBounds(36, 342, 121, 34);
		contentPane.add(lblGender);
		
		comboBox = new JComboBox();
		comboBox.setBounds(224, 342, 167, 40);
		contentPane.add(comboBox);
		comboBox.addItem("Select Gender");
		comboBox.addItem("Male");
		comboBox.addItem("Female");
		comboBox.addItem("Other");
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(224, 289, 167, 34);  // Adjust the position and size
		contentPane.add(dateChooser);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("Sitka Text", Font.PLAIN, 18));
		textField_3.setColumns(10);
		textField_3.setBounds(224, 236, 167, 40);
		contentPane.add(textField_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(224, 403, 167, 34);
		contentPane.add(passwordField);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(224, 186, 167, 40);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("Select Class");
		comboBox_1.addItem("IMCA-1");
		comboBox_1.addItem("IMCA-2");
		comboBox_1.addItem("MSc-1");
		comboBox_1.addItem("MSc-2");

		
		
	}
	private static void saveDetails(String s1, String s2,String s3,String s4,String s5,Date s6,String s7,String s8) {
		String dbURL = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "Snehal@16";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, user, pass);
                 Statement stmt = conn.createStatement()) {
            	
            	String createDB = "CREATE DATABASE IF NOT EXISTS Ideathon_db";
                stmt.executeUpdate(createDB);
                System.out.println("Database Student_db ensured.");
                
                String useDB = "USE Ideathon_db";
                stmt.execute(useDB);

                String createTable = """
                    CREATE TABLE IF NOT EXISTS Student (
                        Name VARCHAR(255) NOT NULL,
                        Roll_no VARCHAR(255) NOT NULL UNIQUE,
                        Mobile_no VARCHAR(10) NOT NULL UNIQUE,
                        Class VARCHAR(255) NOT NULL,
                        Email VARCHAR(255) NOT NULL,
                        date DATE,
                        Gender VARCHAR(255) NOT NULL,
                        password VARCHAR(255)NOT NULL
                    );
                """;
                stmt.executeUpdate(createTable);
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Student(Name, Roll_no, Mobile_no, Class, Email, date, Gender,  password) VALUES (?,?,?,?,?,?,?,?)"); 
                pstmt.setString(1,s1);
                pstmt.setString(2,s2);
                pstmt.setString(3,s3);
                pstmt.setString(4,s4);
                pstmt.setString(5,s5);
                java.sql.Date sqlDate = new java.sql.Date(s6.getTime());
                pstmt.setDate(6, sqlDate);
                pstmt.setString(7, s7);
                pstmt.setString(8, s8);
                pstmt.executeUpdate();
            }
        }
        catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        }  catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
}
