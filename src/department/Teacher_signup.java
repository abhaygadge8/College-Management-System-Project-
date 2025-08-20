package department;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class Teacher_signup extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5;
    private JRadioButton rdbtnMale, rdbtnFemale;
    private JDateChooser dateChooser;
    private JPasswordField passwordField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Teacher_signup frame = new Teacher_signup();
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
    public Teacher_signup() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1358, 764);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("First Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(278, 72, 230, 30);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(624, 75, 280, 30);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Last Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(278, 123, 224, 30);
        contentPane.add(lblNewLabel_1);

        textField_1 = new JTextField();
        textField_1.setBounds(624, 126, 280, 30);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Username");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(278, 184, 229, 35);
        contentPane.add(lblNewLabel_2);

        textField_2 = new JTextField();
        textField_2.setBounds(624, 186, 280, 35);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("Subject");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBounds(278, 247, 230, 35);
        contentPane.add(lblNewLabel_3);

        textField_3 = new JTextField();
        textField_3.setBounds(624, 252, 280, 30);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JLabel lblNewLabel_4 = new JLabel("Password");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(278, 569, 230, 35);
        contentPane.add(lblNewLabel_4);

        JButton btnNewButton = new JButton("Sign up");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String firstName = textField.getText();
                String lastName = textField_1.getText();
                String username = textField_2.getText();
                String subject = textField_3.getText();
                String mobileNo = textField_4.getText();
                String gender =  rdbtnMale.isSelected() ? "Male" : (rdbtnFemale.isSelected() ? "Female" : "Not Selected");
                Date dob = dateChooser.getDate();
                String qualification = textField_5.getText();
                String password = new String(passwordField.getPassword());

                if (!firstName.isEmpty() && !lastName.isEmpty() && !username.isEmpty() && 
                    !subject.isEmpty() && !mobileNo.isEmpty() && dob != null && 
                    !qualification.isEmpty() && !password.isEmpty()) {
                    saveDetails(firstName, lastName, username, subject, mobileNo, dob, gender, qualification, password);
                    JOptionPane.showMessageDialog(contentPane, "Details saved");
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_5.setText("");
                    passwordField.setText("");
                    rdbtnMale.setSelected(false);
                    rdbtnFemale.setSelected(false);
                    dateChooser.setDate(null);
                    EventQueue.invokeLater(new Runnable() {
            			public void run() {
            				try {
            					Teacher_login frame = new Teacher_login();
            					frame.setVisible(true);
            					dispose();
            				} catch (Exception e) {
            					e.printStackTrace();
            				}
            			}
            		});
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Enter valid details.");
                }
            }
        });
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.setBounds(318, 664, 125, 33);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textField.setText("");
                textField_1.setText("");
                textField_2.setText("");
                textField_3.setText("");
                textField_4.setText("");
                textField_5.setText("");
                passwordField.setText("");
                rdbtnMale.setSelected(false);
                rdbtnFemale.setSelected(false);
                dateChooser.setDate(null);
            }
        });
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_1.setBounds(593, 662, 125, 35);
        contentPane.add(btnNewButton_1);

        JButton btnNewButton_2 = new JButton("Exit");
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnNewButton_2.setBounds(862, 662, 125, 35);
        contentPane.add(btnNewButton_2);

        JLabel lblNewLabel_5 = new JLabel("Teacher Registration");
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 40));
        lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_5.setBounds(313, 10, 656, 44);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("Mobile No");
        lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6.setBounds(274, 311, 234, 35);
        contentPane.add(lblNewLabel_6);

        textField_4 = new JTextField();
        textField_4.setBounds(624, 313, 280, 35);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JLabel lblNewLabel_6_1 = new JLabel("Gender");
        lblNewLabel_6_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6_1.setBounds(274, 374, 234, 35);
        contentPane.add(lblNewLabel_6_1);

        rdbtnMale = new JRadioButton("Male");
        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnMale.setBounds(624, 372, 125, 43);
        contentPane.add(rdbtnMale);

        rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 17));
        rdbtnFemale.setBounds(763, 371, 141, 44);
        contentPane.add(rdbtnFemale);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(rdbtnMale);
        genderGroup.add(rdbtnFemale);

        JLabel lblNewLabel_6_1_1 = new JLabel("Date of Birth");
        lblNewLabel_6_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6_1_1.setBounds(278, 441, 234, 35);
        contentPane.add(lblNewLabel_6_1_1);

        dateChooser = new JDateChooser();
        dateChooser.setBounds(624, 441, 280, 34);
        contentPane.add(dateChooser);

        JLabel lblNewLabel_6_1_2 = new JLabel("Qualification");
        lblNewLabel_6_1_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_6_1_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_6_1_2.setBounds(274, 504, 234, 35);
        contentPane.add(lblNewLabel_6_1_2);

        textField_5 = new JTextField();
        textField_5.setBounds(624, 506, 280, 35);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setBounds(624, 571, 280, 35);
        contentPane.add(passwordField);
    }
    
    private static void saveDetails(String firstName, String lastName, String username, String subject, String mobileNo, Date dob, String gender, String qualification, String password) {
        String dbURL = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String pass = "Snehal@16";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, user, pass);
                 Statement stmt = conn.createStatement()) {

                String useDB = "USE Ideathon_db";
                stmt.execute(useDB);

                String createTable = """
                    CREATE TABLE IF NOT EXISTS Teacher (
                        Name VARCHAR(255) NOT NULL,
                        Username VARCHAR(255) NOT NULL UNIQUE,
                        Subject VARCHAR(255) NOT NULL,
                        Mobile_No VARCHAR(10) NOT NULL UNIQUE,
                        Date_Of_Birth DATE,
                        Gender VARCHAR(10) NOT NULL,
                        Qualification VARCHAR(255) NOT NULL,
                        Password VARCHAR(255) NOT NULL,
                        Status VARCHAR(20) DEFAULT 'pending' 
                    );
                """;
                stmt.executeUpdate(createTable);

                PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO Teacher (Name, Username, Subject, Mobile_No, Date_Of_Birth, Gender, Qualification, Password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
                );
                pstmt.setString(1, firstName+" "+lastName);
                pstmt.setString(2, username);
                pstmt.setString(3, subject);
                pstmt.setString(4, mobileNo);
                java.sql.Date sqlDate = new java.sql.Date(dob.getTime());
                pstmt.setDate(5, sqlDate);
                pstmt.setString(6, gender);
                pstmt.setString(7, qualification);
                pstmt.setString(8, password);
                pstmt.executeUpdate();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            e.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
