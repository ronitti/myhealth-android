package myhealth.ufscar.br.myhealth.repository;

import java.util.List;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.FrequencyCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.MonitoringCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RegisterListRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MyHealthService {

    @POST("user/signUp")
    Call<UserLoadResponse> signUp(@Body User request);

    @POST("user/signIn")
    Call<UserLoadResponse> signIn(@Body User request);

    @POST("user/delete")
    Call<UserLoadResponse> deleteUser(@Body User user);


    @POST("patient/create")
    Call<PatientCreateResponse> createPatient(@Body Patient request);

    @POST("patient/load")
    Call<PatientLoadResponse> loadPatient(@Body PatientLoadRequest request);

    @POST("patient/loadByUser")
    Call<PatientLoadResponse> loadPatientByUser(@Body PatientLoadRequest request);


    @POST("monitor/create")
    Call<MonitoringCreateResponse> createMonitor(@Body MonitoringCreateRequest request);


    @POST("frequency/create")
    Call<FrequencyCreateResponse> createFrequency(@Body FrequencyCreateRequest request);


    @POST("register/create")
    Call<RegisterCreateResponse> createRegister(@Body RegisterCreateRequest request);

    @POST("register/list")
    Call<List<RegisterLoadResponse>> listRegister(@Body RegisterListRequest request);
}
