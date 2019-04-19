import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PharmacyDriver {

    public static void main(String[] args) {
        
        // putting methods to instantiate objects from files in each Class
        // create an arraylist of Class Objects from reading every file in driver
        
        readFile("Patient.txt");
        readFile("Drugs.txt");
       
        
        
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
            //fillArrayList(scan.nextLine());
        }
        scan.close();
    }
    
}

