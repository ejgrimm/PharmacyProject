import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class Doctor extends Person {

	// data members
	private String specialization;
	private ArrayList<Drug> watchlist;
	private boolean hasPrescribedAlot;
	private int getDrugCounterWatch;

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
		hasPrescribedAlot = false;
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
		// if any Drug occurs on the doctor's watchlist more than 5 times

		if (this.watchlist.contains(d)) {
			int numTimes = 1; // this starts at 1 since a doctor has already prescribed the drug once if you
								// are in this if
			// count occurrences of Drug in Array List
			Iterator<Drug> i = watchlist.iterator();
			while (i.hasNext()) {
				if (i.next() == d) {
					numTimes++;
				}
			}
			if (numTimes > 5) {
				commit("Output.txt",
						"Caution! " + this.getName() + " has prescribed " + d.getName() + " " + numTimes + " times.  ");
				hasPrescribedAlot = true;
			} else {
				commit("Output.txt", "All clear! " + this.getName() + " has prescribed " + d.getName() + " " + numTimes
						+ " times.  ");
				hasPrescribedAlot = false;
			}
		} else {
			commit("Output.txt", "1st Time Prescription by " + this.getName() + " for " + d.getName() + ".  ");
		}

	}

	public String contactDoctor() {
		return "Doctor's name: " + getName() + "\n" + "Doctor's phone number: " + getPhone() + "\n"
				+ "Doctor's address: " + getAddress();
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

	public boolean isHasPrescribedAlot() {
		return hasPrescribedAlot;
	}

	public void setHasPrescribedAlot(boolean hasPrescribedAlot) {
		this.hasPrescribedAlot = hasPrescribedAlot;
	}

	public int getDrugCounterWatch() {
		return getDrugCounterWatch;
	}

	public void incDCW() {
		getDrugCounterWatch++;
	}

	public void commit(String fileName, String text) {

		try {
			BufferedWriter w = new BufferedWriter(new FileWriter(fileName, true));
			w.append(text);
			w.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
