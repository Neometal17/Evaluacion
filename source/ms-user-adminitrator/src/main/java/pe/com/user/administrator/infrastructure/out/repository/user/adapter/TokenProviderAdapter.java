package pe.com.user.administrator.infrastructure.out.repository.user.adapter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import pe.com.user.administrator.application.port.out.TokenProviderPort;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TokenProviderAdapter implements TokenProviderPort {

    private static final String SECRET = "miClaveSuperSeguraParaJWTDe32CaracteresOMas1234567";
    private static final SecretKey KEY = Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    private static final long EXPIRATION = 450000;

    @Override
    public String generateToken(String userName) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + EXPIRATION);
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(KEY)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            System.out.println("Token expirado");
            return false;

        } catch (io.jsonwebtoken.SignatureException e) {
            System.out.println("Firma del token inválida");
            return false;

        } catch (io.jsonwebtoken.MalformedJwtException e) {
            System.out.println("Token malformado");
            return false;

        } catch (io.jsonwebtoken.UnsupportedJwtException e) {
            System.out.println("Token no soportado");
            return false;

        } catch (IllegalArgumentException e) {
            System.out.println("Token vacío o nulo");
            return false;
        }
    }

    @Override
    public String getUserNameToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
