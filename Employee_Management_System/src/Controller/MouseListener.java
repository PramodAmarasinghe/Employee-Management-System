package Controller;

import java.awt.Color; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MouseListener {

	public static void mouseEvent(JPanel main_panel,JPanel select,JPanel new_panel){

		select.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
						
				main_panel.removeAll(); // Remove all components from panel
				main_panel.add(new_panel); // Add the new panel q
				main_panel.getParent().revalidate(); // Revalidate panel_2 to reflect changes
				main_panel.getParent().repaint(); // Repaint panel_2 to ensure it's updated
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				select.setBackground(new Color(255, 255, 255, 50));
				select.setOpaque(true);
				select.getParent().repaint();

				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			
				
				select.setBackground(null);
				select.setOpaque(false);
				select.getParent().repaint();
			
			}
			
			
		});
		
		
		
		
	}
	
	public static void exit(JPanel select){

		select.addMouseListener(new MouseAdapter() {

			
			@Override
			public void mouseEntered(MouseEvent e) {
				select.setBackground(new Color(255, 255, 255, 50));
				select.setOpaque(true);
				select.getParent().repaint();

				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
			
				
				select.setBackground(null);
				select.setOpaque(false);
				select.getParent().repaint();
			
			}
			
			
		});
}
}
