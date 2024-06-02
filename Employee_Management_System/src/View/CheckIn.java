package View;

import java.awt.Color;  
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.Database;

public class CheckIn extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField id_num;
	private String formattedTime2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckIn frame = new CheckIn();
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
	public CheckIn() {
		setUndecorated(true);
		setResizable(false);

		setBounds(0, 0, 928, 597);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(Color.CYAN, 2));
		contentPane.setBackground(Color.BLACK);
		getContentPane().add(contentPane);

		Database db = new Database();

		JLabel time = new JLabel("00:00");
		time.setBounds(402, 59, 126, 65);
		time.setForeground(Color.CYAN);
		time.setFont(new Font("Times New Roman", Font.BOLD, 50));

		JLabel des = new JLabel("<html>..Click on button bellow to<br> start the count of your shift</br></html>");
		des.setBounds(309, 178, 329, 67);
		des.setVerticalAlignment(SwingConstants.CENTER);
		des.setHorizontalAlignment(SwingConstants.CENTER);
		des.setForeground(Color.CYAN);
		des.setFont(new Font("Times New Roman", Font.PLAIN, 25));

		JLabel checkin = new JLabel("New label");
		checkin.setBounds(321, 374, 298, 51);
		checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin1.png")));
		checkin.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent arg0) {
				
				try {
					String str1 = id_num.getText();

					db.getDbConnection();

					PreparedStatement st1 = Database.getcon().prepareStatement("SELECT id FROM emp_details WHERE id = ?");
					st1.setString(1, str1);

					ResultSet rs1 = st1.executeQuery();

					if (rs1.next()==false) {
						
						JOptionPane.showMessageDialog(null, "You are not a employee");


					} else {

				try {
					String str = id_num.getText();

					db.getDbConnection();

					PreparedStatement st = Database.getcon().prepareStatement("SELECT id FROM attendance WHERE id = ?");
					st.setString(1, str);

					ResultSet rs = st.executeQuery();

					if (rs.next()) {
					
						JOptionPane.showMessageDialog(null, "Already Checked in");

					} else {

						try {
							db.getDbConnection();

							String query = " insert into attendance (id ,in_time ,out_time)" + " values (?,?,?)";

							PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

							preparedStmt.setString(1, id_num.getText());
							preparedStmt.setString(2, formattedTime2);
							preparedStmt.setString(3, null);

							preparedStmt.execute();

							Database.getcon().close();
							
							JOptionPane.showMessageDialog(null, "Checked in");

						} catch (ClassNotFoundException | SQLException en) {
							System.err.println("Got an exception!");
							System.err.println(en.getMessage());
						}

					}
				} catch (Exception ew) {
					System.out.println(ew);
				}
					}
			} catch (Exception ew) {
				System.out.println(ew);
			}
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin2.png")));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin1.png")));

			}

		});

		JLabel exit = new JLabel("X");
		exit.setBounds(893, 12, 23, 27);
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));

		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				Attendance a = new Attendance();
				a.setVisible(true);
				dispose();

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Type your ID hear");
		lblNewLabel_1.setBounds(409, 263, 118, 26);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial Nova", Font.BOLD | Font.ITALIC, 13));

		id_num = new JTextField();
		id_num.setBounds(342, 295, 248, 38);
		id_num.setHorizontalAlignment(SwingConstants.CENTER);
		id_num.setForeground(Color.CYAN);
		id_num.setFont(new Font("Verdana", Font.BOLD, 20));
		id_num.setColumns(10);
		id_num.setBackground(Color.BLACK);
		contentPane.setLayout(null);
		contentPane.add(time);
		contentPane.add(des);
		contentPane.add(checkin);
		contentPane.add(exit);
		contentPane.add(lblNewLabel_1);
		contentPane.add(id_num);

		JLabel bg = new JLabel();
		bg.setBorder(new MatteBorder(2, 2, 2, 2, (Color) Color.CYAN));
		bg.setBounds(0, 0, 928, 597);
		bg.setIcon(new ImageIcon(Login.class.getResource("/res/grade_bg.png")));
		contentPane.add(bg);

		// Create a Timer to update the time every second
		Timer timer = new Timer(true);
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				// Get the current time
				Date currentTime = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

				String formattedTime = sdf.format(currentTime);
				formattedTime2 = sdf2.format(currentTime);

				// Update the timeLabel with the current time
				time.setText(formattedTime);
			}
		}, 0, 1000);
	}
}
