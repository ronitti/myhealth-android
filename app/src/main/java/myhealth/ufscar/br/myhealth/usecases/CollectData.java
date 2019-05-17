package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.collect.Register;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateRequest;
import myhealth.ufscar.br.myhealth.repository.query.RegisterCreateResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import retrofit2.Response;

public class CollectData {
    public static boolean execute(Patient patient, Register register){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<RegisterCreateResponse> response;
        try {
            RequestData requestData = new RequestData(new RegisterCreateRequest(patient, register));
            response = service.createRegister(requestData).execute();
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
            return response.body().isSuccess();
        } catch (IOException | NullPointerException e) {
            //e.printStackTrace();
        }
        return false;
    }
}
