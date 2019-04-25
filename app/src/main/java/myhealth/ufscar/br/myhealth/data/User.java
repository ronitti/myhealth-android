package myhealth.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
