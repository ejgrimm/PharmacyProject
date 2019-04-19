
public class DrugLine {

	//data members
	private Drug drug;
	private String dosage;
	private int remainingRefills;
	private int timesRefilled;
	
	//constructors
	public DrugLine() {
		remainingRefills = 0;
		timesRefilled = 0;
	}

	public DrugLine(Drug d, String ds, int r, int t) {
		drug = d;
		dosage = ds;
		remainingRefills = r;
		timesRefilled = t;
	}

	//getters and setters
	public Drug getDrug() {
		return drug;
	}

	public void setDrug(Drug drug) {
		this.drug = drug;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public int getRemainingRefills() {
		return remainingRefills;
	}

	public void setRemainingRefills(int remainingRefills) {
		this.remainingRefills = remainingRefills;
	}

	public int getTimesRefilled() {
		return timesRefilled;
	}

	public void setTimesRefilled(int timesRefilled) {
		this.timesRefilled = timesRefilled;
	}
	
	// other methods
	public void updateDrugLine() {
	    this.timesRefilled += 1;
	    this.remainingRefills -= 1;
	}
	
	
}
