package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class MonitoringListRequest {
    @SerializedName("sus_number")
    private String sus_number;

    public MonitoringListRequest(String sus_number) {
        this.sus_number = sus_number;
    }
}
