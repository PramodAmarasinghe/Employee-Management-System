package Model;

import javax.swing.JPanel; 
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class home_bg extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public home_bg() {

		setBounds(0, 0, 1375, 800);
		setLayout(null);
		
		JLabel bg = new JLabel();
		bg.setBounds(0, 0, 1375, 800);
		bg.setIcon(new ImageIcon(home_bg.class.getResource("/res/homebgnew.png")));
		add(bg);
		
	}
}
