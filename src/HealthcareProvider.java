
public class HealthcareProvider {
    
    // data members 
    private String name, address, phone;
    
    // constructors 
    public HealthcareProvider() {
        this.name = "TBD";
        this.address = "TBD";
        this.phone = "TBD";    
    }

    // getters & setters 
    public HealthcareProvider (String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
