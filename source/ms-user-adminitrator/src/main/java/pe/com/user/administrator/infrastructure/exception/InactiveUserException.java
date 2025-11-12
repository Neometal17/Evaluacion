package pe.com.user.administrator.infrastructure.exception;

public class InactiveUserException extends RuntimeException{
    public InactiveUserException(String message){
        super(message);
    }
}
