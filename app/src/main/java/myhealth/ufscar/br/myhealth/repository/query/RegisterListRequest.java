package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.Patient;

public class RegisterListRequest {
    @SerializedName("patient_id")
    private String patientId;

    public RegisterListRequest(Patient patient){
        patientId = patient.getSusNumber();
        System.out.println("SUS " + patientId);
    }
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}
