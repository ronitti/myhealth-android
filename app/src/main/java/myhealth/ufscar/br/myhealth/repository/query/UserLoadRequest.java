package myhealth.ufscar.br.myhealth.repository.query;

import com.google.gson.annotations.SerializedName;

import myhealth.ufscar.br.myhealth.data.User;

public class UserLoadRequest extends ResponseData{

    @SerializedName("request_data")
    private User user;

    public UserLoadRequest(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserLoadRequest{" +
                "user=" + user +
                '}';
    }
}
