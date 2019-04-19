import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class PharmacyDriver {

    public static void main(String[] args) {
        
        // putting methods to instantiate objects from files in each Class
        // create an arraylist of Class Objects from reading every file in driver
        
        readFile("Patient.txt");
        readFile("Drugs.txt");
        readFile("Doctors.txt");
        readFile("Pharmacist.txt");
        readFile("Prescriptions.txt");
       
        
        
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
                ArrayList<Patient> patients = new ArrayList<Patient>();
                if (values[0].equals("inPatient")) {
                    ArrayList<DrugLine> drugLine = new ArrayList<DrugLine>();
                    // NONFUNCTIONAL - TO FIX drugLine.add(new DrugLine(Drug(can't be a string!!), dlElements[2], Integer.parseInt(dlElements[3]), Integer.parseInt(dlElements[4])));
                    patients.add(new InPatient(values[1], values[2], values[3], values[4], values[5], drugLine, Integer.parseInt(values[7])));
                }
            }
            else if (fileName.contains("Doctor")) {
                
            }
        }
        scan.close();
    }
    
}

