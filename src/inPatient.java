import java.util.ArrayList;

public class inPatient extends Patient {

	//data members
	private int bedNum;

	//constructors
	
	public inPatient(String ssn, String name, String address, String phone, String insurance,
			ArrayList<DrugLine> currentPrescription, int bedNum) {
		super(ssn, name, address, phone, insurance, currentPrescription);
		this.bedNum = bedNum;
	}
	
	public inPatient() {
		super();
	}

	//getters and setters
	public int getBedNum() {
		return bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}
	
	
	
}
