package myhealth.ufscar.br.myhealth.data.collect;

public class Glycemic extends Register {
    private float glycemicRate;

    public Glycemic(float glycemicRate) {
        this.glycemicRate = glycemicRate;
    }

    public Glycemic() {
    }

    public float getGlycemicRate() {
        return glycemicRate;
    }

    public void setGlycemicRate(float glycemicRate) {
        this.glycemicRate = glycemicRate;
    }
}
