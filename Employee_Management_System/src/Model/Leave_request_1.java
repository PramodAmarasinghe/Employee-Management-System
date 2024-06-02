package Model;

import java.awt.Color; 
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Controller.Database;
import Controller.DayFilter;
import Controller.MouseEvent_crudBtn;

import java.awt.Font;

public class Leave_request_1 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	text_field_add days ;
	DatePicker edate;
	text_field_add reason;
	text_field_add type ;
	DatePicker sdate ;
	text_field_add id ;
	text_field_add fname ;
	/**
	 * Create the panel.
	 */
	public Leave_request_1() {

		crud_inputs ci = new crud_inputs();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		setBackground(Color.WHITE);
		setBounds(0, 0, 1375, 800);
		setLayout(null);

		Database db = new Database();
		//JDateChooser dateChooser = new JDateChooser();
		// delete start

		 days = new text_field_add("Number Of Days", 95, 575);
		add(days);
		 edate = new DatePicker("Leave End Date", 615, 245);
		add(edate);
		 reason = new text_field_add("Reason", 95, 465);
		add(reason);
		 type = new text_field_add("Type", 95, 355);
		add(type);
		 sdate = new DatePicker("Leave Start Date", 615, 135);
		add(sdate);
		 id = new text_field_add("ID Number", 95, 135);
		add(id);
		 fname = new text_field_add("First Name", 95, 245);
		add(fname);

		Panel_frame p = new Panel_frame("Employee info", 80, 115, 490, 200);
		add(p);

		Panel_frame p1 = new Panel_frame("Leave request info", 80, 335, 490, 300);
		add(p1);

		Panel_frame p2 = new Panel_frame("Duration info", 600, 115, 490, 200);
		add(p2);

		JLabel update = new JLabel();
		update.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				String idText = id.text.getText();
				String fnameText = fname.text.getText();
				String typeText = type.text.getText();
				String reasonText = reason.text.getText();
				String daysText = days.text.getText();

				Date sdateText_d = sdate.getter();
				String sdateText = dateFormat.format(sdateText_d);

				Date edateText_d = edate.getter();
				String edateText = dateFormat.format(edateText_d);
				
				

				ci.setFname(fnameText);
				ci.setID(idText);
				ci.setType(typeText);
				ci.setReason(reasonText);
				ci.setDays(daysText);
				ci.setSdate(sdateText);
				ci.setEdate(edateText);

				try {
					// Load the database connection and prepare the update query
					db.getDbConnection();
					String query = "UPDATE leave_r SET fname=?, id=?, title=?, reason=?, days=?, start=?, end=? WHERE id="
							+ id.text.getText(); // Replace "your_condition_here" with the appropriate WHERE condition

					// Create a prepared statement
					PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

					// Bind parameters
					preparedStmt.setString(1, ci.getFname());
					preparedStmt.setString(2, ci.getID());
					preparedStmt.setString(3, ci.getType());
					preparedStmt.setString(4, ci.getReason());
					preparedStmt.setInt(5, Integer.parseInt(ci.getDays()));
					preparedStmt.setString(6, ci.getSdate());
					preparedStmt.setString(7, ci.getEdate());

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

		JLabel delete = new JLabel();
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				try {

					db.getDbConnection();

					java.sql.Statement stmt = Database.getcon().createStatement();

					String sql = ("DELETE FROM leave_r WHERE id = " + Integer.parseInt(id.text.getText()));

					int delete = stmt.executeUpdate(sql);
					if (delete == 1) {

						JOptionPane.showMessageDialog(null, "Request Deleted");

					}
					stmt.close();
					Database.getcon().close();

				} catch (Exception eo) {
					System.out.println(eo);
				}

			}
		});
		delete.setBounds(1180, 460, 175, 75);
		delete.setIcon(new ImageIcon(Leave_request_1.class.getResource("/res/delete.png")));
		add(delete);

		// end

		JLabel save = new JLabel();
		save.setIcon(new ImageIcon(Leave_request_1.class.getResource("/res/save.png")));
		save.setBounds(1180, 160, 175, 75);
		add(save);

		save.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {

				// Retrieve data from the text_field_add components
				String idText = id.text.getText();
				String fnameText = fname.text.getText();
				String typeText = type.text.getText();
				String reasonText = reason.text.getText();
				String daysText = days.text.getText();

				Date sdateText_d = sdate.getter();
				String sdateText = dateFormat.format(sdateText_d);

				Date edateText_d = edate.getter();
				String edateText = dateFormat.format(edateText_d);

				// Set the retrieved data into the ci object
				ci.setFname(fnameText);
				ci.setID(idText);
				ci.setType(typeText);
				ci.setReason(reasonText);
				ci.setDays(daysText);
				ci.setSdate(sdateText);
				ci.setEdate(edateText);

				try {
					if (fnameText.isEmpty() || idText.isEmpty() || typeText.isEmpty() || reasonText.isEmpty()
							|| daysText.isEmpty() || sdateText.isEmpty() || edateText.isEmpty()) {
						JOptionPane.showMessageDialog(null, "All details must be filled");
					} else {

						try {
							// Get a database connection
							db.getDbConnection();

							// Define the SQL query with placeholders
							String query = "INSERT INTO leave_r (fname, id, title, reason, days, start, end) VALUES (?, ?, ?, ?, ?, ?, ?)";

							// Create a PreparedStatement
							PreparedStatement preparedStmt = Database.getcon().prepareStatement(query);

							// Set the values using placeholders
							preparedStmt.setString(1, ci.getFname());
							preparedStmt.setString(2, ci.getID());
							preparedStmt.setString(3, ci.getType());
							preparedStmt.setString(4, ci.getReason());
							preparedStmt.setString(5, ci.getDays());
							preparedStmt.setString(6, ci.getSdate());
							preparedStmt.setString(7, ci.getEdate());

							// Execute the query
							int rowsInserted = preparedStmt.executeUpdate();

							if (rowsInserted > 0) {
								JOptionPane.showMessageDialog(null, "Data inserted successfully.");
							} else {
								JOptionPane.showMessageDialog(null, "Data not inserted.");
							}

							// Close the database connection
							Database.getcon().close();
						} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
							System.err.println("Got an exception: " + e.getMessage());
						}
					}
				}

				catch (Exception ew) {
					System.out.println(ew);
				}

			}

		});
		
		
		//search crud beg
		
		
		
		
		JLabel search = new JLabel();
		search.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        try {
		            // Get a database connection
		            db.getDbConnection();

		            // Construct a parameterized SQL query to prevent SQL injection
		            String query = "SELECT fname, id, title, reason, days, start, end FROM leave_r WHERE id = ?";
		            
		            // Use a PreparedStatement for the query
		            PreparedStatement preparedStatement = Database.getcon().prepareStatement(query);
		            preparedStatement.setString(1, id.text.getText()); // Set the parameter

		            // Execute the query
		            ResultSet rs1 = preparedStatement.executeQuery();

		            if (rs1.next()) {
		                // Data found
		                String day = rs1.getString("days");
		                String idText = rs1.getString("id");
		                String fnameText = rs1.getString("fname");
		                String title = rs1.getString("title");
		                String reasonText = rs1.getString("reason");
		                String sdate_s = rs1.getString("start");
		                String edate_s = rs1.getString("end");

		                // Update UI components
		                ci.setFname(fnameText);
		                ci.setID(idText);
		                ci.setType(title);
		                ci.setReason(reasonText);
		                ci.setDays(day);
		                ci.setSdate(sdate_s);
		                ci.setEdate(edate_s);

		                fname.text.setText(ci.getFname());
		                id.text.setText(ci.getID());
		                type.text.setText(ci.getType());
		                reason.text.setText(ci.getReason());
		                days.text.setText(ci.getDays());

		                // Update date fields - it appears there was an error in this section
		                DayFilter sdateFilter = new DayFilter();
		                sdateFilter.filter(ci.getSdate());
		                sdate.dateChooser.setDate(sdateFilter.day());

		                DayFilter edateFilter = new DayFilter();
		                edateFilter.filter(ci.getEdate()); // Use getEdate() instead of getSdate()
		                edate.dateChooser.setDate(edateFilter.day());
		            } else {
		                // No data found
		                JOptionPane.showMessageDialog(null, "Employee not found");
		            }

		            // Close the database connection
		            Database.getcon().close();
		        } catch (SQLException ew) {
		            ew.printStackTrace();
		            System.err.println("Got an exception: " + ew.getMessage());
		        } catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    }
		});
		
		search.setBounds(1180, 260, 175, 75);
		search.setIcon(new ImageIcon(getClass().getResource("/res/search.png")));
		add(search);


		JLabel lblLeaveRequests = new JLabel("Leave Requests");
		lblLeaveRequests.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblLeaveRequests.setBounds(10, 10, 508, 70);
		add(lblLeaveRequests);
		
		JLabel clear = new JLabel();
		clear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				clearAll();
			}
		});
		clear.setIcon(new ImageIcon(Leave_request_1.class.getResource("/res/clear.png")));
		clear.setBounds(1180, 550, 175, 75);
		add(clear);
		
		MouseEvent_crudBtn crudBtn = new MouseEvent_crudBtn();

		crudBtn.operation("save2.png", "save.png", save);
		crudBtn.operation("search2.png", "search.png", search);
		crudBtn.operation("update2.png", "update.png", update);
		crudBtn.operation("delete2.png", "delete.png", delete);
		crudBtn.operation("clear2.png", "clear.png", clear);

		// Create a single instance of the crud_inputs (ci) object

	}
	
	@SuppressWarnings("deprecation")
	void clearAll() {

		days.text.setText("");
		reason.text.setText("");
		sdate.dateChooser.setDate(new Date(123, 0, 1));
		edate.dateChooser.setDate(new Date(123, 0, 1));
		type.text.setText("");
		id.text.setText("");
		fname.text.setText("");


		id.text.requestFocus(true);
        JOptionPane.showMessageDialog(null, "Cleared");

		

	}
}
