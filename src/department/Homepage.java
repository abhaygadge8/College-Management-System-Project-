package department;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Homepage extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
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
	public Homepage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1277, 697);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Sitka Text", Font.PLAIN, 60));
		lblNewLabel.setBounds(420, 36, 309, 63);
		contentPane.add(lblNewLabel);
		
		JLabel lblDepartmentOfMathematics = new JLabel("Department OF MATHEMATICS");
		lblDepartmentOfMathematics.setFont(new Font("Sitka Text", Font.PLAIN, 45));
		lblDepartmentOfMathematics.setBounds(281, 95, 661, 63);
		contentPane.add(lblDepartmentOfMathematics);
		
		JButton btnNewButton = new JButton("ADMIN");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnNewButton.setFont(new Font("Sitka Text", Font.PLAIN, 25));
		btnNewButton.setBounds(443, 244, 261, 69);
		contentPane.add(btnNewButton);
		
		JButton btnTeacher = new JButton("TEACHER");
		btnTeacher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnTeacher.setFont(new Font("Sitka Text", Font.PLAIN, 25));
		btnTeacher.setBounds(443, 337, 261, 69);
		contentPane.add(btnTeacher);
		
		JButton btnStudent = new JButton("STUDENT");
		btnStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		btnStudent.setFont(new Font("Sitka Text", Font.PLAIN, 25));
		btnStudent.setBounds(443, 442, 261, 69);
		contentPane.add(btnStudent);
	}
}