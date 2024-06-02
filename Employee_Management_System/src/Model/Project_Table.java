package Model;

import javax.swing.JPanel;   
import Controller.Database;
import Controller.DayFilter;
import Controller.MouseEvent_crudBtn;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.Font;



public class Project_Table extends JPanel {

	private static final long serialVersionUID = 1L;
	private text_field_add pid;
	private text_field_add pname;
	private DatePicker sdate;
	private DatePicker edate;
	private text_field_add pmanager;
	private text_field_add des;;
	private text_field_add status;
	private text_field_add budget;
	private text_field_add cos;
	private text_field_add location;

	private JLabel save;
	private JLabel search;
	private JLabel update;
	private JLabel delete;
	private JLabel clear;

	/**
	 * Create the panel.
	 */
	public Project_Table() {

		setBackground(Color.WHITE);
		setBounds(0, 0, 1375, 800);
		setLayout(null);

		Database db = new Database();
		MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();


		pid = new text_field_add("Project ID", 95, 135);
		pname = new text_field_add("Project Name", 95, 235);
		sdate = new DatePicker("Start Date", 95, 535);
		edate = new DatePicker("End Date", 95, 635);
		pmanager = new text_field_add("Project Manager", 650, 135);
		des = new text_field_add("Description", 95, 335);
		status = new text_field_add("Status", 650, 435);
		budget = new text_field_add("Budget $", 95, 435);
		cos = new text_field_add("Client Name", 650, 235);
		location = new text_field_add("Location", 650, 335);

		add(pid);
		add(pname);
		add(sdate);
		add(edate);
		add(pmanager);
		add(des);
		add(status);
		add(budget);
		add(cos);
		add(location);

		Panel_frame p = new Panel_frame("Project info", 80, 115, 490, 370);
		add(p);
		
		Panel_frame p1 = new Panel_frame("Duration", 80, 520, 490, 165);
		add(p1);
		
		Panel_frame p3 = new Panel_frame("Employee info", 635, 115, 490, 370);
		add(p3);
		

		// Create a single instance of the crud_inputs (ci) object
		crud_inputs_pt ci = new crud_inputs_pt();
		// DayFilter dayFilter = new DayFilter();

		clear = new JLabel();
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				clearAll();
				JOptionPane.showMessageDialog(null, "CLeared");

			}

		});
		clear.setBounds(1180, 550, 175, 75);
		clear.setIcon(new ImageIcon(getClass().getResource("/res/clear.png")));
		add(clear);

		JLabel Title = new JLabel("Project Details");
		Title.setFont(new Font("Tahoma", Font.BOLD, 35));
		Title.setBounds(10, 10, 508, 70);
		add(Title);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		save = new JLabel();
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// Retrieve data from the text_field_add components

				// Set the retrieved data into the ci object

				String pidText = pid.text.getText();
				String pnameText = pname.text.getText();

				Date sdateText_d = sdate.getter();
				String sdateText = dateFormat.format(sdateText_d);
				
				Date edateText_d = edate.getter();
				String edateText = dateFormat.format(edateText_d);

				String pmanagerText = pmanager.text.getText();
				String desText = des.text.getText();
				String statusText = status.text.getText();
				String budgetText = budget.text.getText();
				String cosText = cos.text.getText();
				String locationText = location.text.getText();

				ci.setProjectID(pidText);
				ci.setProjectName(pnameText);
				ci.setStartDate(sdateText);
				ci.setEndDate(edateText);
				ci.setProjectManager(pmanagerText);
				ci.setDescription(desText);
				ci.setStatus(statusText);
				ci.setBudget(budgetText);
				ci.setClientOrStakeholder(cosText);
				ci.setLocation(locationText);

				try {
					if (pidText.isEmpty() || pnameText.isEmpty() || sdateText.isEmpty() || edateText.isEmpty()
							|| pmanagerText == null || desText.isEmpty() || statusText.isEmpty() || budgetText.isEmpty()
							|| cosText.isEmpty() || locationText == null) {
						JOptionPane.showMessageDialog(null, "All details must be filled");
					} else {

						db.getDbConnection();

						PreparedStatement st = Database.getcon().prepareStatement(
								"SELECT pid,pname,sdate,edate,pmanager,des,status,budget,cos,location"
										+ " FROM project_table WHERE pid = ?");
						st.setString(1, pidText);

						ResultSet rs = st.executeQuery();

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Project Already Exsist");

						} else {

							try {
								db.getDbConnection();

								String query = "INSERT INTO project_table (pid,pname,sdate,edate,pmanager,des,status,budget,cos,location)"
										+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
								PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

								preparedStmt.setString(1, ci.getProjectID());
								preparedStmt.setString(2, ci.getProjectName());
								preparedStmt.setString(3, ci.getStartDate());
								preparedStmt.setString(4, ci.getEndDate());
								preparedStmt.setString(5, ci.getProjectManager());
								preparedStmt.setString(6, ci.getDescription());
								preparedStmt.setString(7, ci.getStatus());
								preparedStmt.setString(8, ci.getBudget());
								preparedStmt.setString(9, ci.getClientOrStakeholder());
								preparedStmt.setString(10, ci.getLocation());
								preparedStmt.execute();

								Database.getcon().close();

								JOptionPane.showMessageDialog(null, "Project Added");

							} catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
								System.err.println("Got an exception: " + e1.getMessage());
							}

						}
					}
				}

				catch (Exception ew) {
					System.out.println(ew);
				}

			}
		});
		save.setBounds(1180, 160, 175, 75);
		add(save);
		save.setIcon(new ImageIcon(getClass().getResource("/res/save.png")));

		search = new JLabel();
		search.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// System.out.println(id.text.getText());

				try {

					db.getDbConnection();
					String query = "SELECT pid,pname,sdate,edate,pmanager,des,status,budget,cos,location FROM project_table WHERE pid ="
							+ pid.text.getText();
					Statement st = Database.getcon().createStatement();

					ResultSet rs1 = st.executeQuery(query);

					if (rs1.next() == true) {

						// String pid_s = rs1.getString("pid");
						String pname_s = rs1.getString("pname");
						String sdate_s = rs1.getString("sdate");
						String edate_s = rs1.getString("edate");
						String pmanager_s = rs1.getString("pmanager");
						String des_s = rs1.getString("des");
						String status_s = rs1.getString("status");
						String budget_s = rs1.getString("budget");
						String cos_s = rs1.getString("cos");
						String location_s = rs1.getString("location");

						// ci.setProjectID(pid_s);
						ci.setProjectName(pname_s);
						ci.setStartDate(sdate_s);
						ci.setEndDate(edate_s);
						ci.setProjectManager(pmanager_s);
						ci.setDescription(des_s);
						ci.setStatus(status_s);
						ci.setBudget(budget_s);
						ci.setClientOrStakeholder(cos_s);
						ci.setLocation(location_s);

						// pid.text.setText(ci.getProjectID());
						pname.text.setText(ci.getProjectName());

						DayFilter sdateFilter = new DayFilter();
						sdateFilter.filter(ci.getStartDate());
						sdate.dateChooser.setDate(sdateFilter.day());
						
						DayFilter edateFilter = new DayFilter();
						edateFilter.filter(ci.getEndDate());
						edate.dateChooser.setDate(edateFilter.day());

						pmanager.text.setText(ci.getProjectManager());
						des.text.setText(ci.getDescription());
						status.text.setText(ci.getStatus());
						budget.text.setText(ci.getBudget());
						cos.text.setText(ci.getClientOrStakeholder());
						location.text.setText(ci.getLocation());

					} else {

						JOptionPane.showMessageDialog(null, "Project Not Found");

						String str = pid.text.getText();
						clearAll();
						pid.text.setText(str);

					}

				} catch (Exception ew) {
					System.out.println(ew);
				}

			}
		});
		search.setBounds(1180, 260, 175, 75);
		search.setIcon(new ImageIcon(getClass().getResource("/res/search.png")));
		add(search);

		update = new JLabel();
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String pidText = pid.text.getText();
				String pnameText = pname.text.getText();

				Date sdateText_d = sdate.getter();
				String sdateText = dateFormat.format(sdateText_d);
				Date edateText_d = edate.getter();
				String edateText = dateFormat.format(edateText_d);

				String pmanagerText = pmanager.text.getText();
				String desText = des.text.getText();
				String statusText = status.text.getText();
				String budgetText = budget.text.getText();
				String cosText = cos.text.getText();
				String locationText = location.text.getText();

				ci.setProjectID(pidText);
				ci.setProjectName(pnameText);
				ci.setStartDate(sdateText);
				ci.setEndDate(edateText);
				ci.setProjectManager(pmanagerText);
				ci.setDescription(desText);
				ci.setStatus(statusText);
				ci.setBudget(budgetText);
				ci.setClientOrStakeholder(cosText);
				ci.setLocation(locationText);

				try {
					// Load the database connection and prepare the update query
					db.getDbConnection();
					String query = "UPDATE project_table SET pid=?, pname=?, sdate=?, edate=?, pmanager=?, des=?, status=?, budget=?, cos=?, location=? WHERE pid="
							+ pid.text.getText(); // Replace "your_condition_here" with the appropriate WHERE condition

					// Create a prepared statement
					PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

					// Bind parameters
					preparedStmt.setString(1, ci.getProjectID());
					preparedStmt.setString(2, ci.getProjectName());
					preparedStmt.setString(3, ci.getStartDate());
					preparedStmt.setString(4, ci.getEndDate());
					preparedStmt.setString(5, ci.getProjectManager());
					preparedStmt.setString(6, ci.getDescription());
					preparedStmt.setString(7, ci.getStatus());
					preparedStmt.setString(8, ci.getBudget());
					preparedStmt.setString(9, ci.getClientOrStakeholder());
					preparedStmt.setString(10, ci.getLocation());

					// Execute the update query
					int rowsUpdated = preparedStmt.executeUpdate();

					if (rowsUpdated > 0) {
						JOptionPane.showMessageDialog(null, "Data Updated");
					} else {
						JOptionPane.showMessageDialog(null, "No data updated. Check your condition.");
					}

					preparedStmt.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		update.setBounds(1180, 360, 175, 75);
		update.setIcon(new ImageIcon(getClass().getResource("/res/update.png")));
		add(update);

		delete = new JLabel();
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root", "");
					java.sql.Statement stmt = con.createStatement();

					String sql = ("DELETE FROM project_table WHERE pid = " + Integer.parseInt(pid.text.getText()));

					int delete = stmt.executeUpdate(sql);
					if (delete == 1) {

						JOptionPane.showMessageDialog(null, "Project Deleted");

					}
					stmt.close();
					con.close();

				} catch (Exception eo) {
					System.out.println(eo);
				}

			}
		});
		delete.setBounds(1180, 460, 175, 75);
		delete.setIcon(new ImageIcon(getClass().getResource("/res/delete.png")));
		add(delete);

	//	MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();

		crudBtn.operation("save2.png", "save.png", save);
		crudBtn.operation("search2.png", "search.png", search);
		crudBtn.operation("update2.png", "update.png", update);
		crudBtn.operation("delete2.png", "delete.png", delete);
		crudBtn.operation("clear2.png", "clear.png", clear);
	}

	@SuppressWarnings("deprecation")
	void clearAll() {
		pid.text.setText("");
		pname.text.setText("");
		sdate.dateChooser.setDate(new Date(123, 0, 1));
		edate.dateChooser.setDate(new Date(123, 0, 1));
		pmanager.text.setText("");
		des.text.setText("");
		status.text.setText("");
		budget.text.setText("");
		cos.text.setText("");
		location.text.setText("");

		pid.text.requestFocus(true);

	}
}
