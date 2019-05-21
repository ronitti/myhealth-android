package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import retrofit2.Response;

public class PatientLoad {

    public static Patient load(User user) throws NonRegisteredUserException {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientLoadResponse> response;
        try {
            RequestData requestData = new RequestData(new PatientLoadRequest(user));
            response = service.loadPatientByUser(requestData).execute();
            if(response.body() != null && !response.body().isSuccess()){
                throw new NonRegisteredUserException();
            }
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
            return response.body().getPatientData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Patient load(String susNumber) throws NonRegisteredUserException{
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientLoadResponse> response;
        try {
            RequestData requestData = new RequestData(new PatientLoadRequest(susNumber));
            response = service.loadPatient(requestData).execute();
            if(response.body() != null && !response.body().isSuccess()){
                throw new NonRegisteredUserException();
            }
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
            return response.body().getPatientData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
