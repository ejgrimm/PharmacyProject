/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PharmacyDriver {

	// global data members for all object types
	private static ArrayList<Patient> patients;
	private static ArrayList<Doctor> doctors;
	private static ArrayList<Doctor> doctorsAlot;
	private static ArrayList<Pharmacist> pharmacists;
	private static ArrayList<Nurse> nurses;
	private static ArrayList<Drug> drugs;
	private static ArrayList<Prescription> prescriptions;

	public static void main(String[] args) {

		// putting methods to instantiate objects from files in each Class
		// create an arraylist of Class Objects from reading every file in driver
		createPharmacyObjects();

		//this clears the output.txt file so we can show that it all works properly
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", false));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//calling the readTransactions 
		readTransactions();

	}
	// helper methods
	
	// reads Transactions text file to provide instructions to Pharmacy system
	public static void readTransactions() {
		try {
			String line;
			Scanner scan = new Scanner(new File("Transactions.txt"));
			
			//loop that scans the transactions file and according to the contents of the line it calls the appropriate methods or does appropriate actions
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				String contents[] = line.split(" ");
				if (contents[0].equals("Pharmacist")) {
					pharmacistFinder(contents[1] + " " + contents[2], pharmacists).commitFillPrescription(
							prescriptionFinder(contents[4], prescriptions),
							findPerson(contents[6] + " " + contents[7], patients, doctors, nurses), drugs, patients,
							doctors, "patients.txt");
				} else if (contents[0].equals("Find")) {
					ArrayList<Doctor> baddies = findDoctors(doctors, drugFinder(contents[3], drugs));
					BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", true));
					for (int i = 0; i < baddies.size(); i++) {
						w.append(baddies.get(i).getName() + "\n");
						w.close();
					}
				} else if (contents[0].equals("Contraindications")) {
					findContraindications(contents[2], drugs);
				} else if (contents[0].equals("Contact")) {
					BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", true));
					w.append(doctorFinder(contents[1] + " " + contents[2], doctors).contactDoctor() + "\n");
					w.close();
				}

			}
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// helper for finding person by type
	public static Person findPerson(String person, ArrayList<Patient> patients, ArrayList<Doctor> doctors,
			ArrayList<Nurse> nurses) {
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getName().equals(person))
				return doctors.get(i);
		}
		for (int i = 0; i < patients.size(); i++) {
			if (patients.get(i).getName().equals(person))
				return patients.get(i);
		}
		for (int i = 0; i < nurses.size(); i++) {
			if (nurses.get(i).getName().equals(person))
				return nurses.get(i);
		}
		return null;
	}
	
	// searching pharmacist ArrayList to find pharmacist who will be filling prescription
	public static Pharmacist pharmacistFinder(String ph, ArrayList<Pharmacist> pharmacists) {
		Pharmacist p = new Pharmacist();
		for (int i = 0; i < pharmacists.size(); i++) {
			if (pharmacists.get(i).getName().equals(ph))
				p = pharmacists.get(i);
		}
		return p;
	}

	// returns the prescription object that has a certain ID
	public static Prescription prescriptionFinder(String id, ArrayList<Prescription> prescriptions) {
		Prescription pr = new Prescription();
		for (int i = 0; i < prescriptions.size(); i++) {
			if (prescriptions.get(i).getId().equals(id))
				pr = prescriptions.get(i);
		}
		return pr;
	}

	// returns a doctor method of certain name
	public static Doctor doctorFinder(String doctor, ArrayList<Doctor> doctors) {
		Doctor d = new Doctor();
		for (int i = 0; i < doctors.size(); i++) {
			if (doctors.get(i).getName().equals(doctor))
				d = doctors.get(i);
		}
		return d;
	}

	// returns a drug of certain name
	public static Drug drugFinder(String drug, ArrayList<Drug> drugs) {
		Drug dr = new Drug();
		for (int i = 0; i < drugs.size(); i++) {
			if (drugs.get(i).getName().equals(drug))
				dr = drugs.get(i);
		}

		return dr;
	}

	
	// method that prints contraindications of a certain drug to the output.txt file
	public static void findContraindications(String drug, ArrayList<Drug> drugs) {

		Drug d = drugFinder(drug, drugs);
		try {
			BufferedWriter w = new BufferedWriter(new FileWriter("output.txt", true));
			w.append("Contraindications for " + drug + " are: " + "\n");
			int size = d.getListOfContraindications().size();
			for (int k = 0; k < size; k++) {
				w.append(d.getListOfContraindications().get(k) + "\n");
			}
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void createPharmacyObjects() {

		// calls all methods that read files and initialize objects in arraylists
		readPatientFile("Patients.txt");
		readDoctorFile("Doctors.txt");
		readPharmacistFile("Pharmacists.txt");
		readDrugFile("Drugs.txt");
		readNurseFile("Nurses.txt");
		readPrescriptionsFile("Prescriptions.txt");
	}

	private static void readDrugFile(String fileName) {

		// initialize file and scanner
		File file = new File(fileName);
		Scanner scan = null;

		// instantiate drugs arrayList
		drugs = new ArrayList<Drug>();
		String currentLine;

		try {
			scan = new Scanner(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// drugs is a structured file where each drug has 8 lines of information

		int drugIndex = 0;
		do {

			// gets current line and will send it to fillDrugsArrayList along with the i so
			// that we can keep track
			// of which line that we are on in the drug, and a drugIndex variable to keep
			// track of the drug in order
			// to add it to the arrayList
			while (scan.hasNextLine()) {
				drugs.add(new Drug());
				currentLine = scan.nextLine();
				if (currentLine.equals(""))
					break;

				for (int i = 0; i < 8; i++) {
					fillDrugsArrayList(currentLine, i, drugIndex);
					currentLine = scan.nextLine();
				}
				drugIndex++;
			}

		} while (scan.hasNextLine());
	}

	// this method initializes drugs in the drugs array
	// this method receives an i variable which corresponds to a specific line in
	// the drug file
	// each line is a data member of a drug object and all of them have to be
	// initialized differently
	// each if else case sets a different data member in the drug object
	private static ArrayList<Drug> fillDrugsArrayList(String currentLine, int i, int drugIndex) {
		String[] elements;
		int j; // used for loops (for loops)
		if (i == 0) {
			drugs.get(drugIndex).setName(currentLine);
		} else if (i == 1) {
			drugs.get(drugIndex).setChemicalName(currentLine);
		} else if (i == 2) {
			elements = currentLine.split(",");
			ArrayList<String> ingredients = new ArrayList<String>();
			for (j = 0; j < elements.length; j++) {
				ingredients.add(elements[j]);
			}
			drugs.get(drugIndex).setIngredients(ingredients);
		} else if (i == 3) {
			drugs.get(drugIndex).setManufacturingCompany(currentLine);
		} else if (i == 4) {
			drugs.get(drugIndex).setType(currentLine);
		} else if (i == 5) {
			elements = currentLine.split(",");
			ArrayList<String> conditions = new ArrayList<String>();
			for (j = 0; j < elements.length; j++) {
				conditions.add(elements[j]);
			}
			drugs.get(drugIndex).setListOfConditions(conditions);
		} else if (i == 6) {
			elements = currentLine.split(",");
			ArrayList<String> contraindications = new ArrayList<String>();
			for (j = 0; j < elements.length; j++) {
				contraindications.add(elements[j]);
			}
			drugs.get(drugIndex).setListOfContraindications(contraindications);
		} else if (i == 7) {
			if (currentLine.equals("false")) {
				drugs.get(drugIndex).setWatchlist(false);
			} else {
				drugs.get(drugIndex).setWatchlist(true);
			}
		}
		return drugs;
	}

	//method that reads the patients file and calls the fillPatientsArray method
	private static void readPatientFile(String fileName) {
		File file = new File(fileName);
		Scanner scan = null;
		patients = new ArrayList<Patient>();
		String currentLine;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			currentLine = scan.nextLine();
			String[] values = currentLine.split(";");
			fillPatientsArray(values);
		}

		scan.close();
	}

	//helper method that fills the patients arrayList with patient info
	private static ArrayList<Patient> fillPatientsArray(String[] values) {
		ArrayList<DrugLine> drugLine = new ArrayList<DrugLine>();
		if (values[7].equals("None")) {
			if (values[0].equals("inPatient")) {
				patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5],
						Integer.parseInt(values[6])));
			} else {
				patients.add(new OutPatient(values[1], values[2], values[3], values[4], values[5], values[6]));
			}

		} else {
			String[] dlElements = values[7].split(",");
			drugLine.add(new DrugLine(dlElements[0], dlElements[1], Integer.parseInt(dlElements[2]),
					Integer.parseInt(dlElements[3])));
			if (values[0].equals("inPatient")) {
				patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5],
						Integer.parseInt(values[6]), drugLine));
			} else {
				patients.add(
						new OutPatient(values[1], values[2], values[3], values[4], values[5], values[6], drugLine));
			}
		}
		return patients;
	}

	
	//reads the doctors file and calls the fillDoctorsArray helper method
	private static void readDoctorFile(String fileName) {
		File file = new File(fileName);
		Scanner scan = null;
		doctors = new ArrayList<Doctor>();
		String currentLine;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			currentLine = scan.nextLine();
			String[] values = currentLine.split(";");
			fillDoctorsArray(values);
		}

		scan.close();
	}

	
	//method that returns the list of the doctors that have prescribed a certain drug more than allowed number of times
	private static ArrayList<Doctor> findDoctors(ArrayList<Doctor> doctors, Drug drug) {

		doctorsAlot = new ArrayList<Doctor>();

		// drug on watchlist?
		if (drug.getWatchlist()) {
			// yes? --> check every doctors list for watchlist drug
			for (int i = 0; i < doctors.size(); i++) {
				if (doctors.get(i).getWatchlist().contains(drug)) {
					for (int a = 0; a < doctors.get(i).getWatchlist().size(); a++) {
						if (doctors.get(i).getWatchlist().get(a) == drug) {
							doctors.get(i).incDCW();
						}
					}
				}
				if (doctors.get(i).getDrugCounterWatch() > 5) {
					doctorsAlot.add(doctors.get(i));
				}
			}
		}

		return doctorsAlot;

	}

	//method that fills the doctors arraylist with doctor information
	private static ArrayList<Doctor> fillDoctorsArray(String[] values) {
		doctors.add(new Doctor(values[0], values[1], values[2], values[3], values[4]));
		return doctors;
	}

	
	//reads pharmacist file and calls fillPharmacistArray helper method
	private static void readPharmacistFile(String fileName) {
		File file = new File(fileName);
		Scanner scan = null;
		pharmacists = new ArrayList<Pharmacist>();
		String currentLine;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			currentLine = scan.nextLine();
			String[] values = currentLine.split(";");
			fillPharmacistsArray(values);
		}

		scan.close();
	}

	//helper method that fills the pharmacists arraylist with pharmacist info
	private static ArrayList<Pharmacist> fillPharmacistsArray(String[] values) {

		pharmacists.add(new Pharmacist(values[0], values[1], values[2], values[3], values[4]));
		return pharmacists;
	}

	
	//reads the nursefile and calls fillsNurseArray helper method method
	private static void readNurseFile(String fileName) {
		File file = new File(fileName);
		Scanner scan = null;
		nurses = new ArrayList<Nurse>();
		String currentLine;

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		while (scan.hasNextLine()) {
			currentLine = scan.nextLine();
			String[] values = currentLine.split(";");
			fillNursesArray(values);
		}

		scan.close();
	}

	//helper method that fills the nurses arraylist with nurse info
	private static ArrayList<Nurse> fillNursesArray(String[] values) {
		nurses.add(new Nurse(values[0], values[1], values[2], values[3], values[4], values[5]));
		return nurses;
	}

	
	//method that reads prescription file and fills the prescriptions arrayList
	private static void readPrescriptionsFile(String fileName) {

		// initialize variables to be used in this method
		File file = new File(fileName);
		Scanner scan = null;
		String[] values;
		String currentLine;
		String drugLine;
		prescriptions = new ArrayList<Prescription>();

		try {
			scan = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// the outer while loop will get the prescription line and get its contents
		// this will be used later to add it to a prescription object
		while (scan.hasNextLine()) {
			ArrayList<DrugLine> drugLines = new ArrayList<DrugLine>();
			currentLine = scan.nextLine();
			values = currentLine.split(";");

			// the inner while loop will get each of the druglines in a prescription
			// the contents are added to a DrugLine arraylist
			while (scan.nextLine().equals("DRUGLINE")) {
				drugLine = scan.nextLine();
				String[] values2 = drugLine.split(";");
				drugLines.add(new DrugLine(values2[0], values2[1], Integer.parseInt(values2[2]),
						Integer.parseInt(values2[3])));
			}

			// adds a prescription using the prescription information and the druglines
			prescriptions.add(new Prescription(values[0], values[1], values[2], drugLines, values[3]));
		}
	}

}
