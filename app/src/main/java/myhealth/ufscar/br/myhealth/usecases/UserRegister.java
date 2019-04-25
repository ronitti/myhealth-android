package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.ExistingEmailException;
import myhealth.ufscar.br.myhealth.repository.MyHealthClient;
import myhealth.ufscar.br.myhealth.repository.MyHealthService;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;
import retrofit2.Response;

public class UserRegister {
    public static boolean register(String email, String password) {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<UserLoadResponse> responseData = null;
        try {
            responseData = service.signUp(new User(email, password)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if( responseData.body() != null && !responseData.body().isSuccess()){
            throw new ExistingEmailException();
        }

        return true;
    }
}
