package myhealth.ufscar.br.myhealth.repository;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NoConnectionException;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadRequest;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserRepository {

    public static UserLoadResponse login(User user) throws IOException {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<UserLoadResponse> response;

        response = service.signIn(user).execute();
        if(response.body() == null){
            throw new NoConnectionException();
        }
        return response.body();
    }


}
