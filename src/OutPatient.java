import java.util.ArrayList;

public class OutPatient extends Patient implements PrescriptionFillable {

	//data members
	private String lastVisit;

	//constructors	
	
	public OutPatient() {
		super();
	}

	public OutPatient(String name, String ssn, String address, String phone, String insurance, String type,
			ArrayList<DrugLine> currentPrescription, String lastVisit) {
		super(name, ssn, address, phone, insurance, type, currentPrescription);
		this.lastVisit = lastVisit;
	}

	//getters and setters
	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	
}
