package department;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

public class Teacher_dashboard extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel rightPanel;
    private CardLayout cardLayout;
    private JTextField textField,textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7,textField_8,textField_9;
    private JTable table;
    private JDateChooser dateChooser, dateChooser_1;
    private java.sql.Statement st;
    private Connection conn;
	private ResultSet rs;

	public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Teacher_dashboard adminPage = new Teacher_dashboard();
            adminPage.setVisible(true);
        });
    }
    public Teacher_dashboard() {
        setTitle("Admin Page");
        setSize(1345, 771);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        getContentPane().setLayout(new BorderLayout());

        // Left Panel (Buttons)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(200, getHeight()));
        leftPanel.setBackground(Color.LIGHT_GRAY);

        JButton btn1 = new JButton("Mark Attendance");
        btn1.setFont(new Font("Tahoma", Font.BOLD, 16));
        JButton btn2 = new JButton("Internal Marks");
        btn2.setFont(new Font("Tahoma", Font.BOLD, 16));
        JButton btn3 = new JButton("View Student Details");
        btn3.setFont(new Font("Tahoma", Font.BOLD, 16));
        JButton btn4 = new JButton("Topic Covered");
        btn4.setFont(new Font("Tahoma", Font.BOLD, 16));
        FlowLayout fl_leftPanel = new FlowLayout(FlowLayout.CENTER, 5, 120);
        fl_leftPanel.setAlignOnBaseline(true);
        leftPanel.setLayout(fl_leftPanel);

        leftPanel.add(btn1);
        leftPanel.add(Box.createVerticalStrut(11)); 
        leftPanel.add(btn2);
        leftPanel.add(Box.createVerticalStrut(12));
        leftPanel.add(btn3);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(btn4);

        rightPanel = new JPanel();
        cardLayout = new CardLayout();
        rightPanel.setLayout(cardLayout);

        rightPanel.add(createDashboardPanel(), "Dashboard");
        rightPanel.add(createUsersPanel(), "Users");
        rightPanel.add(createReportsPanel(), "Reports");
        rightPanel.add(createSettingsPanel(), "Settings");

        btn1.addActionListener(e -> cardLayout.show(rightPanel, "Dashboard"));
        btn2.addActionListener(e -> cardLayout.show(rightPanel, "Users"));
        btn3.addActionListener(e -> cardLayout.show(rightPanel, "Reports"));
        btn4.addActionListener(e -> cardLayout.show(rightPanel, "Settings"));

        getContentPane().add(leftPanel, BorderLayout.WEST);
        getContentPane().add(rightPanel, BorderLayout.CENTER);
    }

    private JPanel createDashboardPanel() {
    	initialiseDB();
    	JPanel panel = new JPanel();
        panel.setLayout(null);
        
        ArrayList<String> s = this.getComboList("subject");
        String[] items = {"Present", "Absent", ""};
        JComboBox<String> comboBox = new JComboBox<>(items);
        String[] sArray = s.toArray(new String[0]);
        JComboBox<String> comboBox_1 = new JComboBox<>(sArray);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
        comboBox.setBounds(590, 414, 309, 42);
        panel.add(comboBox);
        dateChooser_1 = new JDateChooser();
		dateChooser_1.setBounds(588, 303, 309, 34);  
		panel.add(dateChooser_1);
        
        JButton btnNewButton = new JButton("Submit");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name=textField.getText();
        		String roll = textField_1.getText();
        		
        		String sub = (String) comboBox_1.getSelectedItem();
        		Date date = dateChooser_1.getDate();
        		String attend =(String) comboBox.getSelectedItem();
        		if (date == null) {
        	        JOptionPane.showMessageDialog(Teacher_dashboard.this, "Please select a valid date.");
        	        return;
        	    }
        		if(!name.isEmpty()&&!roll.isEmpty()&&!attend.isEmpty()){
        			saveattendance(name,roll,date,sub,attend);
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"Attendence saved!");
        			textField.setText("");
        			textField_1.setText("");
        			dateChooser_1.setDate(new Date());
        			comboBox.setSelectedItem(0);
        			comboBox_1.setSelectedItem(0);
        		}else{
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"All Fields are required !");

        		}
        	}
        });
        btnNewButton.setBounds(472, 582, 180, 42);
        panel.add(btnNewButton);
        
        JLabel lblNewLabel = new JLabel("Student Name");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel.setBounds(255, 162, 209, 42);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);
        
        JLabel label = new JLabel("Welcome to the Attendance Page");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(307, 38, 517, 80);
        label.setFont(new Font("Tahoma", Font.BOLD, 30));
        panel.add(label);
        
        textField = new JTextField();
        textField.setEditable(false);
        textField.setBounds(588, 165, 305, 42);
        panel.add(textField);
        textField.setColumns(10);
        
        JLabel lblStudentRollNo = new JLabel("Student Roll No");
        lblStudentRollNo.setHorizontalAlignment(SwingConstants.CENTER);
        lblStudentRollNo.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblStudentRollNo.setBounds(255, 238, 209, 42);
        panel.add(lblStudentRollNo);
        
        textField_1 = new JTextField();
        textField_1.setEditable(false);
        textField_1.setBounds(588, 244, 305, 35);
        panel.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblNewLabel_1_1 = new JLabel("Date");
        lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1.setBounds(255, 303, 209, 42);
        panel.add(lblNewLabel_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Attendance");
        lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_1_1_1.setBounds(255, 414, 209, 42);
        panel.add(lblNewLabel_1_1_1);
        
        JButton btnNewButton_7 = new JButton("First");
        btnNewButton_7.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    					rs.first();
    					textField.setText(rs.getString(1));
    					textField_1.setText(rs.getString(2));
    					
        		}catch(SQLException ex) {
        				ex.printStackTrace();
        		}
        	}
        });
        btnNewButton_7.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_7.setBounds(192, 485, 147, 42);
        panel.add(btnNewButton_7);
        
        JButton btnNewButton_7_1 = new JButton("Next");
        btnNewButton_7_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isLast())
    				{
        				rs.next();
    					textField.setText(rs.getString(1));
    					textField_1.setText(rs.getString(2));
    				}
        		}catch(SQLException ex) {
        				ex.printStackTrace();
        		}
        	}
        });
        btnNewButton_7_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_7_1.setBounds(399, 487, 147, 40);
        panel.add(btnNewButton_7_1);
        
        JButton btnNewButton_7_2 = new JButton("Previous");
        btnNewButton_7_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isFirst())
    				{
        				rs.previous();
    					textField.setText(rs.getString(1));
    					textField_1.setText(rs.getString(2));
    				}
        		}catch(SQLException ex) {
        				ex.printStackTrace();
        		}
        	}
        });
        btnNewButton_7_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_7_2.setBounds(580, 487, 147, 40);
        panel.add(btnNewButton_7_2);
        
        JButton btnNewButton_7_3 = new JButton("Last");
        btnNewButton_7_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    						rs.last();
        					textField.setText(rs.getString(1));
        					textField_1.setText(rs.getString(2));
    					
        		}catch(SQLException ex) {
        				ex.printStackTrace();
        		}
        	}
        });
        btnNewButton_7_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_7_3.setBounds(786, 487, 147, 40);
        panel.add(btnNewButton_7_3);
        
        JLabel lblNewLabel_4_1_1 = new JLabel("Subject");
        lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4_1_1.setBounds(265, 355, 197, 37);
        panel.add(lblNewLabel_4_1_1);
        
        comboBox_1.setBounds(588, 362, 309, 27);
        panel.add(comboBox_1);
        
        return panel;
    }

    private JPanel createUsersPanel() {
    	initialiseDB();
    	JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblNewLabel_1 = new JLabel("Internal Mark");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(224, 22, 550, 87);
        panel.add(lblNewLabel_1);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(589, 10, 2, 2);
        panel.add(scrollPane);
        
        JLabel lblNewLabel_2 = new JLabel("Student Name");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2.setBounds(281, 119, 203, 45);
        panel.add(lblNewLabel_2);
        
        textField_3 = new JTextField();
        textField_3.setEditable(false);
        textField_3.setBounds(548, 119, 307, 45);
        panel.add(textField_3);
        textField_3.setColumns(10);
        
        JLabel lblNewLabel_2_1 = new JLabel("Student Roll");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_1.setBounds(281, 213, 203, 45);
        panel.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_2_2 = new JLabel("Subject");
        lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_2.setBounds(281, 300, 203, 45);
        panel.add(lblNewLabel_2_2);
        
        JLabel lblNewLabel_2_3 = new JLabel("Internal Marks");
        lblNewLabel_2_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_2_3.setBounds(281, 393, 203, 45);
        panel.add(lblNewLabel_2_3);
        
        textField_4 = new JTextField();
        textField_4.setEditable(false);
        textField_4.setBounds(548, 212, 307, 46);
        panel.add(textField_4);
        textField_4.setColumns(10);
        
        textField_5 = new JTextField();
        textField_5.setBounds(548, 299, 315, 45);
        panel.add(textField_5);
        textField_5.setColumns(10);
        
        textField_6 = new JTextField();
        textField_6.setBounds(540, 393, 323, 45);
        panel.add(textField_6);
        textField_6.setColumns(10);
        
        JButton btnNewButton_2 = new JButton("Submit");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String name=textField_3.getText();
        		String roll = textField_4.getText();
        		String subject = textField_5.getText();
        		int mark = Integer.parseInt(textField_6.getText());
        		if(!name.isEmpty()&&!roll.isEmpty()&&!subject.isEmpty()&&mark!=-1){
        			saveInternalmark(name,roll,subject,mark);
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"marks saved!");
        			textField_3.setText("");
        			textField_4.setText("");
        			textField_5.setText("");
        			textField_6.setText("");
        		}else{
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"fill all field");

        		}
        	}
        });
        btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_2.setBounds(309, 586, 222, 42);
        panel.add(btnNewButton_2);
        
        JButton btnNewButton_3 = new JButton("Clear");
        btnNewButton_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textField_5.setText("");
    			textField_6.setText("");
        	}
        });
        btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_3.setBounds(623, 584, 214, 47);
        panel.add(btnNewButton_3);
        
        JButton btnNewButton_8 = new JButton("First");
        btnNewButton_8.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {rs.first();
    					textField_3.setText(rs.getString(1));
    					textField_4.setText(rs.getString(2));
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_8.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_8.setBounds(159, 490, 112, 26);
        panel.add(btnNewButton_8);
        
        JButton btnNewButton_8_1 = new JButton("Next");
        btnNewButton_8_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isLast())
    				{
    					rs.next();
    					textField_3.setText(rs.getString(1));
    					textField_4.setText(rs.getString(2));
    				}
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_8_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_8_1.setBounds(328, 493, 112, 26);
        panel.add(btnNewButton_8_1);
        
        JButton btnNewButton_8_2 = new JButton("Previous");
        btnNewButton_8_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isFirst())
    				{
    					rs.previous();
    					textField_3.setText(rs.getString(1));
    					textField_4.setText(rs.getString(2));
    				}
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_8_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_8_2.setBounds(510, 493, 112, 26);
        panel.add(btnNewButton_8_2);
        
        JButton btnNewButton_8_3 = new JButton("Last");
        btnNewButton_8_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					rs.isLast();
					textField_3.setText(rs.getString(1));
					textField_4.setText(rs.getString(2));
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_8_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_8_3.setBounds(705, 493, 112, 26);
        panel.add(btnNewButton_8_3);
        return panel;
    }

    private JPanel createReportsPanel() {
    	initialiseDB1();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblViewStudentDetail = new JLabel("View Student Detail");
        lblViewStudentDetail.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblViewStudentDetail.setHorizontalAlignment(SwingConstants.CENTER);
        lblViewStudentDetail.setBounds(309, 5, 461, 80);
        panel.add(lblViewStudentDetail);
        
        JLabel lblNewLabel_3 = new JLabel("Student Name");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3.setBounds(236, 116, 253, 54);
        panel.add(lblNewLabel_3);
        
        textField_7 = new JTextField();
        textField_7.setEditable(false);
        textField_7.setBounds(605, 117, 353, 54);
        panel.add(textField_7);
        textField_7.setColumns(10);
        
        JLabel lblNewLabel_3_1 = new JLabel("");
        lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3_1.setBounds(236, 224, 253, 54);
        panel.add(lblNewLabel_3_1);
        
        JLabel lblNewLabel_3_2 = new JLabel("Subject Internal Mark");
        lblNewLabel_3_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3_2.setBounds(236, 345, 253, 54);
        panel.add(lblNewLabel_3_2);
        
        textField_8 = new JTextField();
        textField_8.setEditable(false);
        textField_8.setBounds(605, 231, 353, 47);
        panel.add(textField_8);
        textField_8.setColumns(10);
        
        textField_9 = new JTextField();
        textField_9.setEditable(false);
        textField_9.setBounds(605, 345, 353, 47);
        panel.add(textField_9);
        textField_9.setColumns(10);
        
        JButton btnNewButton_4 = new JButton("First");
        btnNewButton_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
    				{
    					rs.first();
    					textField_7.setText(rs.getString(1));
    					textField_8.setText(rs.getString(2));
    					textField_9.setText(rs.getString(3));
    				}
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_4.setBounds(192, 481, 135, 36);
        panel.add(btnNewButton_4);
        
        JButton btnNewButton_4_1 = new JButton("Next");
        btnNewButton_4_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isLast())
    				{
    					rs.next();
    					textField_7.setText(rs.getString(1));
    					textField_8.setText(rs.getString(2));
    					textField_9.setText(rs.getString(3));
    				}
        		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_4_1.setBounds(376, 481, 135, 36);
        panel.add(btnNewButton_4_1);
        
        JButton btnNewButton_4_2 = new JButton("Previous");
        btnNewButton_4_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			if(!rs.isFirst())
    				{
    					rs.previous();
    					textField_7.setText(rs.getString(1));
    					textField_8.setText(rs.getString(2));
    					textField_9.setText(rs.getString(3));
    				}
        		}catch(SQLException ex) {ex.printStackTrace();}
        		
        	}
        });
        btnNewButton_4_2.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_4_2.setBounds(571, 481, 135, 36);
        panel.add(btnNewButton_4_2);
        
        JButton btnNewButton_4_3 = new JButton("Last");
        btnNewButton_4_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
					rs.isLast();
					textField_7.setText(rs.getString(1));
					textField_8.setText(rs.getString(2));
					textField_9.setText(rs.getString(3));
    		}catch(SQLException ex) {ex.printStackTrace();}
        	}
        });
        btnNewButton_4_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_4_3.setBounds(755, 481, 135, 36);
        panel.add(btnNewButton_4_3);
        
        JLabel lblNewLabel_3_3 = new JLabel("Student Roll No");
        lblNewLabel_3_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3_3.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_3_3.setBounds(236, 231, 253, 54);
        panel.add(lblNewLabel_3_3);
        return panel;
    }

    private JPanel createSettingsPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        
        JLabel lblTopicCovered = new JLabel("Topic Covered");
        lblTopicCovered.setHorizontalAlignment(SwingConstants.CENTER);
        lblTopicCovered.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblTopicCovered.setBounds(314, 48, 424, 52);
        panel.add(lblTopicCovered);
        
        JLabel lblNewLabel_4 = new JLabel("Date");
        lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4.setBounds(249, 155, 197, 37);
        panel.add(lblNewLabel_4);
        
        dateChooser = new JDateChooser();
		dateChooser.setBounds(555, 155, 317, 37); 
		panel.add(dateChooser);
		
        JLabel lblNewLabel_4_1 = new JLabel("Today's Topic");
        lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4_1.setBounds(249, 277, 197, 37);
        panel.add(lblNewLabel_4_1);
        
        JTextArea textArea = new JTextArea();
        textArea.setBounds(555, 285, 328, 116);
        panel.add(textArea);
        
        ArrayList<String> s = this.getComboList("subject");
		String[] sArray = s.toArray(new String[0]);
        JComboBox<String> comboBox = new JComboBox<>(sArray);
        comboBox.setBounds(557, 225, 326, 27);
        panel.add(comboBox);
        
        JButton btnNewButton_5 = new JButton("Submit");
        btnNewButton_5.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String subject=(String) comboBox.getSelectedItem();
        		Date date = dateChooser.getDate();
        		String topic = textArea.getText();
        		
        		if(!subject.isEmpty()&&!topic.isEmpty()){
        			savetopic(date,subject,topic);
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"date has been saved!");
        			textField_3.setText("");
        			textField_4.setText("");
        			textField_5.setText("");
        			textField_6.setText("");
        		}else{
        			JOptionPane.showMessageDialog(Teacher_dashboard.this,"fill all field");

        		}
        	}
        });
        btnNewButton_5.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_5.setBounds(401, 444, 135, 37);
        panel.add(btnNewButton_5);
        
        JButton btnNewButton_6 = new JButton("Clear");
        btnNewButton_6.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		textArea.setText("");
        	}
        });
        btnNewButton_6.setFont(new Font("Tahoma", Font.BOLD, 16));
        btnNewButton_6.setBounds(624, 444, 146, 37);
        panel.add(btnNewButton_6);
        
        JLabel lblNewLabel_4_1_1 = new JLabel("Subject");
        lblNewLabel_4_1_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.BOLD, 16));
        lblNewLabel_4_1_1.setBounds(249, 215, 197, 37);
        panel.add(lblNewLabel_4_1_1);
        
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
    
    public void initialiseDB()
	{
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
			st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st.executeQuery("select Name, Roll_no from Student");
		}
		catch(SQLException e){e.printStackTrace();}
	}
    
    public void saveInternalmark(String name,String roll,String subject,int mark) {
        try (
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
        	Statement st=conn.createStatement()){
        	String query="create table if not exists internal(st_Name varchar(30),st_Roll varchar(20),subject varchar(20),mark int)";
        	st.executeUpdate(query);	
            PreparedStatement stmt = conn.prepareStatement("insert INTO internal (st_name,st_Roll,subject,mark) VALUES (?,?,?,?)"); {
        	
        	stmt.setString(1, name);
            stmt.setString(2, roll);
            stmt.setString(3, subject);
            stmt.setInt(4, mark);
            stmt.executeUpdate();
			}
        } catch (SQLException ex) {ex.printStackTrace();}
   }
    
   public void saveattendance(String name, String roll, Date date, String sub, String attend) {
        try (
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
        	Statement st=conn.createStatement()){
        	String query="create table if not exists attendence(Name varchar(255),Roll_no varchar(255),date date,subject Varchar(255),attendence VARCHAR(255))";
        	st.executeUpdate(query);	
            PreparedStatement stmt = conn.prepareStatement("insert INTO attendence (Name,Roll_no,date,subject,attendence) VALUES (?,?,?,?,?)");
        	stmt.setString(1, name);
            stmt.setString(2, roll);
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
            stmt.setDate(3, sqlDate);
            stmt.setString(4, sub);
            stmt.setString(5, attend);
            stmt.executeUpdate();
        } catch (SQLException ex) {ex.printStackTrace();}
   }
   
   public void savetopic(Date date, String sub, String topic) {
        try (
        	Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
        	Statement st=conn.createStatement()){
        	String query="create table if not exists topic(date Date,subject varchar(256), topic varchar(300))";
        	st.executeUpdate(query);	
            PreparedStatement stmt = conn.prepareStatement("insert INTO topic VALUES (?,?,?)"); 
        	
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        	stmt.setDate(1, sqlDate);
            stmt.setString(2, sub);
            stmt.setString(3, topic);
            stmt.executeUpdate();
        } catch (SQLException ex) {ex.printStackTrace();}
    }
   
    public void initialiseDB1()
	{
		try {conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
			Statement st1 = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = st1.executeQuery("select st_Name, st_Roll, mark from internal");
		}catch(SQLException e){e.printStackTrace();}
	}
}