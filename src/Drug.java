/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

import java.util.ArrayList;

public class Drug {

	// data members
	private String name;
	private String chemicalName;
	private ArrayList<String> ingredients;
	private String manufacturingCompany;
	private String type;
	private ArrayList<String> listOfConditions;
	private ArrayList<String> listOfContraindications;
	private boolean watchlist;

	// constructors
	public Drug() {

	}

	public Drug(String n, String cN, ArrayList<String> i, String mC, String t, ArrayList<String> lC,
			ArrayList<String> lCD, boolean w) {
		name = n;
		chemicalName = cN;
		ingredients = i;
		manufacturingCompany = mC;
		type = t;
		listOfConditions = lC;
		listOfContraindications = lCD;
		watchlist = w;
	}

	// getters and setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChemicalName() {
		return chemicalName;
	}

	public void setChemicalName(String chemicalName) {
		this.chemicalName = chemicalName;
	}

	public ArrayList<String> getIngredients() {
		return ingredients;
	}

	public void setIngredients(ArrayList<String> ingredients) {
		this.ingredients = ingredients;
	}

	public String getManufacturingCompany() {
		return manufacturingCompany;
	}

	public void setManufacturingCompany(String manufacturingCompany) {
		this.manufacturingCompany = manufacturingCompany;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getListOfConditions() {
		return listOfConditions;
	}

	public void setListOfConditions(ArrayList<String> listOfConditions) {
		this.listOfConditions = listOfConditions;
	}

	public ArrayList<String> getListOfContraindications() {
		return listOfContraindications;
	}

	public void setListOfContraindications(ArrayList<String> listOfContraindications) {
		this.listOfContraindications = listOfContraindications;
	}

	public boolean getWatchlist() {
		return watchlist;
	}

	public void setWatchlist(boolean watchlist) {
		this.watchlist = watchlist;
	}

	// tostring method for drug
	public String toString() {
		return "Drug [name=" + name + ", chemicalName=" + chemicalName + ", ingredients=" + ingredients
				+ ", manufacturingCompany=" + manufacturingCompany + ", type=" + type + ", listOfConditions="
				+ listOfConditions + ", listOfContraindications=" + listOfContraindications + ", watchlist=" + watchlist
				+ "]";
	}
}
