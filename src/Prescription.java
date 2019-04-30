/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

import java.util.ArrayList;

public class Prescription {

	// data members
	private String id;
	private String dateIssued;
	private String prescribingDoctor;
	private ArrayList<DrugLine> setOfDrugLines;
	private String patient;

	// constructors
	public Prescription(String id, String dateIssued, String prescribingDoctor, ArrayList<DrugLine> setOfDrugLines,
			String patient) {
		this.id = id;
		this.dateIssued = dateIssued;
		this.prescribingDoctor = prescribingDoctor;
		this.setOfDrugLines = setOfDrugLines;
		this.patient = patient;
	}

	public Prescription() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}

	public String getPrescribingDoctor() {
		return prescribingDoctor;
	}

	public void setPrescribingDoctor(String prescribingDoctor) {
		this.prescribingDoctor = prescribingDoctor;
	}

	public ArrayList<DrugLine> getSetOfDrugLines() {
		return setOfDrugLines;
	}

	public void setSetOfDrugLines(ArrayList<DrugLine> setOfDrugLines) {
		this.setOfDrugLines = setOfDrugLines;
	}

	public String getPatient() {
		return patient;
	}

	public void setPatient(String patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "Prescription [id=" + id + ", dateIssued=" + dateIssued + ", prescribingDoctor=" + prescribingDoctor
				+ ", setOfDrugLines=" + setOfDrugLines + ", patient=" + patient + "]";
	}

}
