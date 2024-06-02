package Model;

public class crud_inputs_pt {
	
	private String ProjectID;
	private String ProjectName;
	private String StartDate;
	private String EndDate;
	private String ProjectManager;
	private String Description;
	private String Status;
	private String Budget; 
	private String ClientOrStakeholder;
	private String Location;
	
	
	
		public void setProjectID(String pid) {
			ProjectID = pid;
		}
	
		public String getProjectID() {
			return ProjectID;
		}
		
		
		public void setProjectName(String pname) {
			ProjectName = pname;
		}
		
		public String getProjectName() {
			return ProjectName;
		}
		
		
		public void setStartDate(String sdate) {
			this.StartDate = sdate;
		}
		
		public String getStartDate() {
			return StartDate;
		}
		
		
		public void setEndDate(String edate) {
			this.EndDate = edate;
		}
		
		public String getEndDate() {
			return EndDate;
		}
		
		
		public void setProjectManager(String pmanager) {
			ProjectManager = pmanager;
		}
		
		public String getProjectManager() {
			return ProjectManager;
		}
		
		public void setDescription(String des) {
			Description = des;
		}
		
		public String getDescription() {
			return Description;
		}
		
		public void setStatus(String status) {
			Status = status;
		}
		
		public String getStatus() {
			return Status;
		}
		
		
		public void setBudget(String budget) {
			Budget = budget;
		}
		
		public String getBudget() {
			return Budget;
		}
		
		public void setClientOrStakeholder(String cos) {
			ClientOrStakeholder = cos;
		}
		
		public String getClientOrStakeholder() {
			return ClientOrStakeholder;
		}

		
		public void setLocation(String location) {
			Location = location;
		}
		
		public String getLocation() {
			return Location;
		}
		
}
