package myhealth.ufscar.br.myhealth.repository;

import java.util.List;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyHealthService {

    @POST("user/signUp")
    Call<UserLoadResponse> signUp(@Body User user);

    @POST("user/signIn")
    Call<UserLoadResponse> signIn(@Body User user);

    @POST("user/delete")
    Call<UserLoadResponse> deleteUser(@Body User user);

    @POST("patient/create")
    Call<UserLoadResponse> createPatient(@Body Patient patient);

    @POST("patient/load")
    Call<PatientLoadResponse> loadPatient(@Body PatientLoadRequest request);
}
