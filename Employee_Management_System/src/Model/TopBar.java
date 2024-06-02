package Model;

import java.awt.Color; 

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TopBar extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel welcome;

	/**
	 * Create the panel.
	 */
	public TopBar() {
		setBackground(new Color(0, 0, 70));
		setBounds(new Rectangle(0, 0, 1375, 100));
		setLayout(null);
		
		welcome = new JLabel("New label");
		welcome.setVisible(false);
		welcome.setHorizontalAlignment(SwingConstants.LEFT);
		welcome.setForeground(Color.WHITE);
		welcome.setFont(new Font("Times New Roman", Font.BOLD, 36));
		welcome.setBounds(28, 10, 600, 80);
		add(welcome);
		
		JLabel time = new JLabel("New label");
		time.setHorizontalAlignment(SwingConstants.RIGHT);
		time.setForeground(Color.WHITE);
		time.setFont(new Font("Times New Roman", Font.BOLD, 36));
		time.setBounds(760, 10, 576, 80);
		add(time);

	// Create a Timer to update the time every second
    Timer timer = new Timer(true);
    timer.scheduleAtFixedRate(new TimerTask() {
        @Override
        public void run() {
            // Get the current time
            Date currentTime = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy   HH:mm:ss");
            String formattedTime = sdf.format(currentTime);

            // Update the timeLabel with the current time
            time.setText(formattedTime);
        }
    }, 0, 1000);
}
	}
