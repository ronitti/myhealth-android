package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

public class ResponseData {
    @SerializedName("message")
    private Object message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("code")
    private int code;

    public Object getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


}
