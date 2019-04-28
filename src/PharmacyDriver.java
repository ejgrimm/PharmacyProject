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
        System.out.println(patients.toString());
        System.out.println(doctors.toString());
        System.out.println(pharmacists.toString());
        System.out.println(nurses.toString());
        System.out.println(prescriptions.toString());
       
        
    }

    // helper methods
    private static void createPharmacyObjects() {
        readPatientFile("Patients.txt");
        readDoctorFile("Doctors.txt");
        readPharmacistFile("Pharmacists.txt");
        readDrugFile("Drugs.txt");
        readNurseFile("Nurses.txt");
        readPrescriptionsFile("Prescriptions.txt");
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
    
    private static ArrayList<Patient> fillPatientsArray(String[] values) {       
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
    
    private static ArrayList<Doctor> fillDoctorsArray(String[] values) {       
        doctors.add(new Doctor(values[0], values[1], values[2], values[3], values[4]));
        return doctors;
    }

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
    
    private static ArrayList<Pharmacist> fillPharmacistsArray(String[] values) {
        
        pharmacists.add(new Pharmacist(values[0], values[1], values[2], values[3], values[4]));
        return pharmacists;
    }

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
    
    private static ArrayList<Nurse> fillNursesArray(String[] values) {        
        nurses.add(new Nurse(values[0], values[1], values[2], values[3], values[4], values[5]));
        return nurses;
    }
      
    
    private static void readPrescriptionsFile(String fileName) {
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
        
        while(scan.hasNextLine()) {
        	ArrayList<DrugLine> drugLines = new ArrayList<DrugLine>();
        	
        	currentLine = scan.nextLine();
        	values = currentLine.split(";");
        	
        	
        	while(scan.nextLine().equals("DRUGLINE")) {           	
        		drugLine = scan.nextLine();
        		String[] values2 = drugLine.split(";");
        		drugLines.add(new DrugLine(values2[0],values2[1],Integer.parseInt(values2[2]),Integer.parseInt(values2[3])));
        	}
        	prescriptions.add(new Prescription(values[0],values[1],values[2],drugLines,values[3]));
        	
        
        	
        		
        	
        }
        
    }
}

