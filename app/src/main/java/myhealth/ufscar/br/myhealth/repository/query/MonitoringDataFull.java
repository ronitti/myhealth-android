package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class MonitoringDataFull {
    @SerializedName("patient_id")
    private String patientId;
    @SerializedName("ncd_id")
    private Integer ncdId;
    @SerializedName("frequency")
    private FrequencyData frequency;

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

    public FrequencyData getFrequency() {
        return frequency;
    }

    public void setFrequency(FrequencyData frequency) {
        this.frequency = frequency;
    }
}
