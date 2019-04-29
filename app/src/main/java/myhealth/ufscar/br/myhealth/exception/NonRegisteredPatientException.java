package myhealth.ufscar.br.myhealth.exception;

public class NonRegisteredPatientException extends RuntimeException{
    public NonRegisteredPatientException(){
        super("Unable to authenticate: user not registered.");
    }
}
