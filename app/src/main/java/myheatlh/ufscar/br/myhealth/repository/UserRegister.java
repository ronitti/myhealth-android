package myheatlh.ufscar.br.myhealth.repository;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.User;
import myheatlh.ufscar.br.myhealth.exception.ExistingEmailException;
import okhttp3.Request;
import retrofit2.Response;

public class UserRegister {
    public static boolean register(String email, String password) {
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<ResponseData> responseData = null;
        try {
            responseData = service.signUp(new User(email, password)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if( responseData.body() != null && !responseData.body().isSuccess()){
            throw new ExistingEmailException();
        }

        return false;
    }
}
