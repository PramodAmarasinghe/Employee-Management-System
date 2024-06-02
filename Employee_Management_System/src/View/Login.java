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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

import Controller.Database;

public class Login {

	private JFrame frame;
	private JTextField t_user;
	private JPasswordField t_pass;
	private JLabel error_label;
	String date_filtered;
	private JLabel login_btn;
	static String user_string, userr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
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
	public Login() {
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

		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setBounds(173, 59, 185, 77);
		lblNewLabel.setFont(new Font("Verdana Pro", Font.BOLD, 30));
		lblNewLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel);

		JLabel pic_1 = new JLabel("New label");
		pic_1.setBounds(715, 108, 100, 100);
		pic_1.setIcon(new ImageIcon(Login.class.getResource("/res/1.png")));
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
				login_btn.setIcon(new ImageIcon(Login.class.getResource("/res/b1.png")));
				login_btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				login_btn.setIcon(new ImageIcon(Login.class.getResource("/res/b.png")));
			}

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {

				String user_text = t_user.getText();
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
						String sql = "SELECT* FROM login WHERE user ='" + t_user.getText() + "'AND password ='"
								+ t_pass.getText().toString() + "'";

						ResultSet rs = st.executeQuery(sql);

						if (rs.next()) {

							Home_pg hp = new Home_pg();
							hp.setVisible(true);

							user_string = "Welcome, " + user.getText();

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
		login_btn.setIcon(new ImageIcon(Login.class.getResource("/res/b.png")));
		login_btn.setHorizontalAlignment(JLabel.CENTER);
		frame.getContentPane().add(login_btn);

		JLabel forget_pass = new JLabel("<html><u>Forget password ?</u></html>");
		forget_pass.setVisible(false);
		forget_pass.setBounds(618, 604, 302, 35);
		forget_pass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {

				forget_pass.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

			}
		});

		forget_pass.setForeground(Color.WHITE);
		forget_pass.setHorizontalAlignment(JLabel.CENTER);
		forget_pass.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame.getContentPane().add(forget_pass);

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

		JLabel emp_int = new JLabel("Click hear to Employee User Interface");
		emp_int.setBounds(44, 607, 392, 35);
		emp_int.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				emp_int.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				Attendance a = new Attendance();

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				String currentTime = sdf.format(new Date());

				Database db = new Database();

				try {
					// String str1 = id_num.getText();

					db.getDbConnection();

					Statement st = Database.getcon().createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM attendance");

					if (rs.next()) {
						String dbdate = rs.getString("in_time");
						date_filtered = dbdate.substring(0, 10);
					}

					if (currentTime.equals(date_filtered)) {
					} else {

						try {
							db.getDbConnection();
							Statement stt = Database.getcon().createStatement();

							ResultSet rss = stt.executeQuery("SELECT * FROM attendance");
							PreparedStatement pss = Database.getcon().prepareStatement(
									" insert into attend_history (id ,in_time ,out_time)" + " values (?,?,?)");

							// System.out.println("UPDATED");

							while (rss.next()) {

								pss.setString(1, rss.getString("id"));
								pss.setString(2, rss.getString("in_time"));
								pss.setString(3, rss.getString("out_time"));

								pss.execute();

								System.out.println("UPDATED");

							}
							rss.close();
							pss.close();
							// statement.close();
							Database.getcon().close();

						} catch (Exception ee) {
							ee.printStackTrace();
						}

						try {

							db.getDbConnection();

							java.sql.Statement stmt = Database.getcon().createStatement();

							String sql = ("DELETE FROM attendance");

							int delete = stmt.executeUpdate(sql);

							stmt.close();
							Database.getcon().close();

						} catch (Exception eo) {
							System.out.println(eo);
						}

					}

					// ResultSet rs1 = st1.executeQuery();

					// if (rs1.next() == false) {

					// JOptionPane.showMessageDialog(null, "You are not a employee");
					// }
				} catch (Exception ew) {
					System.out.println(ew);
				}

				a.setVisible(true);
				frame.dispose();
			}
		});
		emp_int.setHorizontalAlignment(SwingConstants.CENTER);
		emp_int.setForeground(Color.WHITE);
		emp_int.setFont(new Font("Verdana", Font.BOLD, 15));
		frame.getContentPane().add(emp_int);

		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 1000, 700);
		bg.setIcon(new ImageIcon(Login.class.getResource("/res/login_bg.png")));
		frame.getContentPane().add(bg);

	}

	public static void setUser() {
		userr = user_string;
	}

	public static String name() {
		return userr;
	}
}
