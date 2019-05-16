package myhealth.ufscar.br.myhealth.exception;

public class NoConnectionException extends RuntimeException{
    public NoConnectionException(){
        super("Unable to connect to server: verify your internet connection.");
    }
}
