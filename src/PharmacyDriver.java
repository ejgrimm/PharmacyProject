import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PharmacyDriver {

    // data members
    private static ArrayList<Patient> patients;
    private static ArrayList<Doctor> doctors;
    private static ArrayList<Pharmacist> pharmacists;
    private static ArrayList<Nurse> nurses;
    private static ArrayList<Drug> drugs;
    private static ArrayList<Prescription> prescriptions;
    

    public static void main(String[] args) {     

        // putting methods to instantiate objects from files in each Class
        // create an arraylist of Class Objects from reading every file in driver

        createPharmacyObjects();
        System.out.println(drugs.toString());
       
        
    }

    // helper methods
    private static void createPharmacyObjects() {
        readFile("Patients.txt");
        readFile("Doctors.txt");
        readFile("Pharmacists.txt");
        readDrugFile("Drugs.txt");
        //readFile("Prescriptions.txt");
    }
    
    private static void readDrugFile(String fileName) {
        File file = new File(fileName);
        Scanner scan = null;
        
        drugs = new ArrayList<Drug>();
        String currentLine;
        
        try {
            scan = new Scanner(file);
            
        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } 
        
        int drugIndex = 0;
        do {
            drugs.add(new Drug());
            System.out.println(drugIndex);
            
        	while(scan.hasNextLine()) {
        		
        		currentLine=scan.nextLine();
        		if(currentLine.equals(""))
        			break;
 
            for (int i = 0; i <7; i++) {
                
                fillDrugsArrayList(currentLine, i, drugIndex);
                currentLine = scan.nextLine(); 
            }
            
            drugIndex++;
        	}
              
            
            
        }while (scan.hasNextLine());
    }
    
    
    // add watchlist boolean -extra line at bottom of drug
    private static ArrayList<Drug> fillDrugsArrayList(String currentLine, int i, int drugIndex) {
        String[] elements;
        int j;
        if (i==0) {
            drugs.get(drugIndex).setName(currentLine);
        }
        else if (i==1) {
            drugs.get(drugIndex).setChemicalName(currentLine);
        }
        else if (i==2) {
            elements = currentLine.split(",");
            ArrayList<String> ingredients = new ArrayList<String>();
            for (j = 0; j < elements.length; j++) {
                ingredients.add(elements[j]);
            }
            drugs.get(drugIndex).setIngredients(ingredients);
        }
        else if (i==3) {
            drugs.get(drugIndex).setManufacturingCompany(currentLine);
        }
        else if (i==4) {
            drugs.get(drugIndex).setType(currentLine);
        }
        else if (i==5) {
            elements = currentLine.split(",");
            ArrayList<String> conditions = new ArrayList<String>();
            for (j = 0; j < elements.length; j++) {
                conditions.add(elements[j]);
            }
            drugs.get(drugIndex).setListOfConditions(conditions);
        }
        else if (i==6) {
            elements = currentLine.split(",");
            ArrayList<String> contraindications = new ArrayList<String>();
            for (j = 0; j < elements.length; j++) {
                contraindications.add(elements[j]);
            }
            drugs.get(drugIndex).setListOfContraindications(contraindications);            
        }
        else if (i==7) {
            if (currentLine.equals("false")) {
                drugs.get(drugIndex).setWatchlist(false);
            }
            else {
                drugs.get(drugIndex).setWatchlist(true);
            }
        }
        return drugs;
    }
    
    private static void readFile(String fileName) {
        File file = new File(fileName);
        Scanner scan = null;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scan.hasNextLine()) {
            String currentLine = scan.nextLine();
            String[] values = currentLine.split(";");                        
            if (fileName.contains("Patient")) {
                fillPatientsArray(values);
            }
            else if (fileName.contains("Doctor")) {
                fillDoctorsArray(values);
            }
            else if (fileName.contains("Pharmacist")) {
                fillPharmacistsArray(values);
            }
            else if (fileName.contains("Nurse")) {
                fillNursesArray(values);
            }
        }
        scan.close();
    }
    private static ArrayList<Patient> fillPatientsArray(String[] values) {
        patients = new ArrayList<Patient>();
        ArrayList<DrugLine> drugLine = new ArrayList<DrugLine>();
        if (values[7].equals("None")) {
            if(values[0].equals("inPatient")) {
                patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6])));
            }
            else {
                patients.add(new OutPatient(values[1], values[2], values[3], values[4], values[5], values[6]));
            }

        }
        else {
            String[] dlElements = values[7].split(",");
            drugLine.add(new DrugLine(dlElements[0], dlElements[1], Integer.parseInt(dlElements[2]), Integer.parseInt(dlElements[3])));
            if (values[0].equals("inPatient")) {                                        
                patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[6]), drugLine));
            }
            else {
                patients.add(new OutPatient(values[1], values[2], values[3], values[4], values[5], values[6], drugLine));
            }
        }
        return patients;
    }

    private static ArrayList<Doctor> fillDoctorsArray(String[] values) {
        doctors = new ArrayList<Doctor>();
        doctors.add(new Doctor(values[0], values[1], values[2], values[3], values[4]));
        return doctors;
    }

    private static ArrayList<Pharmacist> fillPharmacistsArray(String[] values) {
        pharmacists = new ArrayList<Pharmacist>();
        pharmacists.add(new Pharmacist(values[0], values[1], values[2], values[3], values[4]));
        return pharmacists;
    }

    private static ArrayList<Nurse> fillNursesArray(String[] values) {
        nurses = new ArrayList<Nurse>();
        nurses.add(new Nurse(values[0], values[1], values[2], values[3], values[4], values[5]));
        return nurses;
    }
}

