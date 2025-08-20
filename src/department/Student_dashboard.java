package department;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Student_dashboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel contentDisplayPanel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                Student_dashboard frame = new Student_dashboard("2");
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String getStudentNameFromDatabase(String rollNo) {
        String studentName = "";
        String dbURL = "jdbc:mysql://localhost:3306/Ideathon_db"; 
        String user = "root"; 
        String pass = "Snehal@16"; 

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection(dbURL, user, pass)) {
                String query = "SELECT name FROM Student WHERE roll_no = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, rollNo);
                    ResultSet rs = pstmt.executeQuery();

                    if (rs.next()) {
                        studentName = rs.getString("Name"); 
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return studentName;
    }

    public Student_dashboard(String roll_no) {
        setTitle("Student Dashboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel studentNameLabel = new JLabel("Welcome, Loading...", SwingConstants.RIGHT);
        studentNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        topPanel.add(studentNameLabel, BorderLayout.EAST);
        contentPane.add(topPanel, BorderLayout.NORTH);

        String Name = this.getStudentNameFromDatabase(roll_no);
        studentNameLabel.setText("Welcome " + Name + " !");

        JSplitPane splitPane = new JSplitPane();
        splitPane.setDividerLocation(180);
        contentPane.add(splitPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 1, 10, 15)); // Updated spacing between buttons
        splitPane.setLeftComponent(buttonPanel);

        JButton btnViewAttendance = new JButton("View Attendance");
        btnViewAttendance.setFont(new Font("Sitka Text", Font.PLAIN, 18));
        buttonPanel.add(btnViewAttendance);

        JButton btnViewMarks = new JButton("View Marks");
        btnViewMarks.setFont(new Font("Sitka Text", Font.PLAIN, 18));
        buttonPanel.add(btnViewMarks);

        JButton btnViewTopics = new JButton("View Daily Topics ");
        btnViewTopics.setToolTipText("");
        btnViewTopics.setFont(new Font("Sitka Text", Font.PLAIN, 16));
        buttonPanel.add(btnViewTopics);

        JButton btnFeedback = new JButton("Feedback/Complaints");
        btnFeedback.setFont(new Font("Sitka Text", Font.PLAIN, 18));
        buttonPanel.add(btnFeedback);

        contentDisplayPanel = new JPanel();
        contentDisplayPanel.setLayout(new BorderLayout());
        splitPane.setRightComponent(contentDisplayPanel);

        JLabel defaultLabel = new JLabel("Select an option from the left panel", SwingConstants.CENTER);
        contentDisplayPanel.add(defaultLabel, BorderLayout.CENTER);

        btnViewAttendance.addActionListener(e -> updateAttendanceContent(roll_no));
        btnViewMarks.addActionListener(e -> updateMarksContent(roll_no)); 
        btnViewTopics.addActionListener(e -> updateTopicsContent(roll_no));
        btnFeedback.addActionListener(e -> displayFeedbackPanel());
    }

    private void updateContent(String message) {
        contentDisplayPanel.removeAll();
        JLabel contentLabel = new JLabel(message, SwingConstants.CENTER);
        contentDisplayPanel.add(contentLabel, BorderLayout.CENTER);
        contentDisplayPanel.revalidate();
        contentDisplayPanel.repaint();
    }

    private void updateAttendanceContent(String r) {
        contentDisplayPanel.removeAll();
        int totalAttended = 0, cnt = 0;
        String[] columnNames = {"Subject", "Total Classes", "Classes Attended", "Attendance %"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "root");
             Statement st = conn.createStatement();
             ResultSet rs1 = st.executeQuery("select subject, count(attendence) from attendence where roll_no = \"" + r + "\" and attendence=\"present\" group by subject")) {
            while (rs1.next()) {
                totalAttended = totalAttended + rs1.getInt(2);
                cnt += 1;
                tableModel.addRow(new Object[]{rs1.getString(1), 60, rs1.getInt(2), rs1.getInt(2)/60});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable attendanceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        double overallPercentage = (totalAttended * 100.0) / (cnt * 60);

        JLabel overallAttendanceLabel = new JLabel(
            String.format("Overall Attendance: %.2f%%", overallPercentage),
            SwingConstants.CENTER
        );
        overallAttendanceLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        JLabel graphLabel = new JLabel("[Graph Placeholder]", SwingConstants.CENTER);
        graphLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
        graphLabel.setForeground(Color.GRAY);

        contentDisplayPanel.setLayout(new BorderLayout());
        contentDisplayPanel.add(tablePanel, BorderLayout.CENTER);
        contentDisplayPanel.add(overallAttendanceLabel, BorderLayout.NORTH);
        contentDisplayPanel.add(graphLabel, BorderLayout.SOUTH);

        contentDisplayPanel.revalidate();
        contentDisplayPanel.repaint();
    }

    private void updateMarksContent(String r) {
        contentDisplayPanel.removeAll();

        String[] columnNames = {"Subject", "Maximum Marks", "Marks Obtained", "Grade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
             Statement st = conn.createStatement();
             ResultSet rs1 = st.executeQuery("select subject, sum(mark) from internal where st_roll = \"" + r + "\" group by subject")) {
            while (rs1.next()) {
                tableModel.addRow(new Object[]{rs1.getString(1), 100, rs1.getInt(2), "A"});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JTable marksTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(marksTable);

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        contentDisplayPanel.setLayout(new BorderLayout());
        contentDisplayPanel.add(tablePanel, BorderLayout.CENTER);

        contentDisplayPanel.revalidate();
        contentDisplayPanel.repaint();
    }

    private void updateTopicsContent(String roll_no) {
        contentDisplayPanel.removeAll();

        JPanel topicsPanel = new JPanel();
        topicsPanel.setLayout(new BorderLayout(10, 10));

        JComboBox<String> subjectComboBox = new JComboBox<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT DISTINCT subject FROM topic")) {
            while (rs.next()) {
                subjectComboBox.addItem(rs.getString("subject"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        JLabel subjectLabel = new JLabel("Subject:");
        topPanel.add(subjectLabel);
        topPanel.add(subjectComboBox);

        JTable topicsTable = new JTable(new DefaultTableModel(new Object[]{"Date", "Topics"}, 0));
        DefaultTableModel tableModel = (DefaultTableModel) topicsTable.getModel();
        JScrollPane scrollPane = new JScrollPane(topicsTable);

        subjectComboBox.addActionListener(e -> {
            String selectedSubject = (String) subjectComboBox.getSelectedItem();
            if (selectedSubject != null) {
                tableModel.setRowCount(0);
                try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ideathon_db", "root", "Snehal@16");
                     PreparedStatement pstmt = conn.prepareStatement(
                         "SELECT date, topic FROM topic WHERE subject = ?")) {
                    pstmt.setString(1, selectedSubject);
                    ResultSet rs = pstmt.executeQuery();
                    while (rs.next()) {
                        tableModel.addRow(new Object[]{rs.getDate("date"), rs.getString("topic")});
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        topicsPanel.add(topPanel, BorderLayout.NORTH);
        topicsPanel.add(scrollPane, BorderLayout.CENTER);

        contentDisplayPanel.setLayout(new BorderLayout());
        contentDisplayPanel.add(topicsPanel, BorderLayout.CENTER);

        contentDisplayPanel.revalidate();
        contentDisplayPanel.repaint();
    }

    private void displayFeedbackPanel() {
        contentDisplayPanel.removeAll();

        // Create feedback components
        JTextArea feedbackArea = new JTextArea(10, 40);
        feedbackArea.setWrapStyleWord(true);
        feedbackArea.setLineWrap(true);
        feedbackArea.setFont(new Font("Tahoma", Font.PLAIN, 14));
        feedbackArea.setText("Please type your Feedback/Complaints here!");

        JScrollPane scrollPane = new JScrollPane(feedbackArea);
        scrollPane.setBounds(29, 22, 491, 322);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel feedbackPanel = new JPanel();
        feedbackPanel.setLayout(null);
        feedbackPanel.add(scrollPane);

        JButton submitButton = new JButton("Submit Feedback");
        submitButton.setBounds(113, 369, 378, 25);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String feedback = feedbackArea.getText();
                if (!feedback.isEmpty()) {
                    addfeedback(feedback);
                    JOptionPane.showMessageDialog(contentPane, "Feedback submitted successfully!");
                    feedbackArea.setText(""); // Clear feedback text area
                } else {
                    JOptionPane.showMessageDialog(contentPane, "Please enter feedback before submitting.");
                }
            }
        });
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        feedbackPanel.add(submitButton);

        contentDisplayPanel.setLayout(new BorderLayout());
        contentDisplayPanel.add(feedbackPanel, BorderLayout.CENTER);

        contentDisplayPanel.revalidate();
        contentDisplayPanel.repaint();
    }

    private void addfeedback(String feedback) {
        String dbURL = "jdbc:mysql://localhost:3306/Ideathon_db"; 
        String user = "root"; 
        String pass = "Snehal@16"; 

        try (Connection conn = DriverManager.getConnection(dbURL, user, pass)) {
            String query = "INSERT INTO feedback (feedback) VALUES (?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, feedback);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}