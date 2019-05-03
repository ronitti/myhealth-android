package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.utils.DateTime;

public class RegisterCreateRequest {
    @SerializedName("patient_id")
    private String patientId;
    @SerializedName("ncd_id")
    private Integer ncdId;
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("observation")
    private String observation;
    @SerializedName("systolic")
    private Integer systolic;
    @SerializedName("diastolic")
    private Integer diastolic;
    @SerializedName("heart_beats")
    private Integer heartBeats;
    @SerializedName("weight")
    private Float weight;
    @SerializedName("glycemic_rate")
    private Float glycemicRate;
    @SerializedName("bodyfat")
    private Float bodyfat;

    public RegisterCreateRequest(Patient patient, Register register){
        patientId = patient.getSusNumber();
        ncdId = register.getNcd().getId();
        timestamp = DateTime.SIMPLE_TIMESTAMP_FORMAT.format(register.getTimestamp());
        observation = register.getObservation();

        if(register instanceof Cardiac){
            systolic = ((Cardiac) register).getSystolic();
            diastolic = ((Cardiac) register).getDiastolic();
            heartBeats = ((Cardiac) register).getHeartBeats();
            weight = ((Cardiac) register).getWeight();
        }else if(register instanceof Glycemic){
            glycemicRate = ((Glycemic) register).getGlycemicRate();
        }else if(register instanceof Obesity){
            weight = ((Obesity) register).getWeight();
            bodyfat = ((Obesity) register).getBodyfat();
        }

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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Integer getSystolic() {
        return systolic;
    }

    public void setSystolic(Integer systolic) {
        this.systolic = systolic;
    }

    public Integer getDiastolic() {
        return diastolic;
    }

    public void setDiastolic(Integer diastolic) {
        this.diastolic = diastolic;
    }

    public Integer getHeartBeats() {
        return heartBeats;
    }

    public void setHeartBeats(Integer heartBeats) {
        this.heartBeats = heartBeats;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getGlycemicRate() {
        return glycemicRate;
    }

    public void setGlycemicRate(Float glycemicRate) {
        this.glycemicRate = glycemicRate;
    }

    public Float getBodyfat() {
        return bodyfat;
    }

    public void setBodyfat(Float bodyfat) {
        this.bodyfat = bodyfat;
    }
}
