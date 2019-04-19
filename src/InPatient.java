import java.util.ArrayList;

public class InPatient extends Patient {

	//data members
	private int bedNum;

	//constructors
		
	public InPatient() {
		super();
	}

	public InPatient(String name, String ssn, String address, String phone, String insurance,
			ArrayList<DrugLine> currentPrescription, int bedNum) {
		super(name, ssn, address, phone, insurance, currentPrescription);
		this.bedNum = bedNum;
		setType("InPatient");
	}

	//getters and setters
	public int getBedNum() {
		return bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

}
