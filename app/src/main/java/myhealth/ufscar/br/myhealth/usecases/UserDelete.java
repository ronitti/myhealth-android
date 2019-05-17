package myhealth.ufscar.br.myhealth.usecases;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserDelete {

    public static User delete(String email, String password){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<UserLoadResponse> response;
        try {
            RequestData requestData = new RequestData(new User(email, password));
            response = service.deleteUser(requestData).execute();
            if(response.body() != null && !response.body().isSuccess()) {
                if (response.body().getMessage().equals("User not registered"))
                    throw new NonRegisteredUserException();
                if (response.body().getMessage().equals("Wrong password"))
                    throw new WrongPasswordException();
            }
            if(response.body() == null){
                throw new NoConnectionException();
            }
            Log.i("API", response.body().getMessage().toString());
            Log.i("API","CODE: " + response.body().getCode());
            assert response.body() != null;
            return response.body().getUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
