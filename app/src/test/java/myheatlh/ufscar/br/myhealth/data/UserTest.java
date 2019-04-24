package myheatlh.ufscar.br.myhealth.data;

import org.junit.Test;

import myheatlh.ufscar.br.myhealth.exception.ExistingEmailException;
import myheatlh.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myheatlh.ufscar.br.myhealth.exception.WrongPasswordException;
import myheatlh.ufscar.br.myhealth.repository.UserDelete;
import myheatlh.ufscar.br.myhealth.repository.UserLogin;
import myheatlh.ufscar.br.myhealth.repository.UserRegister;

import static org.junit.Assert.*;

public class UserTest {
    /**
     * RF1 - Cadastro de Usuário
     */
    @Test(expected = ExistingEmailException.class)
    public void shouldNotRegisterAnExistingEmail(){
        UserRegister.register("teste@teste.com", "teste");
    }


    @Test
    public void shouldRegisterNewUserProperly(){
        assertTrue(UserRegister.register("novo@novo.com", "novo"));
    }

    /**
     * RF2 - Exclusão de Usuário
     */
    @Test (expected = WrongPasswordException.class)
    public void shouldNotDeleteUserWithIncorrectPassword(){
        assertTrue(UserDelete.delete("novo@novo.com", "teste"));
    }
    @Test
    public void shouldDeleteUserProperly(){
        assertTrue(UserDelete.delete("novo@novo.com", "teste"));
    }

    /**
     * RF3 - Autenticação de Usuário
     */
    @Test (expected = NonRegisteredUserException.class)
    public void shouldNotAuthenticateNonRegisteredUser(){
        assertTrue(UserLogin.login("1234", "senha"));
    }
    @Test (expected = WrongPasswordException.class)
    public void shouldNotAuthenticateUserWithWrongPassword(){
        assertTrue(UserLogin.login("5678", "senha"));
    }
    @Test
    public void shouldAuthenticateUserProperly(){
        assertTrue(UserLogin.login("5678", "password"));
    }
}