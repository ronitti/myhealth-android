package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.Patient;

public class PatientLoadRequest {
    @SerializedName("susNumber")
    private String susNumber;

    public PatientLoadRequest(String susNumber) {
        this.susNumber = susNumber;
    }
    public PatientLoadRequest(Patient patient){
        this.susNumber = patient.getSusNumber();
    }
    public String getSusNumber() {
        return susNumber;
    }
}
