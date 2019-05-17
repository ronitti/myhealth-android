package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class RequestData {
    @SerializedName("request_data")
    private Object requestData;

    public RequestData(Object requestData) {
        this.requestData = requestData;
    }

    public Object getRequestData() {
        return requestData;
    }

    public void setRequestData(Object requestData) {
        this.requestData = requestData;
    }
}
