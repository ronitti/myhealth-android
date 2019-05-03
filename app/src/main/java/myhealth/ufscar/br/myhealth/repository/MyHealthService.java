package myhealth.ufscar.br.myhealth.repository;

import java.util.List;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyRegisterRequest;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyRegisterResponse;
import myhealth.ufscar.br.myhealth.repository.query.NCDRegisterRequest;
import myhealth.ufscar.br.myhealth.repository.query.NCDRegisterResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientRegisterResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterLoadResponse;
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
    Call<PatientRegisterResponse> createPatient(@Body Patient patient);

    @POST("patient/load")
    Call<PatientRegisterResponse> loadPatient(@Body PatientLoadRequest request);

    @POST("patient/loadByUser")
    Call<PatientRegisterResponse> loadPatientByUser(@Body PatientLoadRequest request);

    @POST("monitor/create")
    Call<NCDRegisterResponse> createMonitor(@Body NCDRegisterRequest request);

    @POST("frequency/create")
    Call<FrequencyRegisterResponse> createFrequency(@Body FrequencyRegisterRequest request);

    @POST("register/create")
    Call<RegisterCreateResponse> createRegister(@Body RegisterCreateRequest request);

    @POST("register/list")
    Call<List<RegisterLoadResponse>> listRegister(@Body RegisterListRequest request);
}
