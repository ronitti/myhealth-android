package myhealth.ufscar.br.myhealth.data;

import com.google.gson.annotations.SerializedName;

public class User {

    private int id;

    @SerializedName("password")
    private String password;

    @SerializedName("email")
    private String email;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(){
        
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

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


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
