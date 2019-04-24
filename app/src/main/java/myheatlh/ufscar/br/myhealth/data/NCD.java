package myheatlh.ufscar.br.myhealth.data;

public enum NCD {
    HYPERTENSION("Cardiac"),
    CORONARY_ARTERY_DISEASE("Cardiac"),
    DIABETES("Glycemic"),
    OBESITY("Obesity");

    String type;
    NCD(String type){
        this.type = type;
    }
}
