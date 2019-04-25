package myheatlh.ufscar.br.myhealth.data.collect;

public class Cardiac extends Register {
    private Float systolicPressure;
    private Float diastolicPressure;
    private Integer pulse;
    private Float weight;

    public Float getSystolicPressure() {
        return systolicPressure;
    }

    public void setSystolicPressure(Float systolicPressure) {
        this.systolicPressure = systolicPressure;
    }

    public Float getDiastolicPressure() {
        return diastolicPressure;
    }

    public void setDiastolicPressure(Float diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public Integer getPulse() {
        return pulse;
    }

    public void setPulse(Integer pulse) {
        this.pulse = pulse;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }
}
