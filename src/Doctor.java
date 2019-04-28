import java.util.ArrayList;
import java.util.Iterator;

public class Doctor extends Person {
    
    // data members   
    private String specialization;
    private ArrayList<Drug> watchlist;
    
    // constructors - both
    public Doctor() {
        super();
        this.specialization = "TBD";
        this.watchlist = new ArrayList<Drug>();
    }
    
    public Doctor(String name, String ssn, String address, String phone, String specialization) {
        super(name, ssn, address, phone);
        this.specialization = specialization;
        this.watchlist = new ArrayList<Drug>();
    }

    // getters & setters 
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public ArrayList<Drug> getWatchlist() {
        return watchlist;
    }

    public void setWatchlist(ArrayList<Drug> watchlist) {
        this.watchlist = watchlist;
    }
    
    // other methods
    
    // run this method BEFORE addToWatchlist!!!
    public void watchlistAlert(Drug d) {
        // if any Drug occurs on the doctor's watchlist more than xx times
        if (this.watchlist.contains(d)) {
            // count occurrences of Drug in Array List
            int numTimes = 0;
            Iterator<Drug> i = watchlist.iterator();
            while (i.hasNext()) {
                if (i.next() == d) {
                    numTimes++;
                }
            }
            if (numTimes > 5) {
                System.out.println("Caution! " + this.getName() + " has prescribed " + d + " " + numTimes + " times.");
            }
            else {
                System.out.println("All clear!" + this.getName() + " has prescribed " + d + " " + numTimes + " times.");
            }           
        }
        else {
           System.out.println("1st Time Prescription by " + this.getName() + " for "+ d.getName() + "."); 
        }

    }
    
    public String contactDoctor(Doctor d) {
    	return "Doctor's name: " + getName() + "\n" + "Doctor's phone number: " + getPhone() + "\n" + "Doctor's address: " + getAddress();
    }

    // adds new Drug element for ArrayList for every prescription by doctor
    public void addToWatchlist(Drug d) {
        this.watchlist.add(d);
    }

    @Override
    public String toString() {
        return "Doctor [specialization=" + specialization + ", watchlist=" + watchlist + ", toString()="
                + super.toString() + "]";
    }

 
    
    
}
