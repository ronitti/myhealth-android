package myhealth.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

public class AccessToken {
    @SerializedName("access_token")
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
