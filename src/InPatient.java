import java.util.ArrayList;

public class InPatient extends Patient implements PrescriptionFillable{

	//data members
	private int bedNum;

	//constructors
		
	public InPatient() {
		super();
	}

	public InPatient(String ssn, String name, String address, String phone, String insurance, String type,
			ArrayList<DrugLine> currentPrescription, int bedNum) {
		super(ssn, name, address, phone, insurance, type, currentPrescription);
		this.bedNum = bedNum;
	}

	//getters and setters
	public int getBedNum() {
		return bedNum;
	}

	public void setBedNum(int bedNum) {
		this.bedNum = bedNum;
	}

	@Override
	public boolean fillPrescription(Prescription rx) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void requestRefill(DrugLine dl) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
