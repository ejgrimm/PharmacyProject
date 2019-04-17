
public class Pharmacist implements PrescriptionFillable {

	//datamembers
	private String name;
	private String ssn;
	private String certificationDate;
	
	public Pharmacist() {
		name = "not found";
	}
	
	public Pharmacist(String n, String s, String c) {
		
	}

	//getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

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
	
	public void requestRefill(DrugLine dl) {
		
	}
	
	public boolean checkContraindications(Drug d) {
		return false;
	}
	
}
