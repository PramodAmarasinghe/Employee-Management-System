package View;

import java.awt.EventQueue; 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Attendance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Attendance frame = new Attendance();
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
	public Attendance() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 928, 597);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new LineBorder(Color.CYAN, 2));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		JLabel checkin = new JLabel();
		checkin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin1.png")));

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CheckIn ci = new CheckIn();
				ci.setVisible(true);
				dispose();
				
				
				
				
			}
		});
		checkin.setIcon(new ImageIcon(Login.class.getResource("/res/checkin1.png")));
		
		JLabel checkout = new JLabel();
		
		checkout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout2.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout1.png")));

			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				CheckOut co = new CheckOut();
				co.setVisible(true);
				dispose();
				
				
				
			}
		});
		checkout.setIcon(new ImageIcon(Login.class.getResource("/res/checkout1.png")));
		
		JLabel exit = new JLabel("X");
		exit.setForeground(Color.WHITE);
		exit.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		exit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				Login.main(null);
				dispose();
				
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			}
		});
		
		JLabel time = new JLabel("00:00");
		time.setForeground(Color.CYAN);
		time.setFont(new Font("Times New Roman", Font.BOLD, 50));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(891, Short.MAX_VALUE)
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(307)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(checkout, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
						.addComponent(checkin, GroupLayout.PREFERRED_SIZE, 295, Short.MAX_VALUE))
					.addGap(322))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(196, Short.MAX_VALUE)
					.addComponent(time, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
					.addGap(192))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(exit, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(62)
					.addComponent(time, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(63)
					.addComponent(checkin)
					.addGap(43)
					.addComponent(checkout, GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
					.addGap(227))
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
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm a - yyyy MMM dd");
                String formattedTime = sdf.format(currentTime);

                // Update the timeLabel with the current time
                time.setText(formattedTime);
            }
        }, 0, 1000);
		
	}
}
