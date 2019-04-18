import java.util.ArrayList;

public class OutPatient extends Patient implements PrescriptionFillable{

	//data members
	private String lastVisit;

	//constructors	
	
	public OutPatient() {
		super();
	}

	public OutPatient(String ssn, String name, String address, String phone, String insurance, String type,
			ArrayList<DrugLine> currentPrescription, String lastVisit) {
		super(ssn, name, address, phone, insurance, type, currentPrescription);
		this.lastVisit = lastVisit;
	}

	//getters and setters
	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	@Override
	public boolean fillPrescription(Prescription rx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestRefill(DrugLine dl) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
