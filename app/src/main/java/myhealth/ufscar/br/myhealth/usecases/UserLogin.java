package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserLogin {

    public static User login(String email, String password){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<UserLoadResponse> response;
        try {
            response = service.signIn(new User(email, password)).execute();
            if(response.body() != null && !response.body().isSuccess()) {
                if (response.body().getMessage().equals("User not registered"))
                    throw new NonRegisteredUserException();
                if (response.body().getMessage().equals("Wrong password"))
                    throw new WrongPasswordException();
            }
            assert response.body() != null;
            return response.body().getUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
