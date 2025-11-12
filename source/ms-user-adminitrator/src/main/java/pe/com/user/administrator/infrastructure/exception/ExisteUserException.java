package pe.com.user.administrator.infrastructure.exception;

public class ExisteUserException extends RuntimeException{
    public ExisteUserException(String message){
        super(message);
    }
}
