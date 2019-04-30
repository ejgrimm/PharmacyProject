/*Group 2: Alicia Anderson, Eric Grimm, Anthony Green, & Nicola Vuckovic
CMS 270: Pharmacy Project
Presented: April 30, 2019
*/

public class Person {

	// data members
	private String name, ssn, address, phone;

	// constructors
	public Person() {
		this.name = "TBD";
		this.ssn = "TBD";
		this.address = "TBD";
		this.phone = "TBD";
	}

	public Person(String name, String ssn, String address, String phone) {
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.phone = phone;
	}

	// getters & setters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return ";" + name + ";" + ssn + ";" + address + ";" + phone;
	}

}
