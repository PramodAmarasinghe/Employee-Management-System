package Model;

import java.awt.Color;
import java.awt.Font;
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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.Database;
import Controller.DayFilter;
import Controller.MouseEvent_crudBtn;

public class Salary_History extends JPanel {

	private static final long serialVersionUID = 1L;
	private text_field_add empid;
	private DatePicker date;
	private text_field_add previous_sal;
	private text_field_add new_sal;;
	private text_field_add reason;
	private text_field_add changedby;

	private JLabel save;
	private JLabel search;
	private JLabel update;
	private JLabel delete;
	private JLabel clear;

	/**
	 * Create the panel.
	 */
	public Salary_History() {

		setBackground(Color.WHITE);
		setBounds(0, 0, 1375, 800);
		setLayout(null);

		Database db = new Database();
		
		MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();
		
		date = new DatePicker("Effective Date", 95, 535);
		date.setLocation(95, 550);
		add(date);

		Panel_frame p = new Panel_frame("Information", 80, 115, 490, 370);
		add(p);
		reason = new text_field_add("Reason for change", 650, 435);
		reason.setBounds(10, 169, 457, 32);
		p.add(reason);

		empid = new text_field_add("Employee ID", 95, 135);
		empid.setBounds(10, 49, 457, 32);
		p.add(empid);

		changedby = new text_field_add("Changed By", 95, 435);
		changedby.setBounds(10, 281, 457, 32);
		p.add(changedby);

		Panel_frame p1 = new Panel_frame("Effective Date", 80, 520, 490, 165);
		add(p1);

		Panel_frame p3 = new Panel_frame("Salary Details", 635, 115, 490, 370);
		add(p3);
		new_sal = new text_field_add("New Salary", 650, 270);
		new_sal.setBounds(10, 264, 457, 32);
		p3.add(new_sal);
		previous_sal = new text_field_add("Previous Salary", 650, 135);
		previous_sal.setBounds(10, 69, 457, 32);
		p3.add(previous_sal);

		crud_inputs_sh ci = new crud_inputs_sh();

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

		JLabel Title = new JLabel("Salary History");
		Title.setFont(new Font("Tahoma", Font.BOLD, 35));
		Title.setBounds(10, 10, 508, 70);
		add(Title);

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		save = new JLabel();
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String empidText = empid.text.getText();

				Date dateText_d = date.getter();
				String dateText = dateFormat.format(dateText_d);

				String previous_salText = previous_sal.text.getText();
				String new_salText = new_sal.text.getText();
				String reasonText = reason.text.getText();
				String changedbyText = changedby.text.getText();

				ci.setEmployeeID(empidText);
				ci.setEffectiveDate(dateText);
				ci.setPreviousSalary(previous_salText);
				ci.setNewSalary(new_salText);
				ci.setReasensForChange(reasonText);
				ci.setChangedBy(changedbyText);
				
				try {
					if (empidText.isEmpty() || dateText.isEmpty() || previous_salText.isEmpty() || new_salText.isEmpty()
							|| reasonText.isEmpty() || changedbyText.isEmpty()) {
						JOptionPane.showMessageDialog(null, "All details must be filled");
					} else {

						db.getDbConnection();

						PreparedStatement st = Database.getcon()
								.prepareStatement("SELECT empid,date,previous_sal,new_sal,reason,changedby"
										+ " FROM salaryhistory WHERE empid = ?");
						st.setString(1, empidText);

						ResultSet rs = st.executeQuery();

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Salary Details Exsist");

						} else {

							try {
								db.getDbConnection();

								String query = "INSERT INTO salaryhistory (empid,date,previous_sal,new_sal,reason,changedby)"
										+ "VALUES (?,?,?,?,?,?)";
								PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

								preparedStmt.setString(1, ci.getEmployeeID());
								preparedStmt.setString(2, ci.getEffectiveDate());
								preparedStmt.setString(3, ci.getPreviousSalary());
								preparedStmt.setString(4, ci.getNewSalary());
								preparedStmt.setString(5, ci.getReasensForChange());
								preparedStmt.setString(6, ci.getChangedBy());
								preparedStmt.execute();

								Database.getcon().close();

								JOptionPane.showMessageDialog(null, "Salary Added");

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

				try {

					db.getDbConnection();
					String query = "SELECT empid,date,previous_sal,new_sal,reason,changedby FROM salaryhistory WHERE empid ="
							+ empid.text.getText();
					Statement st = Database.getcon().createStatement();

					ResultSet rs1 = st.executeQuery(query);

					if (rs1.next() == true) {

						String date_s = rs1.getString("date");
						String previous_sal_s = rs1.getString("previous_sal");
						String new_sal_s = rs1.getString("new_sal");
						String reason_s = rs1.getString("reason");
						String changedby_s = rs1.getString("changedby");

						ci.setEffectiveDate(date_s);
						ci.setPreviousSalary(previous_sal_s);
						ci.setNewSalary(new_sal_s);
						ci.setReasensForChange(reason_s);
						ci.setChangedBy(changedby_s);

						DayFilter dateFilter = new DayFilter();
						dateFilter.filter(ci.getEffectiveDate());
						date.dateChooser.setDate(dateFilter.day());

						previous_sal.text.setText(ci.getPreviousSalary());
						new_sal.text.setText(ci.getNewSalary());
						reason.text.setText(ci.getReasensForChange());
						changedby.text.setText(ci.getChangedBy());

					} else {

						JOptionPane.showMessageDialog(null, "Salary Details Not Found");

						String str = empid.text.getText();
						clearAll();
						empid.text.setText(str);

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

				String empidText = empid.text.getText();

				Date dateText_d = date.getter();
				String dateText = dateFormat.format(dateText_d);

				String previous_salText = previous_sal.text.getText();
				String new_salText = new_sal.text.getText();
				String reasonText = reason.text.getText();
				String changedbyText = changedby.text.getText();

				ci.setEmployeeID(empidText);
				ci.setEffectiveDate(dateText);
				ci.setPreviousSalary(previous_salText);
				ci.setNewSalary(new_salText);
				ci.setReasensForChange(reasonText);
				ci.setChangedBy(changedbyText);

				try {
					db.getDbConnection();
					String query = "UPDATE salaryhistory SET empid=?, date=?, previous_sal=?, new_sal=?, reason=?, changedby=? WHERE empid="
							+ empid.text.getText();

					PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

					preparedStmt.setString(1, ci.getEmployeeID());
					preparedStmt.setString(2, ci.getEffectiveDate());
					preparedStmt.setString(3, ci.getPreviousSalary());
					preparedStmt.setString(4, ci.getNewSalary());
					preparedStmt.setString(5, ci.getReasensForChange());
					preparedStmt.setString(6, ci.getChangedBy());

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

					String sql = ("DELETE FROM salaryhistory WHERE empid = " + Integer.parseInt(empid.text.getText()));

					int delete = stmt.executeUpdate(sql);
					if (delete == 1) {

						JOptionPane.showMessageDialog(null, "Salary Details Deleted");

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

		// MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();

		crudBtn.operation("save2.png", "save.png", save);
		crudBtn.operation("search2.png", "search.png", search);
		crudBtn.operation("update2.png", "update.png", update);
		crudBtn.operation("delete2.png", "delete.png", delete);
		crudBtn.operation("clear2.png", "clear.png", clear);
	}

	@SuppressWarnings("deprecation")
	void clearAll() {
		empid.text.setText("");
		date.dateChooser.setDate(new Date(123, 0, 1));
		previous_sal.text.setText("");
		new_sal.text.setText("");
		reason.text.setText("");
		changedby.text.setText("");

		empid.text.requestFocus(true);

	}

}
