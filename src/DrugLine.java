
public class DrugLine {

	//data members
	private String drug;
	private String dosage;
	private int remainingRefills;
	private int timesRefilled;
	
	//constructors
	public DrugLine() {
		remainingRefills = 0;
		timesRefilled = 0;
	}

	public DrugLine(String d, String ds, int r, int t) {
		drug = d;
		dosage = ds;
		remainingRefills = r;
		timesRefilled = t;
	}

	//getters and setters
	public String getDrug() {
		return drug;
	}

	public void setDrug(String drug) {
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

	@Override
	public String toString() {
		return ";" + drug + "," + dosage + "," + remainingRefills	+ "," + timesRefilled;
	}
		
}
