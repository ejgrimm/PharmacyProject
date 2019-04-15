import java.util.ArrayList;

public class outPatient extends Patient implements PrescriptionFillable{

	//data members
	private String lastVisit;

	//constructors
	public outPatient(String ssn, String name, String address, String phone, String insurance,
			ArrayList<DrugLine> currentPrescription, String lastVisit) {
		super(ssn, name, address, phone, insurance, currentPrescription);
		this.lastVisit = lastVisit;
	}
	
	public outPatient() {
		super();
	}

	//getters and setters
	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}
	
	
	
	
}
