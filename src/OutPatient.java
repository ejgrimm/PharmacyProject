import java.util.ArrayList;

public class OutPatient extends Patient {

	//data members
	private String lastVisit;

	//constructors	
	
	public OutPatient() {
		super();
	}
	
	// with prescriptions

	public OutPatient(String name, String ssn, String address, String phone, String insurance,
			ArrayList<DrugLine> currentPrescription, String lastVisit) {
		super(name, ssn, address, phone, insurance, currentPrescription);
		this.lastVisit = lastVisit;
		setType("OutPatient");
	}
	
	// no prescriptions yet
    public OutPatient(String name, String ssn, String address, String phone, String insurance, String lastVisit) {
        super(name, ssn, address, phone, insurance);
        this.lastVisit = lastVisit;
        setType("OutPatient");
    }
	//getters and setters
	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	
}
