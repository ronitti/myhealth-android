package myheatlh.ufscar.br.myhealth.exception;

public class ExistingSusNumberException extends RuntimeException {
    public ExistingSusNumberException(){
        super("Unable to register patient: sus number already registered.");
    }
}
