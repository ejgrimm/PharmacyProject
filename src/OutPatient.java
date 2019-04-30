/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

import java.util.ArrayList;

public class OutPatient extends Patient {

	// data members
	private String lastVisit;

	// constructors

	public OutPatient() {
		super();
	}

	// with prescriptions

	public OutPatient(String name, String ssn, String address, String phone, String insurance, String lastVisit,
			ArrayList<DrugLine> currentPrescription) {
		super(name, ssn, address, phone, insurance, currentPrescription);
		this.lastVisit = lastVisit;
		setType("outPatient");
	}

	// no prescriptions yet
	public OutPatient(String name, String ssn, String address, String phone, String insurance, String lastVisit) {
		super(name, ssn, address, phone, insurance);
		this.lastVisit = lastVisit;
		setType("outPatient");
	}

	// getters and setters
	public String getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(String lastVisit) {
		this.lastVisit = lastVisit;
	}

	@Override
	public String toString() {
		return super.toString() + ";" + lastVisit + ";"; // getCurrentPrescription();
	}

}
