import java.util.ArrayList;

public class Pharmacist extends Person {

	//data members
	private String certificationDate;
	
	public Pharmacist() {
		this.certificationDate = "Unknown";
	}
	
	public Pharmacist(String name, String ssn, String address, String phone, String certificationDate) {
		super(name, ssn, address, phone);
		this.certificationDate = certificationDate;
	}

	//getters and setters
	public String getCertificationDate() {
		return certificationDate;
	}

	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}
	
	//other methods
//	public boolean fillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients, ArrayList<Doctor> doctors) {
//		//check if authorized
//		if (p instanceof OutPatient || p instanceof Nurse) {
//			//check if prescription is currently in the system
//			Patient patient = rx.getPatient();
//			if (rx.getSetOfDrugLines().contains(patient.getCurrentPrescription())) {
//				//update
//				
//			}
//			else {
//				//add new drugline to Patient object and update file
//			}
//				
//		}
//		else
//			return false;
//			System.out.println("Not authorized to fill out this prescription");
//	}
//	
//	public boolean checkContraindications(Drug d) {
//		
//	}
	
    public boolean fillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients,
            ArrayList<Doctor> doctors) {
        // check if authorized
        if (p instanceof OutPatient || p instanceof Nurse) {
            // check if prescription is currently in the system
            
            Patient patient = patientFinder(rx.getPatient(), patients);
            
            
            if (checkContraindications(rx, patient.getCurrentPrescription(), drugs) == true) {

                System.out.println("Has contraindications");
                return false;

            } 
            else {

                for (int i = 0; i < rx.getSetOfDrugLines().size(); i++) {

                    //if(drugFinder(rx.getSetOfDrugLines().get(i).getDrug(),drugs))

                    for (int j = 0; j < patient.getCurrentPrescription().size(); j++) {
                        if (patient.getCurrentPrescription().get(j).getDrug()
                                .equals(rx.getSetOfDrugLines().get(i).getDrug())) {
                            patient.getCurrentPrescription().get(j)
                            .setDosage(rx.getSetOfDrugLines().get(i).getDosage());
                            patient.getCurrentPrescription().get(j)
                            .setRemainingRefills(rx.getSetOfDrugLines().get(i).getRemainingRefills());
                            patient.getCurrentPrescription().get(j)
                            .setTimesRefilled(rx.getSetOfDrugLines().get(i).getTimesRefilled());


                            return true;
                        }
                    }
                    DrugLine nd = new DrugLine();
                    nd.setDrug(rx.getSetOfDrugLines().get(i).getDrug());
                    nd.setDosage(rx.getSetOfDrugLines().get(i).getDosage());
                    nd.setRemainingRefills(rx.getSetOfDrugLines().get(i).getRemainingRefills());
                    nd.setTimesRefilled(rx.getSetOfDrugLines().get(i).getTimesRefilled());

                    patient.getCurrentPrescription().add(nd);
                    return true;
                }
            }
            return true;
        } else {

            System.out.println("Not authorized to fill out this prescription");
            return false;
        }
    }

    public boolean checkContraindications(Prescription rx, ArrayList<DrugLine> currentPrescription,
            ArrayList<Drug> drugs) {
        int counter = 0;
        for (int i = 0; i < rx.getSetOfDrugLines().size(); i++) {
            for (int j = 0; j < currentPrescription.size(); j++) {
                for (int k = 0; k < drugFinder(currentPrescription.get(j).getDrug(), drugs).getListOfContraindications()
                        .size(); k++)
                    if (drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs).getType()
                            .equals(drugFinder(currentPrescription.get(j).getDrug(), drugs).getListOfContraindications()
                                    .get(k))) {
                        counter++;
                    }
            }

        }
        if (counter != 0) {
            return true;
        } else
            return false;

    }

    public Drug drugFinder(String drug, ArrayList<Drug> drugs) {
        for (int i = 0; i < drugs.size(); i++) {
            if (drugs.get(i).getType().equals(drug))
                return drugs.get(i);
        }

        return null;
    }	

    public Patient patientFinder(String patient, ArrayList<Patient> patients) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getType().equals(patient))
                return patients.get(i);
        }

        return null;
    }   
    
    @Override
    public String toString() {
        return "Pharmacist [certificationDate=" + certificationDate + ", toString()=" + super.toString() + "]";
    }
	
	
	
}
