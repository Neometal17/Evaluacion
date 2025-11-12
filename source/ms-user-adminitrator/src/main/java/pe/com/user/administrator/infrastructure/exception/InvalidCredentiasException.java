package pe.com.user.administrator.infrastructure.exception;

public class InvalidCredentiasException extends RuntimeException{
    public InvalidCredentiasException(String message){
        super(message);
    }
}
