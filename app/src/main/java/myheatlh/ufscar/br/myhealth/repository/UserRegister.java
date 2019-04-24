package myheatlh.ufscar.br.myhealth.repository;

import java.io.IOException;

import myheatlh.ufscar.br.myhealth.data.User;
import retrofit2.Response;

public class UserRegister {
    public static boolean register(String email, String password){
        MyHealthService service = MyHealthClient.getMyHealthServiceInstance();
        try {
            Response<User> userRegister = service.signUp(new User(email, password)).execute();
            /* TODO Verificar existencia de email*/
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
