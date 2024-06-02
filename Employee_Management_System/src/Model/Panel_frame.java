package Model;

import javax.swing.JPanel; 
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;

public class Panel_frame extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public Panel_frame(String title, int x,int y,int w,int h) {
		//setBackground(Color.WHITE);
		
		setBounds(x,y,w,h);
		setBorder(new TitledBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 255)), ""+title, TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
		setBackground(null);
		setLayout(null);
		
			
		
	}
}
