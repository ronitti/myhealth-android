package myheatlh.ufscar.br.myhealth.data;

public enum FrequencyType {
    DAILY(0),
    WEEKLY(1),
    MONTHLY(2);
    Integer type;
    FrequencyType(Integer type){
        this.type = type;
    }
}
