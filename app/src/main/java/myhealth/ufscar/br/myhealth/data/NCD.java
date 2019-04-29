package myhealth.ufscar.br.myhealth.data;

public enum NCD {
    HYPERTENSION(0,"Cardiac"),
    CORONARY_ARTERY_DISEASE(1,"Cardiac"),
    DIABETES(2,"Glycemic"),
    OBESITY(3,"Obesity");

    Integer id;
    String type;
    NCD(Integer id, String type){
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
