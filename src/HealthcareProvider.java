
public class HealthcareProvider extends Person {
    
    // data members 
    private String address, phone;
    
    // constructors 
    public HealthcareProvider() {
        super();
        this.address = "TBD";
        this.phone = "TBD";    
    }

    // getters & setters 
    public HealthcareProvider (String name, String ssn, String address, String phone) {
        super(name, ssn);
        this.address = address;
        this.phone = phone;
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
}
