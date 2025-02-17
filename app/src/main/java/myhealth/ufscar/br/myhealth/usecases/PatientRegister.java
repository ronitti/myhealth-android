package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.exception.ExistingSusNumberException;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.PatientCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import retrofit2.Response;

public class PatientRegister {
    public static boolean register(Patient patient){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<PatientCreateResponse> response;
        try {
            RequestData requestData = new RequestData(patient);
            Log.i("PatientRegister", "request: " + requestData.getRequestData().toString());
            response = service.createPatient(requestData).execute();
            Log.i("PatientRegister", response.body().toString());
            if(response.body() != null && !response.body().isSuccess()){
                Log.i("API", response.body().getMessage().toString());
                Log.i("API","CODE: " + response.body().getCode());
                throw new ExistingSusNumberException();
            }
            if(response.body() == null){
                throw new NoConnectionException();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
