package Model;

import javax.swing.GroupLayout;  
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import com.toedter.calendar.JTextFieldDateEditor;
import com.toedter.calendar.JDateChooser;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

public class DatePicker extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel title;
	JDateChooser dateChooser;
	/** 
	 * Create the panel.
	 */
	public DatePicker(String str,int x,int y) {
		
		setBackground(Color.WHITE);
		
		title = new JLabel();
		title.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(128, 128, 128)));
		dateChooser.setBounds(0, 30,221, 23);
		JTextFieldDateEditor dateEditor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		dateEditor.setEditable(false);
		add(dateChooser);
		title.setText(str);

		
		setBounds(x, y, 457,32);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(title, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(90, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(dateChooser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(2)
							.addComponent(title, GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)))
					.addGap(35))
		);
		setLayout(groupLayout);
		
	}
	
	public Date getter() {
		
		 if (dateChooser.getDate() == null) {
	            System.out.println("DateChooser is empty");
	        } else {
	            // A date is selected
	            System.out.println("DateChooser has a selected date");
	        }
		 
		return dateChooser.getDate();
	}
}
