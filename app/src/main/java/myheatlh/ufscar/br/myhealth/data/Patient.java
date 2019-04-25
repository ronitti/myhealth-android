package myheatlh.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

public class Patient extends User{
    @SerializedName("susNumber")
    private String susNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("dateOfBirth")
    private String dateOfBirth;
    @SerializedName("gender")
    private Character gender;
    @SerializedName("placeOfBirth")
    private String placeOfBirth;
    @SerializedName("mothersName")
    private String mothersName;
    @SerializedName("address")
    private Address address;

    public Patient(String email, String password) {
        super(email, password);
    }

    public String getSusNumber() {
        return susNumber;
    }

    public void setSusNumber(String susNumber) {
        this.susNumber = susNumber;
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

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
