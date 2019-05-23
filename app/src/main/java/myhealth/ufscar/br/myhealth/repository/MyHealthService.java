package myhealth.ufscar.br.myhealth.repository;


import myhealth.ufscar.br.myhealth.repository.query.AccessCodeResponse;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyHealthService {

    @POST("user/signUp")
    Call<UserLoadResponse> signUp(@Body RequestData request);

    @POST("user/signIn")
    Call<UserLoadResponse> signIn(@Body RequestData request);

    @POST("user/delete")
    Call<UserLoadResponse> deleteUser(@Body RequestData request);


    @POST("patient/create")
    Call<PatientCreateResponse> createPatient(@Body RequestData request);

    @POST("patient/load")
    Call<PatientLoadResponse> loadPatient(@Body RequestData request);

    @POST("patient/loadByUser")
    Call<PatientLoadResponse> loadPatientByUser(@Body RequestData request);


    @POST("monitor/create")
    Call<MonitoringCreateResponse> createMonitor(@Body RequestData request);


    @POST("frequency/create")
    Call<FrequencyCreateResponse> createFrequency(@Body RequestData request);


    @POST("register/create")
    Call<RegisterCreateResponse> createRegister(@Body RequestData request);

    @POST("register/list")
    Call<RegisterListResponse> listRegister(@Body RequestData request);

    @POST("patient/token/load")
    Call<AccessCodeResponse> getAccessCode(@Body RequestData request);
}
