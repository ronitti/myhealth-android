package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonitoringListResponse extends ResponseData {
    @SerializedName("monitoring")
    private List<MonitoringDataFull> monitoring;

    public List<MonitoringDataFull> getMonitoring() {
        return monitoring;
    }

    public void setMonitoring(List<MonitoringDataFull> monitoring) {
        this.monitoring = monitoring;
    }
}
