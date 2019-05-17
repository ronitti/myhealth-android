package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import java.text.ParseException;

import myhealth.ufscar.br.myhealth.data.NCD;
import myhealth.ufscar.br.myhealth.data.collect.Cardiac;
import myhealth.ufscar.br.myhealth.data.collect.Glycemic;
import myhealth.ufscar.br.myhealth.data.collect.Obesity;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.utils.DateTime;

public class RegisterData {
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

    public Register toRegister(){
        Register register = null;
        if (ncdId.equals(NCD.HYPERTENSION.getId()) || ncdId.equals(NCD.CORONARY_ARTERY_DISEASE.getId())){
            register = new Cardiac(systolic,diastolic,heartBeats, weight);
        }else if (ncdId.equals(NCD.DIABETES.getId())){
            register = new Glycemic(glycemicRate);
        }else if (ncdId.equals(NCD.OBESITY.getId())){
            register = new Obesity(weight, bodyfat);
        }
        if(register != null){
            register.setNcd(NCD.values()[ncdId]);
            register.setObservation(observation);
            try {
                register.setTimestamp(DateTime.SIMPLE_TIMESTAMP_FORMAT.parse(timestamp));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return register;
    }
}
