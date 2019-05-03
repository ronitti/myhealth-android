package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class FrequencyRegisterResponse extends ResponseData{

    @SerializedName("frequency_id")
    private Integer frequencyId;

    public Integer getFrequencyId() {
        return frequencyId;
    }

    public void setFrequencyId(Integer frequencyId) {
        this.frequencyId = frequencyId;
    }
}
