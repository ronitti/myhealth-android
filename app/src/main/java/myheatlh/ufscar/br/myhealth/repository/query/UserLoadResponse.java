package myheatlh.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myheatlh.ufscar.br.myhealth.data.User;

public class UserLoadResponse extends ResponseData{

    @SerializedName("userData")
    private User userData;

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
