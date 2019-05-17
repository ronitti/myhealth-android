package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class MonitoringCreateResponse extends ResponseData {
    @SerializedName("monitor_data")
    private MonitoringData monitoringData;

    public MonitoringCreateResponse(String patientId, Integer ncdId, Integer frequencyId) {
        this.monitoringData = new MonitoringData(patientId, ncdId, frequencyId);
    }

    public MonitoringData getMonitoringData() {
        return monitoringData;
    }

    public void setMonitoringData(MonitoringData monitoringData) {
        this.monitoringData = monitoringData;
    }
}
