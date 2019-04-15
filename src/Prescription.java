
public class Prescription {

	//data members
	private String id;
	private String dateIssued;
	private Doctor prescribingDoctor;
	private ArrayList<DrugLine> setOfDrugLines;
	private Patient patient;
	
	
	public Prescription(String id, String dateIssued, Doctor prescribingDoctor, ArrayList<DrugLine> setOfDrugLines,
			Patient patient) {
		super();
		this.id = id;
		this.dateIssued = dateIssued;
		this.prescribingDoctor = prescribingDoctor;
		this.setOfDrugLines = setOfDrugLines;
		this.patient = patient;
	}


	public String getId() {
		return id;
	}-


	public void setId(String id) {
		this.id = id;
	}


	public String getDateIssued() {
		return dateIssued;
	}


	public void setDateIssued(String dateIssued) {
		this.dateIssued = dateIssued;
	}


	public Doctor getPrescribingDoctor() {
		return prescribingDoctor;
	}


	public void setPrescribingDoctor(Doctor prescribingDoctor) {
		this.prescribingDoctor = prescribingDoctor;
	}


	public ArrayList<DrugLine> getSetOfDrugLines() {
		return setOfDrugLines;
	}


	public void setSetOfDrugLines(ArrayList<DrugLine> setOfDrugLines) {
		this.setOfDrugLines = setOfDrugLines;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	
	
}
