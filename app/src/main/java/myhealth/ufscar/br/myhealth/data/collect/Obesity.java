package myhealth.ufscar.br.myhealth.data.collect;

public class Obesity extends Register{
    private Float weight;
    private Float bodyfat;

    public Obesity() {
    }

    public Obesity(Float weight, Float bodyfat) {
        this.weight = weight;
        this.bodyfat = bodyfat;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getBodyfat() {
        return bodyfat;
    }

    public void setBodyfat(Float bodyfat) {
        this.bodyfat = bodyfat;
    }
}
