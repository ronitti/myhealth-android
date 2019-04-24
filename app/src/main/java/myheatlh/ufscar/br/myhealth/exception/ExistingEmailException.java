package myheatlh.ufscar.br.myhealth.exception;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException(){
        super("Unable to register user: email already registered.");
    }
}
