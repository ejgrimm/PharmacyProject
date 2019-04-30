import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Pharmacist extends Person implements Committable {

	// data members
	private String certificationDate;

	public Pharmacist() {
		this.certificationDate = "Unknown";
	}

	public Pharmacist(String name, String ssn, String address, String phone, String certificationDate) {
		super(name, ssn, address, phone);
		this.certificationDate = certificationDate;
	}

	// getters and setters
	public String getCertificationDate() {
		return certificationDate;
	}

	public void setCertificationDate(String certificationDate) {
		this.certificationDate = certificationDate;
	}

	// other methods

	public String fillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients,
			ArrayList<Doctor> doctors, String fileName) {
		// check if authorized
		if (p instanceof OutPatient || p instanceof Nurse) {
			// check if prescription is currently in the system

			Patient patient = patientFinder(rx.getPatient(), patients);

			// System.out.println(patient);

			if (checkContraindications(rx, patient.getCurrentPrescription(), drugs) == true) {
				//System.out.println("Hfsadf");
				return "Has contraindications";
				
				//return false;

			} else {

				for (int i = 0; i < rx.getSetOfDrugLines().size(); i++) {
					int counter = 0;

					if (drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs).getWatchlist() == true) {
						doctorFinder(rx.getPrescribingDoctor(), doctors)
								.watchlistAlert(drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs));
						doctorFinder(rx.getPrescribingDoctor(), doctors)
								.addToWatchlist(drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs));
					}

					
					for (int j = 0; j < patient.getCurrentPrescription().size(); j++) {
						if (patient.getCurrentPrescription().get(j).getDrug()
								.equals(rx.getSetOfDrugLines().get(i).getDrug())) {
							patient.getCurrentPrescription().get(j)
									.setDosage(rx.getSetOfDrugLines().get(i).getDosage());
							patient.getCurrentPrescription().get(j)
									.setRemainingRefills(rx.getSetOfDrugLines().get(i).getRemainingRefills());
							patient.getCurrentPrescription().get(j)
									.setTimesRefilled(rx.getSetOfDrugLines().get(i).getTimesRefilled());

							
							counter++;
						}

					}
					if (counter == 0) {
						DrugLine nd = new DrugLine();
						nd.setDrug(rx.getSetOfDrugLines().get(i).getDrug());
						nd.setDosage(rx.getSetOfDrugLines().get(i).getDosage());
						nd.setRemainingRefills(rx.getSetOfDrugLines().get(i).getRemainingRefills());
						nd.setTimesRefilled(rx.getSetOfDrugLines().get(i).getTimesRefilled());
						
						patient.getCurrentPrescription().add(nd);
						
						
					}
					counter = 0;
				}
			}
			commit("patients.txt", patients);
			//return true;
			//System.out.println("Hfsadf");
			return "Prescription for " + rx.getPatient() + " was successfuly filled";
		} else {
			System.out.println("Hfsadf");
			return "Not authorized to fill out this prescription";
			//System.out.println("Not authorized to fill out this prescription");
			//return false;
		}
	}
	
	public void commitFillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients,
			ArrayList<Doctor> doctors, String fileName) {
		String output=fillPrescription(rx,p,drugs,patients,doctors,fileName);
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter("Output.txt", true));
			w.append(output + "\n");
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String[] commitHelp(ArrayList<Patient> patients) {
		String[] patientInfo = new String[patients.size()];
		for(int i = 0; i < patients.size();i++) {
        	//System.out.print(patients.get(i));
			patientInfo[i]="";
			
			patientInfo[i]+=patients.get(i);
			
        	// inner loop prints patient drug information
        	for(int j = 0; j < patients.get(i).getCurrentPrescription().size(); j++) {
            	//System.out.print(patients.get(i).getCurrentPrescription().get(j) + ";");
            	patientInfo[i]+=patients.get(i).getCurrentPrescription().get(j) + ";";
            }
        	//System.out.println();  
        	
        	if(patients.get(i).getCurrentPrescription().isEmpty()){
        		patientInfo[i]+="None;";
        	}
        }
		
		return patientInfo;
	}
	
	public void commit(String fileName, ArrayList<Patient> patients) {
		
		String[] pInfo=commitHelp(patients);
		
		try {
			BufferedWriter w2 = new BufferedWriter(new FileWriter(fileName, false));
			BufferedWriter w = new BufferedWriter(new FileWriter(fileName, true));
			for(int i=0; i<commitHelp(patients).length; i++) {
				w.append(pInfo[i] + "\n");
				
				
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean checkContraindications(Prescription rx, ArrayList<DrugLine> currentPrescription,
			ArrayList<Drug> drugs) {
		int counter = 0;
		for (int i = 0; i < rx.getSetOfDrugLines().size(); i++) {
			for (int j = 0; j < currentPrescription.size(); j++) {

				Drug d = drugFinder(currentPrescription.get(j).getDrug(), drugs);

				int size = d.getListOfContraindications().size();
				for (int k = 0; k < size; k++)
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
		Drug dr = new Drug();
		for (int i = 0; i < drugs.size(); i++) {
			if (drugs.get(i).getName().equals(drug))
				dr = drugs.get(i);
		}

		return dr;
	}

	public Patient patientFinder(String patient, ArrayList<Patient> patients) {
		Patient p = new Patient();
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getName().equals(patient))
				p = patients.get(i);
		}

		return p;
	}

	public Doctor doctorFinder(String doctor, ArrayList<Doctor> doctors) {
		Doctor d = new Doctor();
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getName().equals(doctor))
				d = doctors.get(i);
		}
		return d;
	}

	
	
	@Override
	public String toString() {
		return "Pharmacist [certificationDate=" + certificationDate + ", toString()=" + super.toString() + "]";
	}

}
