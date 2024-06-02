package View;

import java.awt.EventQueue;  

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.MatteBorder;

public class Login_s {

	private JFrame frame;
	private JTextField t_user;
	private JPasswordField t_pass;
	private JLabel error_label;
	String date_filtered;
	private JLabel login_btn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_s window = new Login_s();
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
	public Login_s() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setAutoRequestFocus(false);
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBackground(Color.BLACK);
		frame.setResizable(false);
		frame.setBounds(0, 0, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);

		frame.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		frame.getContentPane().setLayout(null);


		
		

		JLabel user = new JLabel("User Name");
		user.setBounds(608, 299, 130, 35);
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		frame.getContentPane().add(user);

		t_user = new JTextField();
		t_user.setBounds(607, 336, 313, 35);
		t_user.setFont(new Font("Times New Roman", Font.BOLD, 20));
		t_user.setForeground(Color.WHITE);
		t_user.setDisabledTextColor(Color.WHITE);
		t_user.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		t_user.setOpaque(false);
		frame.getContentPane().add(t_user);
		t_user.setColumns(10);
		t_user.requestFocus();


		JLabel exit = new JLabel("X");
		exit.setBounds(967, 10, 23, 27);
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				int confirmed = JOptionPane.showConfirmDialog(frame, "Do you want to exit the application?",
						"Exit Confirmation", JOptionPane.YES_NO_OPTION);
				if (confirmed == JOptionPane.YES_OPTION) {
					System.exit(0);

				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		exit.setForeground(Color.WHITE);
		frame.getContentPane().add(exit);

		JLabel pic_1 = new JLabel("New label");
		pic_1.setBounds(715, 108, 100, 100);
		pic_1.setIcon(new ImageIcon(Login_s.class.getResource("/res/1.png")));
		frame.getContentPane().add(pic_1);

		t_pass = new JPasswordField();
		t_pass.setBounds(607, 434, 313, 35);
		t_pass.setOpaque(false);
		t_pass.setForeground(Color.WHITE);
		t_pass.setFont(new Font("Times New Roman", Font.BOLD, 20));
		t_pass.setDisabledTextColor(Color.WHITE);
		t_pass.setColumns(10);
		t_pass.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(255, 255, 255)));
		frame.getContentPane().add(t_pass);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(608, 401, 130, 35);
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 17));
		frame.getContentPane().add(lblPassword);

		login_btn = new JLabel();
		login_btn.setBounds(608, 526, 324, 68);
		login_btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				login_btn.setIcon(new ImageIcon(Login_s.class.getResource("/res/b1.png")));
				login_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				login_btn.setIcon(new ImageIcon(Login_s.class.getResource("/res/b.png")));
			}

			@Override
			public void mouseClicked(MouseEvent e) {

				String user_text = t_user.getText();
				@SuppressWarnings("deprecation")
				String pass_text = t_pass.getText();

				if (user_text.isEmpty() || pass_text.isEmpty()) {
					error_label.setText("User Name or Password cannot be empty");

				} else {
					Connection con;
					Statement st;

					try {
						// String str = i_in.getText();
						Class.forName("com.mysql.jdbc.Driver");
						con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "");
						st = con.createStatement();
						@SuppressWarnings("deprecation")
						String sql = "SELECT* FROM login WHERE user ='" + t_user.getText() + "'AND password ='"
								+ t_pass.getText().toString() + "'";

						ResultSet rs = st.executeQuery(sql);

						if (rs.next()) {

							
								SignUpAdmin.main(null);
								
							JOptionPane.showMessageDialog(null, "Login Sucessfully");
							frame.dispose();
							error_label.setText("");

						} else {
							error_label.setText("");
							JOptionPane.showMessageDialog(null, "Incorrect user name or password");
							t_user.setText("");
							t_pass.setText("");
							t_user.requestFocus();
							con.close();

						}
					} catch (Exception ew) {
						System.out.println(ew);
					}

				}
			}
		});
		login_btn.setIcon(new ImageIcon(Login_s.class.getResource("/res/b.png")));
		login_btn.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(login_btn);

		error_label = new JLabel("");
		error_label.setBounds(608, 495, 312, 21);
		error_label.setFont(new Font("Arial", Font.BOLD, 12));
		error_label.setForeground(Color.RED);
		frame.getContentPane().add(error_label);

		JLabel sign = new JLabel("Sign In");
		sign.setBounds(715, 212, 100, 77);
		sign.setForeground(Color.WHITE);
		sign.setFont(new Font("Verdana Pro", Font.BOLD, 24));
		sign.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(sign);
		
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 1000, 700);
		bg.setIcon(new ImageIcon(Login_s.class.getResource("/res/login_bg.png")));
		frame.getContentPane().add(bg);


	}
}
