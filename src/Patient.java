import java.util.ArrayList;

public  abstract class Patient extends Person {

	
	//data members
	private String insurance, type;
	private ArrayList<DrugLine> currentPrescription;
	
	//constructors
	public Patient(String name, String ssn, String address, String phone, String insurance,String type,
			ArrayList<DrugLine> currentPrescription) {
		super(name, ssn, address, phone);
		this.type = type;
		this.insurance = insurance;
		this.currentPrescription = currentPrescription;
	}

	public Patient() {
		super();
	}

	//getters and setters

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public ArrayList<DrugLine> getCurrentPrescription() {
		return currentPrescription;
	}

	public void setCurrentPrescription(ArrayList<DrugLine> currentPrescription) {
		this.currentPrescription = currentPrescription;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	// other methods
	public void addNewPrescription(DrugLine dl) {
	    this.currentPrescription.add(dl);
	}
}
