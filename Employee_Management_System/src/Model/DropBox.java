package Model;

import java.awt.Color; 
import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.MatteBorder;
import javax.swing.JComboBox;

public class DropBox extends JPanel {

	private static final long serialVersionUID = 1L;
	public JLabel title;
	public JComboBox<String> text;

	/**
	 * Create the panel.
	 */
public DropBox(String[] options,String str,int x,int y) {
		
		setBackground(Color.WHITE);
		
		title = new JLabel();
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		text = new JComboBox<String>(options);
		text.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		text.setFont(new Font("Times New Roman", Font.PLAIN, 20));
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
