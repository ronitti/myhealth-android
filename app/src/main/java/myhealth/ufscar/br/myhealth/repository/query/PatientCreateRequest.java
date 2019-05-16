package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.Patient;

public class PatientCreateRequest {
    @SerializedName("patient_data")
    private Patient patientData;

    public PatientCreateRequest(Patient patientData) {
        this.patientData = patientData;
    }

    public Patient getPatientData() {
        return patientData;
    }

    public void setPatientData(Patient patientData) {
        this.patientData = patientData;
    }
}
