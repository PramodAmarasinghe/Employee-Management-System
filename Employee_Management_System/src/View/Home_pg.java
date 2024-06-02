package View;

import java.awt.Color;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.MouseListener;
import Model.Employee_Details;
import Model.Leave_request_1;
import Model.Project_Table;
import Model.Salary_History;
import Model.TopBar;
import Model.home_bg;
import Model.jPanelGradient;
import Model.select_bar;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;


public class Home_pg extends JFrame {


	private static final long serialVersionUID = 1L;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home_pg frame = new Home_pg();
					frame.setVisible(true);

				} catch (Exception e) { 
					e.printStackTrace();
				}
			}
		});
	}

/**
	 * Create the frame.
	 */
	public Home_pg() {
		setUndecorated(true);
		setBackground(new Color(0, 204, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1650, 900);
		setLocationRelativeTo(null);
		
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLUE, 2)); // You can customize the color and thickness
        
		select_bar employee = new select_bar("Employee Details");
		select_bar salary = new select_bar("Salary History");
		select_bar project = new select_bar("Project Details");
		select_bar leave = new select_bar("Leave Requests");
		select_bar about = new select_bar("About");
		select_bar exit = new select_bar("Log Out");
		select_bar admin = new select_bar("Admin SignUp");


		
		getContentPane().setLayout(null);
		
		TopBar top = new TopBar();
		top.setBounds(275, 0, 1375, 100);
		getContentPane().add(top);
		
		jPanelGradient select_panel = new jPanelGradient();
		select_panel.setBounds(0, 0, 275, 900);
		getContentPane().add(select_panel);
		select_panel.setLayout(null);
		
		select_panel.add(employee);
		employee.setBounds(0,100,275,100);
		
		select_panel.add(salary);
		salary.setBounds(0,200,275,100);
		
		select_panel.add(project);
		project.setBounds(0,300,275,100);
		
		select_panel.add(leave);
		leave.setBounds(0,400,275,100);
		
		select_panel.add(about);
		about.setBounds(0,500,275,100);
		
		select_panel.add(exit);
		exit.setBounds(0,600,275,100);
		
		select_panel.add(admin);
		admin.setBounds(0,700,275,100);
		
		JPanel panel = new JPanel();
		panel.setBounds(275, 100, 1375, 800);
	//	save.setIcon(new ImageIcon(getClass().getResource("/res/save.png")));
		getContentPane().add(panel);

		Employee_Details add_panel = new Employee_Details();
		MouseListener.mouseEvent(panel,employee,add_panel);
		
		Leave_request_1 leave_panel = new Leave_request_1();
		MouseListener.mouseEvent(panel,leave,leave_panel);
		
		home_bg about_panel = new home_bg();
		MouseListener.mouseEvent(panel,about,about_panel);
		
		Project_Table project_panel = new Project_Table();
		MouseListener.mouseEvent(panel,project,project_panel);
		
		Salary_History salary_panel = new Salary_History();
		MouseListener.mouseEvent(panel,salary,salary_panel);
		
		home_bg hb = new home_bg();
		panel.add(hb);
		
		MouseListener.exit(admin);
		admin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login_s.main(null);

				dispose();
			}
		});

		panel.setLayout(null);
		MouseListener.exit(exit);
		
		exit.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
						
				int confirmed= JOptionPane.showConfirmDialog(panel, "Do you want to log out?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);				
				if (confirmed == JOptionPane.YES_OPTION) {
					dispose();
                    Login.main(null);
                    
                }
			}
			
		});
		
		/*Login lg = new Login();
		
		String str = Login.name();
		Login.setUser();
		top.welcome.setText(str);*/
		}
	
}



