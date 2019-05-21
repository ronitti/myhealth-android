package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.ExistingEmailException;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserRegister {
    public static boolean register(String email, String password) {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<UserLoadResponse> response;
        try {
            RequestData requestData = new RequestData(new User(email, password));
            response = service.signUp(requestData).execute();
            Log.i("UserRegister", response.body().toString());
            if( response.body() != null && !response.body().isSuccess()){
                throw new ExistingEmailException();
            }
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
