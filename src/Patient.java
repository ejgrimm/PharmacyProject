import java.util.ArrayList;

public  abstract class Patient {

	
	//data members
	private String ssn, name, address, phone, insurance, type;
	private ArrayList<DrugLine> currentPrescription;
	
	//constructors
	
	public Patient(String ssn, String name, String address, String phone, String insurance,String type,
			ArrayList<DrugLine> currentPrescription) {
		super();
		this.ssn = ssn;
		this.type=type;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.insurance = insurance;
		this.currentPrescription = currentPrescription;
	}

	public Patient() {
		super();
	}

	//getters and setters
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

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
	

	
	
	
	
}
