package myheatlh.ufscar.br.myhealth.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(){
        super("Unable to authenticate user: password is incorrect.");
    }
}
