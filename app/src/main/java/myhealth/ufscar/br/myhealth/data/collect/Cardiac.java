package myhealth.ufscar.br.myhealth.data.collect;

public class Cardiac extends Register {
    private Integer systolic;
    private Integer diastolic;
    private Integer heartBeats;
    private Float weight;

    public Cardiac() {
    }

    public Cardiac(Integer systolic, Integer diastolic, Integer heartBeats, Float weight) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartBeats = heartBeats;
        this.weight = weight;
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
}
