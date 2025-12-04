package pe.com.user.administrator.infrastructure.exception;

public class ExpiredTokenException extends RuntimeException{
    public ExpiredTokenException(String message){
        super(message);
    }
}
