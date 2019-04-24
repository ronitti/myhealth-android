package myheatlh.ufscar.br.myhealth.data;

public class Patient extends User{
    private String sus_number;
    private String name;
    private String dateOfBirth;
    private Character gender;
    private String placeOfBirth;
    private Address address;

    public Patient(String email, String password) {
        super(email, password);
    }

    public String getSus_number() {
        return sus_number;
    }

    public void setSus_number(String sus_number) {
        this.sus_number = sus_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Character getGender() {
        return gender;
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
