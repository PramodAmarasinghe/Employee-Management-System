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

public class Employee_Details extends JPanel {

	private static final long serialVersionUID = 1L;
	private text_field_add id;
	private text_field_add fname;
	private text_field_add mname;
	private text_field_add lname;
	private DatePicker dob;
	private text_field_add city;
	private text_field_add postal;;
	private text_field_add address;
	private text_field_add job;
	private DatePicker hire;
	private text_field_add phone_1;
	private text_field_add phone_2;
	private DropBox gender;
	private DropBox marital;
	private JLabel save;
	private JLabel search;
	private JLabel update;
	private JLabel delete;
	private JLabel clear;

	/**
	 * Create the panel.
	 */
	public Employee_Details() {

		setBackground(Color.WHITE);
		setBounds(0, 0, 1375, 800);
		setLayout(null);

		Database db = new Database();
		MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();

		String[] options_g = { "Male", "Female", "Custom" };
		String[] options_m = { "Single", "Married" };

		id = new text_field_add("ID Number", 95, 135);
		fname = new text_field_add("First Name", 95, 215);
		mname = new text_field_add("Middle Name", 95, 295);
		lname = new text_field_add("Last Name", 95, 375);
		dob = new DatePicker("Date of Birth", 95, 455);
		city = new text_field_add("City", 655, 155);
		postal = new text_field_add("Postal Code", 655, 235);
		address = new text_field_add("Address", 655, 315);
		job = new text_field_add("Job Title", 95, 584);
		hire = new DatePicker("Hire Date", 95, 664);
		phone_1 = new text_field_add("Mobile Phone", 655, 580);
		phone_2 = new text_field_add("Email", 655, 660);
		gender = new DropBox(options_g, "Gender", 655, 410);
		marital = new DropBox(options_m, "Marital Status", 655, 490);

		add(id);
		add(fname);
		add(mname);
		add(lname);
		add(dob);
		add(city);
		add(postal);
		add(address);
		add(job);
		add(hire);
		add(phone_1);
		add(phone_2);
		add(gender);
		add(marital);

		Panel_frame Pframe1 = new Panel_frame("Personal Information", 80, 115, 500, 400);
		add(Pframe1);

		Panel_frame Pframe2 = new Panel_frame("Current Location Details", 639, 135, 500, 230);
		add(Pframe2);

		Panel_frame Pframe3 = new Panel_frame("Family and Gender Status", 639, 385, 500, 150);
		add(Pframe3);

		Panel_frame Pframe4 = new Panel_frame("Contact Details", 639, 550, 500, 150);
		add(Pframe4);

		Panel_frame Pframe5 = new Panel_frame("Additional Information", 629, 115, 520, 595);
		add(Pframe5);

		Panel_frame Pframe6 = new Panel_frame("Job Status", 80, 564, 500, 150);
		add(Pframe6);

		// Create a single instance of the crud_inputs (ci) object
		crud_inputs_1 ci = new crud_inputs_1();
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

		JLabel Title = new JLabel("Employee Details");
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

				String idText = id.text.getText();
				String fnameText = fname.text.getText();
				String mnameText = mname.text.getText();
				String lnameText = address.text.getText();

				Date dobText_d = dob.getter();
				String dobText = dateFormat.format(dobText_d);

				String cityText = city.text.getText();
				String postalText = postal.text.getText();
				String addressText = address.text.getText();
				String jobText = job.text.getText();

				Date hireText_d = hire.getter();
				String hireText = dateFormat.format(hireText_d);

				String phone1Text = phone_1.text.getText();
				String phone2Text = phone_2.text.getText();
				String genderText = gender.text.getSelectedItem().toString();
				String maritalText = marital.text.getSelectedItem().toString();

				ci.setID(idText);
				ci.setFname(fnameText);
				ci.setMname(mnameText);
				ci.setLname(lnameText);
				ci.setDOB(dobText);
				ci.setCity(cityText);
				ci.setPostal(postalText);
				ci.setAddress(addressText);
				ci.setJob(jobText);
				ci.setHire(hireText);
				ci.setPhone1(phone1Text);
				ci.setPhone2(phone2Text);
				ci.setGender(genderText);
				ci.setMarital(maritalText);

				try {
					if (idText.isEmpty() || fnameText.isEmpty() || mnameText.isEmpty() || lnameText.isEmpty()
							|| dobText == null || cityText.isEmpty() || postalText.isEmpty() || addressText.isEmpty()
							|| jobText.isEmpty() || hireText == null || phone1Text.isEmpty() || phone2Text.isEmpty()
							|| genderText.isEmpty() || maritalText.isEmpty()) {
						JOptionPane.showMessageDialog(null, "All details must be filled");
					} else {

						db.getDbConnection();

						PreparedStatement st = Database.getcon().prepareStatement(
								"SELECT id,fname,mname,lname,dob,job,hire,city,postal,address,gender,marital,phone1,phone2"
										+ " FROM emp_details WHERE id = ?");
						st.setString(1, idText);

						ResultSet rs = st.executeQuery();

						if (rs.next()) {
							JOptionPane.showMessageDialog(null, "Employee already exsist");

						} else {

							try {
								db.getDbConnection();

								String query = "INSERT INTO emp_details (id,fname,mname,lname,dob,job,hire,city,postal,address,gender,marital,phone1,phone2)"
										+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

								preparedStmt.setString(1, ci.getID());
								preparedStmt.setString(2, ci.getFname());
								preparedStmt.setString(3, ci.getMname());
								preparedStmt.setString(4, ci.getLname());
								preparedStmt.setString(5, ci.getDOB());
								preparedStmt.setString(6, ci.getJob());
								preparedStmt.setString(7, ci.getHire());
								preparedStmt.setString(8, ci.getCity());
								preparedStmt.setString(9, ci.getPostal());
								preparedStmt.setString(10, ci.getAddress());
								preparedStmt.setString(11, ci.getGender());
								preparedStmt.setString(12, ci.getMarital());
								preparedStmt.setString(13, ci.getPhone1());
								preparedStmt.setString(14, ci.getPhone2());
								preparedStmt.execute();

								Database.getcon().close();

								JOptionPane.showMessageDialog(null, "Employee added");

							} catch (ClassNotFoundException | SQLException e1) {
								JOptionPane.showMessageDialog(null, "ID, Phone number must be a Numaric");

							}

						}
					}
				}

				catch (Exception ew) {
					// System.out.println(ew);
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
					String query = "SELECT fname,mname,lname,dob,job,hire,city,postal,address,gender,marital,phone1,phone2  FROM emp_details WHERE id ="
							+ id.text.getText();
					Statement st = Database.getcon().createStatement();

					ResultSet rs1 = st.executeQuery(query);

					if (rs1.next() == true) {

						// String id_s = rs1.getString("id");
						String fname_s = rs1.getString("fname");
						String mname_s = rs1.getString("mname");
						String lname_s = rs1.getString("lname");
						String dob_s = rs1.getString("dob");
						String job_s = rs1.getString("job");
						String hire_s = rs1.getString("hire");
						String city_s = rs1.getString("city");
						String postal_s = rs1.getString("postal");
						String address_s = rs1.getString("address");
						String gender_s = rs1.getString("gender");
						String marital_s = rs1.getString("marital");
						String phone1_s = rs1.getString("phone1");
						String email_s = rs1.getString("phone2");

						// ci.setID(id_s);
						ci.setFname(fname_s);
						ci.setMname(mname_s);
						ci.setLname(lname_s);
						ci.setDOB(dob_s);
						ci.setCity(city_s);
						ci.setPostal(postal_s);
						ci.setAddress(address_s);
						ci.setJob(job_s);
						ci.setHire(hire_s);
						ci.setPhone1(phone1_s);
						ci.setPhone2(email_s);
						ci.setGender(gender_s);
						ci.setMarital(marital_s);

						// id.text.setText(ci.getID());
						fname.text.setText(ci.getFname());
						mname.text.setText(ci.getMname());
						lname.text.setText(ci.getLname());

						DayFilter dobFilter = new DayFilter();
						dobFilter.filter(ci.getDOB());
						dob.dateChooser.setDate(dobFilter.day());

						city.text.setText(ci.getCity());
						postal.text.setText(ci.getPostal());
						address.text.setText(ci.getAddress());
						job.text.setText(ci.getJob());

						DayFilter hireFilter = new DayFilter();
						hireFilter.filter(ci.getHire());
						hire.dateChooser.setDate(hireFilter.day());

						phone_1.text.setText(ci.getPhone1());
						phone_2.text.setText(ci.getPhone2());
						gender.text.setSelectedItem(ci.getGender());
						marital.text.setSelectedItem(ci.getMarital());

					} else {

						JOptionPane.showMessageDialog(null, "Employee not found");

						String str = id.text.getText();
						clearAll();
						id.text.setText(str);

					}

				} catch (Exception ew) {
					JOptionPane.showMessageDialog(null, "ID must be numaric");
					// System.out.println(ew);
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

				String idText = id.text.getText();
				String fnameText = fname.text.getText();
				String mnameText = mname.text.getText();
				String lnameText = address.text.getText();

				Date dobText_d = dob.getter();
				String dobText = dateFormat.format(dobText_d);

				String cityText = city.text.getText();
				String postalText = postal.text.getText();
				String addressText = address.text.getText();
				String jobText = job.text.getText();

				Date hireText_d = hire.getter();
				String hireText = dateFormat.format(hireText_d);

				String phone1Text = phone_1.text.getText();
				String phone2Text = phone_2.text.getText();
				String genderText = gender.text.getSelectedItem().toString();
				String maritalText = marital.text.getSelectedItem().toString();

				ci.setID(idText);
				ci.setFname(fnameText);
				ci.setMname(mnameText);
				ci.setLname(lnameText);
				ci.setDOB(dobText);
				ci.setCity(cityText);
				ci.setPostal(postalText);
				ci.setAddress(addressText);
				ci.setJob(jobText);
				ci.setHire(hireText);
				ci.setPhone1(phone1Text);
				ci.setPhone2(phone2Text);
				ci.setGender(genderText);
				ci.setMarital(maritalText);

				try {
					// Load the database connection and prepare the update query
					db.getDbConnection();
					String query = "UPDATE emp_details SET id=?, fname=?, mname=?, lname=?, dob=?, job=?, hire=?, city=?, postal=?, address=?, gender=?, marital=?, phone1=?, phone2=? WHERE id="
							+ id.text.getText(); // Replace "your_condition_here" with the appropriate WHERE condition

					// Create a prepared statement
					PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

					// Bind parameters
					preparedStmt.setString(1, ci.getID());
					preparedStmt.setString(2, ci.getFname());
					preparedStmt.setString(3, ci.getMname());
					preparedStmt.setString(4, ci.getLname());
					preparedStmt.setString(5, ci.getDOB());
					preparedStmt.setString(6, ci.getJob());
					preparedStmt.setString(7, ci.getHire());
					preparedStmt.setString(8, ci.getCity());
					preparedStmt.setString(9, ci.getPostal());
					preparedStmt.setString(10, ci.getAddress());
					preparedStmt.setString(11, ci.getGender());
					preparedStmt.setString(12, ci.getMarital());
					preparedStmt.setString(13, ci.getPhone1());
					preparedStmt.setString(14, ci.getPhone2());

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

					String sql = ("DELETE FROM emp_details WHERE id = " + Integer.parseInt(id.text.getText()));

					int delete = stmt.executeUpdate(sql);
					if (delete == 1) {

						JOptionPane.showMessageDialog(null, "Employee Deleted");

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

		crudBtn.operation("save2.png", "save.png", save);
		crudBtn.operation("search2.png", "search.png", search);
		crudBtn.operation("update2.png", "update.png", update);
		crudBtn.operation("delete2.png", "delete.png", delete);
		crudBtn.operation("clear2.png", "clear.png", clear);

	}

	@SuppressWarnings("deprecation")
	void clearAll() {
		id.text.setText("");
		fname.text.setText("");
		mname.text.setText("");
		lname.text.setText("");
		dob.dateChooser.setDate(new Date(123, 0, 1));
		city.text.setText("");
		postal.text.setText("");
		address.text.setText("");
		job.text.setText("");
		hire.dateChooser.setDate(new Date(123, 0, 1));
		phone_1.text.setText("");
		phone_2.text.setText("");
		gender.text.setSelectedItem("Male");
		marital.text.setSelectedItem("Single");

		id.text.requestFocus(true);

	}
}
