package myheatlh.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.User;
import myheatlh.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myheatlh.ufscar.br.myhealth.exception.WrongPasswordException;
import myheatlh.ufscar.br.myhealth.repository.MyHealthClient;
import myheatlh.ufscar.br.myhealth.repository.MyHealthService;
import myheatlh.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserDelete {

    public static User delete(String email, String password){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        Response<UserLoadResponse> response;
        try {
            response = service.deleteUser(new User(email, password)).execute();
            if(response.body() != null && !response.body().isSuccess()) {
                if (response.body().getMessage().equals("User not registered"))
                    throw new NonRegisteredUserException();
                if (response.body().getMessage().equals("Wrong password"))
                    throw new WrongPasswordException();
            }
            return response.body().getUserData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
