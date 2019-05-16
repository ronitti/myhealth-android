package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.User;

public class UserLoadRequest extends ResponseData{

    @SerializedName("user_data")
    private User userData;

    public UserLoadRequest(User userData) {
        this.userData = userData;
    }

    public User getUserData() {
        return userData;
    }

    public void setUserData(User userData) {
        this.userData = userData;
    }
}
