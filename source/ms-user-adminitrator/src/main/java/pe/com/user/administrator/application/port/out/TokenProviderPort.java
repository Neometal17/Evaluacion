package pe.com.user.administrator.application.port.out;

public interface TokenProviderPort {
    String generateToken(String userName);
    boolean validateToken(String token);
    String getUserNameToken(String token);
}
