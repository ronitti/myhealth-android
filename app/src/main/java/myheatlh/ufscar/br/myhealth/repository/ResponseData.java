package myheatlh.ufscar.br.myhealth.repository;

import com.google.gson.annotations.SerializedName;

import myheatlh.ufscar.br.myhealth.data.User;

public class ResponseData {

    @SerializedName("message")
    private String message;
    @SerializedName("success")
    private boolean success;
    @SerializedName("userData")
    private User userData;

    public String getMessage() {
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

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "message='" + message + '\'' +
                ", success=" + success +
                //", userData=" + userData +
                '}';
    }
}
