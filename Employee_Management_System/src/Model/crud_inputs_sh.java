package Model;

public class crud_inputs_sh {
	
	private String EmployeeID;
	private String EffectiveDate;
	private String PreviousSalary;
	private String NewSalary;
	private String ReasensForChange;
	private String ChangedBy;
	
	
	
		public void setEmployeeID(String empid) {
			EmployeeID = empid;
		}
	
		public String getEmployeeID() {
			return EmployeeID;
		}
	
		
		public void setEffectiveDate(String date) {
			this.EffectiveDate = date;
		}
		
		public String getEffectiveDate() {
			return EffectiveDate;
		}
		
		
		public void setPreviousSalary(String previous_sal) {
			PreviousSalary = previous_sal;
		}
		
		public String getPreviousSalary() {
			return PreviousSalary;
		}
		
		public void setNewSalary(String new_sal) {
			NewSalary = new_sal;
		}
		
		public String getNewSalary() {
			return NewSalary;
		}
		
		public void setReasensForChange(String reason) {
			ReasensForChange = reason;
		}
		
		public String getReasensForChange() {
			return ReasensForChange;
		}
		
		
		public void setChangedBy(String changedby) {
			ChangedBy = changedby;
		}
		
		public String getChangedBy() {
			return ChangedBy;
		}

}
