import java.util.ArrayList;

public class Doctor extends HealthcareProvider {
    
    // data members  
    private String specialization;
    private ArrayList<Drug> watchlist;
    
    // constructors
    public Doctor() {
        super();
        this.specialization = "TBD";
        this.watchlist = new ArrayList<Drug>();
    }
    
    public Doctor(String name, String address, String phone, String specialization) {
        super(name, address, phone);
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
    
}
