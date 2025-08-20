package department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.table.TableModel;

import department.Teacher_dashboard;

import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_dashboard extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane; 
	private CardLayout cardlayout;
	private JTextField textField, textField_1, textField_5,textField_6,textField_7,textField_8,textField_9;
	private JTable table_1,table_2,table_3;
	private Connection con;
	private Statement st;
	private ResultSet rs;
	private JButton btnNewButton, btnNewButton_1, btnNewButton_2, btnNewButton_3,btnNewButton_4, btnNewButton_6;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_dashboard frame = new Admin_dashboard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void GetConnection() {
		try{
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		}
		catch(SQLException ex) {ex.printStackTrace();}
	}
	
	public Admin_dashboard() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		GetConnection();
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(50, 20));
		
		JPanel Left_panel = new JPanel();
		Left_panel.setBorder(new EmptyBorder(10, 10, 30, 0));
		contentPane.add(Left_panel, BorderLayout.WEST);
		
		btnNewButton = new JButton("Profile");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnNewButton_1 = new JButton("Student Details");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnNewButton_2 = new JButton("Teacher Details");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnNewButton_3 = new JButton("Feedback");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		btnNewButton_6 = new JButton("Verify the Teacher");
		btnNewButton_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnNewButton_4 = new JButton("Assign the teacher");
		btnNewButton_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		Dimension buttonSize = new Dimension(150, 40); 
		btnNewButton.setPreferredSize(buttonSize);
		btnNewButton_1.setPreferredSize(buttonSize);
		btnNewButton_2.setPreferredSize(buttonSize);
		btnNewButton_3.setPreferredSize(buttonSize);
		btnNewButton_6.setPreferredSize(buttonSize);
		
		Left_panel.setLayout(new GridLayout(0, 1, 10, 8));
		Left_panel.add(btnNewButton);
		Left_panel.add(btnNewButton_1);
		Left_panel.add(btnNewButton_2);
		Left_panel.add(btnNewButton_3);
		Left_panel.add(btnNewButton_4);
		Left_panel.add(btnNewButton_6);
		
		JLabel lblNewLabel = new JLabel("Admin Dashboard");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel Right_panel = new JPanel();
		cardlayout = new CardLayout();
		Right_panel.setLayout(cardlayout);
		contentPane.add(Right_panel, BorderLayout.CENTER);
		
		Right_panel.add(createProfilePanel(), "Profile");
		Right_panel.add(createStudent_DetailsPanel(), "Student_Details");
		Right_panel.add(createTeacher_DetailsPanel(), "Teacher_Details");
		Right_panel.add(createFeedbackPanel(), "Feedback");
		Right_panel.add(createVerificationPanel(), "Verify_teacher");
		Right_panel.add(createAssignPanel(), "Assign_teacher");
		
		btnNewButton.addActionListener(e -> cardlayout.show(Right_panel, "Profile"));
		btnNewButton_1.addActionListener(e -> cardlayout.show(Right_panel, "Student_Details"));
		btnNewButton_2.addActionListener(e -> cardlayout.show(Right_panel, "Teacher_Details"));
		btnNewButton_3.addActionListener(e -> cardlayout.show(Right_panel, "Feedback"));
		btnNewButton_6.addActionListener(e -> cardlayout.show(Right_panel, "Verify_teacher"));
		btnNewButton_4.addActionListener(e -> cardlayout.show(Right_panel, "Assign_teacher"));
	}
	
	public JPanel createProfilePanel() {
		JPanel Rpanel = new JPanel();
		Rpanel.setLayout(new BorderLayout(0, 0));
		JLabel label = new JLabel("Since Aug 2024-present");
		Rpanel.add(label, BorderLayout.SOUTH);
		
		JTextArea txtrThisIsAn = new JTextArea();
		txtrThisIsAn.setLineWrap(true);
		txtrThisIsAn.setWrapStyleWord(true);
		txtrThisIsAn.setEditable(false);
		txtrThisIsAn.setFont(new Font("Monospaced", Font.PLAIN, 17));
		txtrThisIsAn.setText("This is an admin dashboard.\r\n1. Admin can view student datails by searching his/her name and he can remove the student from the department.\r\n2. Admin can view teacher details such as name, email, qualifications etc. At the same admin is able to remove the teacher from the department\r\n3. Admin can view the feedbacks given by students.\r\n4. Admin has access to attendence, internal & external marks, course progress.\r\n5. Admin verifies the student and teacher after registration.");
		Rpanel.add(txtrThisIsAn, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Admin Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		Rpanel.add(lblNewLabel_1, BorderLayout.NORTH);
		
		return Rpanel;
	}
	public JPanel createStudent_DetailsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(50, 50, 100, 50));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 1, 1));
		
        String[] columnNames = {"ID", "Name","Phone No.", "Class", "Email", "DOB","Gender"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        try(ResultSet rs = st.executeQuery("select * from student")){
			while(rs.next()) {
				tableModel.addRow(new String[]{rs.getString(2), rs.getString(1), rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6).toString(), rs.getString(7)});
			}
		}catch(SQLException ex) {ex.printStackTrace();}
        
		table_3 = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table_3);
        scrollPane.setPreferredSize(new Dimension(1000, 300));
		panel_1.add(scrollPane);
	    
	    JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Centered with padding
	    JLabel comboLabel = new JLabel("Student Details ");
	    comboPanel.add(comboLabel);
	    panel.add(comboPanel, BorderLayout.NORTH);
	    
		return panel;
	}
	
	public JPanel createTeacher_DetailsPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EmptyBorder(50, 50, 100, 50));
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 2, 5, 5));
		
		JLabel lblNewLabel_14 = new JLabel("Name");
		panel_1.add(lblNewLabel_14);
		
		textField_5 = new JTextField();
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Username");
		panel_1.add(lblNewLabel_2);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Subject");
		panel_1.add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_15 = new JLabel("Mobile no.");
		panel_1.add(lblNewLabel_15);
		
		textField_6 = new JTextField();
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Date of Birth");
		panel_1.add(lblNewLabel_16);
		
		textField_7 = new JTextField();
		panel_1.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_18 = new JLabel("Gender");
		panel_1.add(lblNewLabel_18);
		
		textField_8 = new JTextField();
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblNewLabel_13 = new JLabel("Qualifications");
		panel_1.add(lblNewLabel_13);
		
		textField_9 = new JTextField();
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Status");
		panel_1.add(lblNewLabel_4);
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
	    
	    JButton btnNewButton_7 = new JButton("View Dashboard");
	    btnNewButton_7.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SwingUtilities.invokeLater(() -> {
	            	Teacher_dashboard adminPage = new Teacher_dashboard();
	                adminPage.setVisible(true);
	            });
	    	}
	    });
	    buttonPanel.add(btnNewButton_7);
	    JButton btnDeleteTeacher = new JButton("Delete Teacher");
	    btnDeleteTeacher.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String username = textField.getText();
	    		try {
	    			st.executeUpdate("delete from teacher where username=\"" + username + "\"");
	    		}catch(SQLException ex) {ex.printStackTrace();}
	    		JOptionPane.showMessageDialog(
        	            Admin_dashboard.this,
        	            "Teacher record deleted from department!"
        	        );
	    	}
	    });
	    buttonPanel.add(btnDeleteTeacher);
	    panel.add(buttonPanel, BorderLayout.SOUTH);
	    
	    JPanel comboPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
	    JLabel comboLabel = new JLabel("Select teacher: ");
	    ArrayList<String> t = this.getComboList("teacher");
		String[] itemsArray = t.toArray(new String[0]);
	    JComboBox<String> teacherList = new JComboBox<String>(itemsArray);
	    comboPanel.add(comboLabel);
	    comboPanel.add(teacherList);
	    panel.add(comboPanel, BorderLayout.NORTH);
	    
	    JButton btnNewButton_5 = new JButton("Select");
	    btnNewButton_5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String name = (String) teacherList.getSelectedItem();
	    		try(ResultSet rs = st.executeQuery("select * from teacher where name=\"" + name + "\"")){
	    			rs.next();
	    			textField_5.setText(rs.getString(1));
	    			textField.setText(rs.getString(2));
	    			textField_1.setText(rs.getString(3));
	    			textField_6.setText(rs.getString(4));
	    			textField_7.setText(rs.getDate(5).toString());
	    			textField_8.setText(rs.getString(6));
	    			textField_9.setText(rs.getString(7));
	    			lblNewLabel_4.setText("Status : "+ rs.getString(9));
	    		}catch(SQLException ex) {ex.printStackTrace();}
	    	}
	    });
	    comboPanel.add(btnNewButton_5);
		return panel;
	}
	
	public JPanel createFeedbackPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1, 0, 0));
		
        String[] columnNames = {"ID", "Feedback"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        try(ResultSet rs = st.executeQuery("select * from feedback")){
			while(rs.next()) {
				tableModel.addRow(new String[]{rs.getString(1), rs.getString(2)});
			}
		}catch(SQLException ex) {ex.printStackTrace();}
        
        table_2 = new JTable(tableModel);
        
        table_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
        table_2.setSize(500, 300);
        TableColumn idColumn = table_2.getColumnModel().getColumn(0);
        idColumn.setPreferredWidth(10);
        TableColumn feedbackColumn = table_2.getColumnModel().getColumn(1);
        feedbackColumn.setPreferredWidth(500);
        JScrollPane scrollPane = new JScrollPane(table_2);
        panel.add(scrollPane);
		return panel;
	}
	public JPanel createVerificationPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		String[] columnNames = {"Name", "Username","Subject","Mobile no.", "Date of Birth","Gender","Qualifications"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        try(ResultSet rs = st.executeQuery("select * from teacher where status=\"pending\" ")){
			while(rs.next()) {
				tableModel.addRow(new String[]{rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5).toString(),rs.getString(6),rs.getString(7)});
			}
		}catch(SQLException ex) {ex.printStackTrace();}
        
        table_1 = new JTable(tableModel);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 

        table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table_1.getSelectedRow();
                    if (selectedRow != -1) {
                    	String name = (String) tableModel.getValueAt(selectedRow, 0);
                    	String Uname = (String) tableModel.getValueAt(selectedRow, 1);
                    	int result = JOptionPane.showConfirmDialog(
                    	        Admin_dashboard.this, 
                    	        "Do you want to accept the teacher in the department? Name " + name ,
                    	        "Confirm",
                    	        JOptionPane.YES_NO_OPTION,
                    	        JOptionPane.QUESTION_MESSAGE
                    	    );

                    	    if (result == JOptionPane.YES_OPTION) {
                    	    	String query = "UPDATE teacher SET status=? WHERE username=?";
                    	    	try {
                    	    	    PreparedStatement pstmt = con.prepareStatement(query);
                    	    	    pstmt.setString(1, "approved"); // Set the first parameter
                    	    	    pstmt.setString(2, Uname);      // Set the second parameter (username)
                    	    	    pstmt.executeUpdate();          // Execute the update query
                    	    	    pstmt.close();                  // Close the PreparedStatement
                    	    	} catch (SQLException ep) {
                    	    	    ep.printStackTrace();            // Handle SQL exceptions
                    	    	}
                    	    	JOptionPane.showMessageDialog(
                        	            Admin_dashboard.this,
                        	            "Teacher " + name + " is added succesfully");
                    	        /*try{st.executeUpdate("update teacher set status=\"approved\" where username="+Uname);}
                    	        catch(SQLException ex) {ex.printStackTrace();}
                    	        
                    	        );*/
                    	    } else if (result == JOptionPane.NO_OPTION) {
                    	        JOptionPane.showMessageDialog(
                    	            Admin_dashboard.this,
                    	            "Selection cancelled!"
                    	        );
                    	    }
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(table_1);
        panel.add(scrollPane);
		return panel;
	}
	
	public JPanel createAssignPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 

        JLabel teacherLabel = new JLabel("Choose the Teacher:");
        teacherLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        teacherLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(teacherLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 
        
        ArrayList<String> t = this.getComboList("teacher");
		String[] itemsArray = t.toArray(new String[0]);
        JComboBox<String> teacherComboBox = new JComboBox<>(itemsArray);
        teacherComboBox.setMaximumSize(new Dimension(400, 30));
        teacherComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(teacherComboBox);
        
        panel.add(Box.createRigidArea(new Dimension(0, 50))); 

        JLabel subjectLabel = new JLabel("Choose the Subject:");
        subjectLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subjectLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(subjectLabel);
        
        panel.add(Box.createRigidArea(new Dimension(0, 10))); 

        ArrayList<String> s = this.getComboList("subject");
		String[] sArray = s.toArray(new String[0]);
        JComboBox<String> subjectComboBox = new JComboBox<>(sArray);
        subjectComboBox.setMaximumSize(new Dimension(400, 30));
        subjectComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(subjectComboBox);
        
        panel.add(Box.createRigidArea(new Dimension(0, 30))); 
        JLabel lblNewLabel_5 = new JLabel("Choose the Class:");
        lblNewLabel_5.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(lblNewLabel_5);
        
		String[] cArray = {" ","MSc-I", "MSc-II", "IMCA-I","IMCA-II"};
        JComboBox<String> comboBox = new JComboBox<>(cArray);
        comboBox.setMaximumSize(new Dimension(400, 30));
        comboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(comboBox);
        
        JButton submitButton = new JButton("Assign");
        submitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String createTable = """
                        CREATE TABLE IF NOT EXISTS Subject_teacher (
                            ID INT AUTO_INCREMENT NOT NULL,
                            Subject VARCHAR(255) NOT NULL UNIQUE,
                            Teacher VARCHAR(255) NOT NULL UNIQUE,
                            Class VARCHAR(255) NOT NULL,
                            primary key (ID)
                        );
                    """;
        		try(	
        				Statement st = con.createStatement();
        				){
        			st.executeUpdate(createTable);
    				PreparedStatement pst = con.prepareStatement("insert into Subject_teacher(Subject, Teacher, Class) values (?,?,?)");
        			String tea = (String) teacherComboBox.getSelectedItem();
        			String subj = (String) subjectComboBox.getSelectedItem();
        			String cls = (String) comboBox.getSelectedItem();
        			pst.setString(1, subj);
					pst.setString(2, tea);
					pst.setString(3, cls);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(
            	            Admin_dashboard.this,
            	            "Subject "+ subj+"is assigned to "+tea+" is assigned succesfully");
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.setMaximumSize(new Dimension(100, 30)); 
        panel.add(submitButton);
		return panel;
	}
	
	public ArrayList<String> getComboList(String table) {
		ArrayList<String> teacher = new ArrayList<String>();
		teacher.add("                    ");
		try(ResultSet rs = st.executeQuery("select name from " + table)) {
			while(rs.next()) {
				teacher.add(rs.getString(1));
			}	
		}catch(SQLException ex) {ex.printStackTrace();}
		return teacher;
	}
}