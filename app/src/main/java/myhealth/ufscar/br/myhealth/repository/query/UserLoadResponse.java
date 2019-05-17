package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.User;

public class UserLoadResponse extends ResponseData{

    @SerializedName("user_data")

    private User userData;

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
