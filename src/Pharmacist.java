
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
	public boolean fillPrescription(Prescription rx) {
		return false;
	}
	
	public boolean checkContraindications(Drug d) {
		return false;
	}
	
}
