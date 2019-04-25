package myhealth.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("postCode")
    private String postcode;
    @SerializedName("thoroughfare")
    private String thoroughfare;
    @SerializedName("neighborhood")
    private String neighborhood;
    @SerializedName("number")
    private Integer number;
    @SerializedName("complement")
    private String complement;
    @SerializedName("city")
    private String city;
    @SerializedName("state")
    private String state;

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getThoroughfare() {
        return thoroughfare;
    }

    public void setThoroughfare(String thoroughfare) {
        this.thoroughfare = thoroughfare;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
