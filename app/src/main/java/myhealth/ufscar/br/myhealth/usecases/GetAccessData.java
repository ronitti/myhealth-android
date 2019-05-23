package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.AccessToken;
import myhealth.ufscar.br.myhealth.data.Patient;
import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.ExistingEmailException;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.AccessCodeResponse;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.PatientLoadResponse;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class GetAccessData {
    public static AccessToken execute(String susNumber) throws NonRegisteredUserException {

        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<AccessCodeResponse> response;
        try {
            RequestData requestData = new RequestData(new PatientLoadRequest(susNumber));
            response = service.getAccessCode(requestData).execute();
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());

            return response.body().getTokenData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
