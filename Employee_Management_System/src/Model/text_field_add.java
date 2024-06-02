package Model;

import java.awt.Font;  

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;

import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class text_field_add extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel title;
	public JTextField text;
	/** 
	 * Create the panel.
	 */
	public text_field_add(String str,int x,int y) {
		
		setBackground(Color.WHITE);
		
		title = new JLabel();
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		text = new JTextField();
		text.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		text.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		text.setText("");
		text.setColumns(10);
		title.setText(str);
		setBounds(x, y, 457,32);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(text, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(text, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)))
					.addGap(35))
		);
		setLayout(groupLayout);
		
	}


}
