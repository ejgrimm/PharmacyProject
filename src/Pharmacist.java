/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pharmacist extends Person {

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

	
	//method that will execute filling of the prescription 
	public String fillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients,
			ArrayList<Doctor> doctors, String fileName) {
		// check if authorized
		if (p instanceof OutPatient || p instanceof Nurse) {
			// check if prescription is currently in the system

			//creating the patient object and initializing it with the patient object with the same name as on the prescription
			Patient patient = patientFinder(rx.getPatient(), patients);
			
		
			// check if prescription is currently in the system
			//we are calling the checkContraindications method that returns a boolean
			if (checkContraindications(rx, patient.getCurrentPrescription(), drugs) == true) {
				
				//if it has contraindications it just prints out the reason why the prescription was not filled
				return "Prescription with " + rx.getId() + " ID has contraindications for " + rx.getPatient();

			} else {

				//if there are no contraindications we can go through the list of drugs that are in the prescription and add them to the patient or just update it if the patient already has it
				for (int i = 0; i < rx.getSetOfDrugLines().size(); i++) {
					
					//counter variable that is used to track if we are updating a current or adding a new prescription
					int counter = 0;
					
					
					//here we are checking if the drug getting prescribed is on the watchlist and if it is than we call necessary methods from the doctor class 
					if (drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs).getWatchlist() == true) {
						doctorFinder(rx.getPrescribingDoctor(), doctors)
								.watchlistAlert(drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs));
						doctorFinder(rx.getPrescribingDoctor(), doctors)
								.addToWatchlist(drugFinder(rx.getSetOfDrugLines().get(i).getDrug(), drugs));
					}

					
					//in this loop we are checking if the patient has the drug prescribed already and if yes we just update its drugline
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
					
					//here we are checking if the drug is a new drug and if not we are adding it to the patient's drugline
					if (counter == 0) {
						DrugLine nd = new DrugLine();
						nd.setDrug(rx.getSetOfDrugLines().get(i).getDrug());
						nd.setDosage(rx.getSetOfDrugLines().get(i).getDosage());
						nd.setRemainingRefills(rx.getSetOfDrugLines().get(i).getRemainingRefills());
						nd.setTimesRefilled(rx.getSetOfDrugLines().get(i).getTimesRefilled());

						patient.getCurrentPrescription().add(nd);

					}
					
					//to successfully track we need to reset the counter for every drug
					counter = 0;
				}
			}
			
			//after all the drugLines for the patient are updated we can call the commit method that prints updates that information on the patients.txt text file
			commit("patients.txt", patients);
			
			//here we return the notification that the prescription was successfully filed
			return "Prescription for " + rx.getPatient() + " was successfully filled";
		} else {
			//here we return the message that the person was not authorized to fill the prescription
			return "Not authorized to fill out this prescription";
		}
	}

	
	//helper method takes the string output from fillPrescription and prints it out to the output.txt file
	public void commitFillPrescription(Prescription rx, Person p, ArrayList<Drug> drugs, ArrayList<Patient> patients,
			ArrayList<Doctor> doctors, String fileName) {
		
		//string that takes the output
		String output = fillPrescription(rx, p, drugs, patients, doctors, fileName);
		
		//printing the output to the file
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter("Output.txt", true));
			w.append(output + "\n");
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//helper method for commit that brings all of the patients information into one stirng array
	public String[] commitHelp(ArrayList<Patient> patients) {
		String[] patientInfo = new String[patients.size()];
		for (int i = 0; i < patients.size(); i++) {
			patientInfo[i] = "";

			patientInfo[i] += patients.get(i);

			// inner loop prints patient drug information
			for (int j = 0; j < patients.get(i).getCurrentPrescription().size(); j++) {
				patientInfo[i] += patients.get(i).getCurrentPrescription().get(j) + ";";
			}

			if (patients.get(i).getCurrentPrescription().isEmpty()) {
				patientInfo[i] += "None;";
			}
		}

		return patientInfo;
	}

	
	//method that updates the information on the patient.txt file during filling of the prescription
	public void commit(String fileName, ArrayList<Patient> patients) {
		
		//string array that takes the output of commitHelp mehtod
		String[] pInfo = commitHelp(patients);

		//printing out the string array to the fiel
		try {
			BufferedWriter w2 = new BufferedWriter(new FileWriter(fileName, false));
			BufferedWriter w = new BufferedWriter(new FileWriter(fileName, true));
			for (int i = 0; i < commitHelp(patients).length; i++) {
				w.append(pInfo[i] + "\n");

			}
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	//helper method that checks if the drugs in the  prescription have any contraindications with the drugs that patient is already taking 
	public boolean checkContraindications(Prescription rx, ArrayList<DrugLine> currentPrescription,
			ArrayList<Drug> drugs) {
		
		//counter that keeps track if we hit the contraindication
		int counter = 0;
		
		//loop that scans for contraindications and if it finds one it increments the counter
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
		
		//if the counter is unchanged then there are no contraindications and if not than there are some
		if (counter != 0) {
			return true;
		} else
			return false;

	}

	
	//finder methods that return the object that is related to the string that we are searing 
	
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
	
	
	//toString method
	@Override
	public String toString() {
		return "Pharmacist [certificationDate=" + certificationDate + ", toString()=" + super.toString() + "]";
	}

}
