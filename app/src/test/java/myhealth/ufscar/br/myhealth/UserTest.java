package myhealth.ufscar.br.myhealth;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import myhealth.ufscar.br.myhealth.exception.ExistingEmailException;
import myhealth.ufscar.br.myhealth.exception.NonRegisteredUserException;
import myhealth.ufscar.br.myhealth.exception.WrongPasswordException;
import myhealth.ufscar.br.myhealth.usecases.UserDelete;
import myhealth.ufscar.br.myhealth.usecases.UserLogin;
import myhealth.ufscar.br.myhealth.usecases.UserRegister;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest {
    /**
     * RF1 - Cadastro de Usuário
     */
    @Test(expected = ExistingEmailException.class)
    public void t01_shouldNotRegisterAnExistingEmail(){
        System.out.println("Running test: should not register an existing email");
        UserRegister.register("teste@teste.com", "teste");
    }


    @Test
    public void t02_shouldRegisterNewUserProperly(){
        System.out.println("Running test: should register user properly");
        assertTrue(UserRegister.register("novo@novo.com", "novo"));
    }

    /**
     * RF2 - Exclusão de Usuário
     */
    @Test (expected = WrongPasswordException.class)
    public void t03_shouldNotDeleteUserWithIncorrectPassword(){
        System.out.println("Running test: should not delete user with incorrect password");
        assertNull(UserDelete.delete("novo@novo.com", "teste"));
    }
    @Test
    public void t04_shouldDeleteUserProperly(){
        System.out.println("Running test: should delete user properyly");
        assertNotNull(UserDelete.delete("novo@novo.com", "novo"));
    }

    /**
     * RF3 - Autenticação de Usuário
     */
    @Test (expected = NonRegisteredUserException.class)
    public void t05_shouldNotAuthenticateNonRegisteredUser(){
        System.out.println("Running test: should not authenticate non registered user");
        assertNull(UserLogin.login("teste@novo.com", "teste"));
    }
    @Test (expected = WrongPasswordException.class)
    public void t06_shouldNotAuthenticateUserWithWrongPassword(){
        System.out.println("Running test: should not authenticate user with incorrect password");
        assertNull(UserLogin.login("teste@teste.com", "novo"));
    }
    @Test
    public void t07_shouldAuthenticateUserProperly(){
        System.out.println("Running test: should authenticate user properly");
        assertNotNull(UserLogin.login("teste@teste.com", "teste"));
    }
}