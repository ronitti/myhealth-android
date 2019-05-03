package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;

public class PatientLoadRequest {
    @SerializedName("sus_number")
    private String susNumber;

    @SerializedName("email")
    private String email;

    public PatientLoadRequest(String susNumber) {
        this.susNumber = susNumber;
    }

    public PatientLoadRequest(User user) {
        this.email = user.getEmail();
    }

    public PatientLoadRequest(Patient patient){
        this.susNumber = patient.getSusNumber();
    }

    public void setSusNumber(String susNumber) {
        this.susNumber = susNumber;
    }

    public String getSusNumber() {
        return susNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
