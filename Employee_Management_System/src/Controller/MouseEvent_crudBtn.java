package Controller;

import java.awt.event.MouseAdapter; 
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MouseEvent_crudBtn {
	
public void operation(String mouseEntered,String mouseExited,JLabel btn) {
	
	btn.addMouseListener(new MouseAdapter() {

		@Override
		public void mouseEntered(MouseEvent e) {
			btn.setIcon(new ImageIcon(getClass().getResource("/res/"+mouseEntered)));

			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btn.setIcon(new ImageIcon(getClass().getResource("/res/"+mouseExited)));

		}
	});
}

}
