package myhealth.ufscar.br.myhealth.repository;

import android.util.Log;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.query.RequestData;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserRepository {

    public static UserLoadResponse login(User user) throws IOException {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<UserLoadResponse> response;

        RequestData requestData = new RequestData(user);
        response = service.signIn(requestData).execute();
        if(response.body() == null){
            throw new NoConnectionException();
        }
        Log.i("API", response.body().getMessage().toString());
        Log.i("API","CODE: " + response.body().getCode());
        return response.body();
    }


}
