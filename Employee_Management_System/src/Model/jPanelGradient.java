package Model;

import java.awt.Color; 
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

//gradient color
public class jPanelGradient extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void paintComponent (Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	//GroupLayout groupLayout = new GroupLayout(getRootPane());
	int width = 500;
	int height = Short.MAX_VALUE;
	
//GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
	Color colorl = Color.decode("#000046");
	Color color2 = Color.decode("#1CB5E0");
	GradientPaint gp = new GradientPaint (150, 180, colorl, 180, 600, color2) ;
	g2d.setPaint (gp) ;
	g2d.fillRect (0, 0, width, height);
	}
}