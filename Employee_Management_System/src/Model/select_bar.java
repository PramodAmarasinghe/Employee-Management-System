package Model;

import java.awt.Color; 
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class select_bar extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public select_bar(String str) {
		setOpaque(false);
		setBounds(0, 0, 275, 95);
		setLayout(null);
		
		JLabel name = new JLabel(str); 
		name.setBounds(0, 0, 275, 95);
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setVerticalAlignment(SwingConstants.CENTER);
		name.setForeground(Color.WHITE);
		name.setFont(new Font("Times New Roman", Font.BOLD, 30));
		add(name);

	}

}
