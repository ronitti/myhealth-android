package myheatlh.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myheatlh.ufscar.br.myhealth.data.Patient;

public class PatientLoadResponse extends ResponseData{

    @SerializedName("patientData")
    private Patient patientData;

    public Patient getPatientData() {
        return patientData;
    }

    public void setPatientData(Patient patientData) {
        this.patientData = patientData;
    }

}
