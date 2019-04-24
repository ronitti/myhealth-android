package myheatlh.ufscar.br.myhealth.repository;

import java.util.List;

import myheatlh.ufscar.br.myhealth.data.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyHealthService {

    @GET("user/list")
    Call<List<User>> listUsers();

    @POST("user/signUp")
    Call<User> signUp(@Body User user);

    @POST("user/signIn")
    Call<User> signIn();
}
