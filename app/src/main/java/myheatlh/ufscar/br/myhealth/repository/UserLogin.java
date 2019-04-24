package myheatlh.ufscar.br.myhealth.repository;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.User;
import myheatlh.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myheatlh.ufscar.br.myhealth.exception.WrongPasswordException;
import retrofit2.Response;

public class UserLogin {



    public static boolean login(String email, String password){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();

        Response<ResponseData> response = null;
        try {
            response = service.signIn(new User(email, password)).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.body() == null) {
            return false;
        } else  {
            if(!response.body().isSuccess()) {
                if (response.body().getMessage().equals("User not registered"))
                throw new NonRegisteredUserException();
                if (response.body().getMessage().equals("Wrong password"))
                    throw new WrongPasswordException();
            }
        }

        return true;
    }
}
