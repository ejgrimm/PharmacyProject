import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PharmacyDriver {
    
    // data members
    private static ArrayList<Patient> patients;
    private static ArrayList<Doctor> doctors;

    public static void main(String[] args) {     
        
        // putting methods to instantiate objects from files in each Class
        // create an arraylist of Class Objects from reading every file in driver
        
        readFile("Patients.txt");
        readFile("Doctors.txt");
        //readFile("Drugs.txt");
        //readFile("Pharmacist.txt");
        //readFile("Prescriptions.txt");
       
        
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
        }
        scan.close();
    }
    
    private static ArrayList<Patient> fillPatientsArray(String[] values) {
        patients = new ArrayList<Patient>();
        ArrayList<DrugLine> drugLine = new ArrayList<DrugLine>();
        if (values[6].equals("None")) {
           if(values[0].equals("inPatient")) {
               patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5], Integer.parseInt(values[7])));
           }
           else {
               patients.add(new OutPatient(values[1], values[2], values[3], values[4], values[5], drugLine, values[7]));
           }
                
        }
        else {
            String[] dlElements = values[6].split(",");
            drugLine.add(new DrugLine(dlElements[0], dlElements[1], Integer.parseInt(dlElements[2]), Integer.parseInt(dlElements[3])));
            if (values[0].equals("inPatient")) {                                        
                patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5], drugLine, Integer.parseInt(values[7])));
            }
            else {
                patients.add(new OutPatient(values[1], values[2], values[3], values[4], values[5], drugLine, values[7]));
            }
        }
        return patients;
    }
    
    private static ArrayList<Doctor> fillDoctorsArray(String[] values) {
        doctors = new ArrayList<Doctor>();
        doctors.add(new Doctor(values[0], values[1], values[2], values[3], values[4]));
        return doctors;
    }
}

