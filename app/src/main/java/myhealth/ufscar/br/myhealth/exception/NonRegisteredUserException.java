package myhealth.ufscar.br.myhealth.exception;

public class NonRegisteredUserException extends RuntimeException {
    public NonRegisteredUserException(){
        super("Unable to authenticate: user not registered.");
    }
}
