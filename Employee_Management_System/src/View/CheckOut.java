package View;

import java.awt.Color;  
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import Controller.Database;

import javax.swing.LayoutStyle.ComponentPlacement;

public class CheckOut extends JFrame {

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
					CheckOut frame = new CheckOut();
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
	public CheckOut() {
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
		time.setForeground(Color.CYAN);
		time.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		JLabel des = new JLabel("<html>Click on button bellow to end your shift</html>");
		des.setVerticalAlignment(SwingConstants.CENTER);
		des.setHorizontalAlignment(SwingConstants.CENTER);
		des.setForeground(Color.CYAN);
		des.setFont(new Font("Times New Roman", Font.PLAIN, 25));
		
		id_num = new JTextField();
		id_num.setHorizontalAlignment(SwingConstants.CENTER);
		id_num.setForeground(Color.CYAN);
		id_num.setFont(new Font("Verdana", Font.BOLD, 20));
		id_num.setColumns(10);
		//textField.setBorder(new MatteBorder(0, 0, 2, 0, (Color) Color.WHITE));
		id_num.setBackground(Color.BLACK);
		
		
		JLabel checkout = new JLabel("New label");
		checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout1.png")));
		checkout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				String str = id_num.getText();

				try {
				    db.getDbConnection();

				    // Prepare the SELECT statement with a PreparedStatement
				    PreparedStatement st = Database.getcon().prepareStatement("SELECT out_time FROM attendance WHERE id = ?");
				    st.setString(1, str);
				    ResultSet rs = st.executeQuery();

				    if (rs.next()) {
				        String out = rs.getString("out_time");
				        if (out == null) {
				            // If "out_time" is null, update the row
				            PreparedStatement pst = Database.getcon().prepareStatement("UPDATE attendance SET out_time = ? WHERE id = ?");
				            pst.setString(1, formattedTime2);
				            pst.setString(2, str);
				            int rowsUpdated = pst.executeUpdate();
				            
				            if (rowsUpdated > 0) {
				                JOptionPane.showMessageDialog(null, "Checked Out");
				            } else {
				                JOptionPane.showMessageDialog(null, "Data not Updated");
				            }
				        } else {
				            JOptionPane.showMessageDialog(null, "Already Checked out");
				        }
				    } else {
				        JOptionPane.showMessageDialog(null, "Invalid ID");
				    }
				} catch (Exception e) {
				    e.printStackTrace();
				}

			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout1.png")));

			}
		});
		
		JLabel exit = new JLabel("X");
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Attendance at = new Attendance();
				at.setVisible(true);
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Type your ID hear");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Arial Nova", Font.BOLD | Font.ITALIC, 13));
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(400)
					.addComponent(time, GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
					.addGap(398))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(319)
					.addComponent(checkout, GroupLayout.PREFERRED_SIZE, 298, Short.MAX_VALUE)
					.addGap(307))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(891, Short.MAX_VALUE)
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(265)
					.addComponent(des, GroupLayout.PREFERRED_SIZE, 411, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(248, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(341)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
						.addComponent(id_num, GroupLayout.PREFERRED_SIZE, 248, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(335, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(20)
					.addComponent(time, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(56)
					.addComponent(des, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(id_num, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
					.addGap(58)
					.addComponent(checkout, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.addGap(170))
		);
		contentPane.setLayout(gl_contentPane);
		
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
