package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.AccessToken;

public class AccessCodeResponse extends ResponseData{

    @SerializedName("token_data")
    private AccessToken tokenData;

    public AccessToken getTokenData() {
        return tokenData;
    }

    public void setTokenData(AccessToken tokenData) {
        this.tokenData = tokenData;
    }
}
