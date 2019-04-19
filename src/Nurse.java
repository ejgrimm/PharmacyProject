
public class Nurse extends Person {
    
    // data members 
    private String department, staffID;
    
    // constructors 
    public Nurse() {
        super();
        this.department = "Unassigned";
        this.staffID = "TBD";
    }
    
    public Nurse(String name, String ssn, String address, String phone, String department, String staffID) {
        super(name, ssn, address, phone);
        this.department = department;
        this.staffID = staffID;        
    }

    // getters & setters 
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

}
