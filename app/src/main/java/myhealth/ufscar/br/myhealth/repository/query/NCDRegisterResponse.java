package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class NCDRegisterResponse extends ResponseData {
    @SerializedName("patient_id")
    private String patientId;
    @SerializedName("ncd_id")
    private Integer ncdId;
    @SerializedName("frequency_id")
    private Integer frequencyId;

    public NCDRegisterResponse(String patientId, Integer ncdId, Integer frequencyId) {
        this.patientId = patientId;
        this.ncdId = ncdId;
        this.frequencyId = frequencyId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getNcdId() {
        return ncdId;
    }

    public void setNcdId(Integer ncdId) {
        this.ncdId = ncdId;
    }

    public Integer getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Integer frequencyId) {
        this.frequencyId = frequencyId;
    }
}
