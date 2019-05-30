package myhealth.ufscar.br.myhealth.data.collect.frequency;

public enum FrequencyType {
    DAILY(0),
    WEEKLY(1),
    CUSTOM_DAYS(2),
    CUSTOM_WEEKS(3);
    Integer type;
    FrequencyType(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

}
