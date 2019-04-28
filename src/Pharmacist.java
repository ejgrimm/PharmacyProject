import java.util.ArrayList;

public class Pharmacist extends Person {

	//data members
	private String certificationDate;
	
	public Pharmacist() {
		this.certificationDate = "Unknown";
	}
	
	public Pharmacist(String name, String ssn, String address, String phone, String certificationDate) {
		super(name, ssn, address, phone);
		this.certificationDate = certificationDate;
	}

	//getters and setters
	public String getCertificationDate() {
		return certificationDate;
	}

	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}
	
	//other methods
	public boolean fillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
		//check if authorized
		if (p instanceof OutPatient || p instanceof Nurse) {
			//check if prescription is currently in the system
			Patient patient = rx.getPatient();
			if (rx.getSetOfDrugLines().contains(patient.getCurrentPrescription())) {
				//update
				
			}
			else {
				//add new drugline to Patient object and update file
			}
				
		}
		else
			return false;
			System.out.println("Not authorized to fill out this prescription");
	}
	
	public boolean checkContraindications(Drug d) {
		
	}
	
}
