package myhealth.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class Patient extends User implements Serializable {
    @SerializedName("sus_number")
    private String susNumber;
    @SerializedName("name")
    private String name;
    @SerializedName("date_of_birth")
    private Date dateOfBirth;
    @SerializedName("gender")
    private Character gender;
    @SerializedName("place_of_birth")
    private String placeOfBirth;
    @SerializedName("mothers_name")
    private String mothersName;
    @SerializedName("address")
    private Address address;

    public Patient(String email, String password) {
        super(email, password);
    }
    public Patient(){
        super();
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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
