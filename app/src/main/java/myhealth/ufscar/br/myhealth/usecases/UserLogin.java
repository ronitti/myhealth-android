package myhealth.ufscar.br.myhealth.usecases;

import java.io.IOException;

import myhealth.ufscar.br.myhealth.data.User;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.repository.UserRepository;
import myhealth.ufscar.br.myhealth.repository.query.UserLoadResponse;

public class UserLogin {

    public static User execute(String email, String password) throws IOException {
        UserLoadResponse response = UserRepository.login(new User(email, password));
        if(!response.isSuccess()) {
            if (response.getMessage().equals("User not registered"))
                throw new NonRegisteredUserException();
            if (response.getMessage().equals("Wrong password"))
                throw new WrongPasswordException();
        }
        return response.getUserData();
    }
}
