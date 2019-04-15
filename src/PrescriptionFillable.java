
public interface PrescriptionFillable {

	public boolean fillPrescription(Prescription rx);
	
	public void requestRefill(DrugLine dl);
	
}
