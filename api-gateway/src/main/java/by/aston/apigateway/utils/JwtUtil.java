package by.aston.apigateway.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class JwtUtil {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;
    private static final int MIN_KEY_LENGTH_BYTES = 32; // 256 бит

    @Value("${jwt.secret}")
    private String secretKeyRaw;

    @Value("${jwt.expiration}")
    private int tokenValidityMs;

    private SecretKey secretKey; // будет инициализирован при первом вызове

    private SecretKey getSigningKey() {
        if (secretKey == null) {
            byte[] keyBytes = secretKeyRaw.getBytes(StandardCharsets.UTF_8);

            if (keyBytes.length < MIN_KEY_LENGTH_BYTES) {
                throw new IllegalArgumentException("JWT secret must be at least 256 bits (32 bytes). Provided: " + keyBytes.length);
            }

            secretKey = Keys.hmacShaKeyFor(keyBytes);
        }
        return secretKey;
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public UUID getUserIdFromToken(String token) {
        return UUID.fromString(getClaims(token).getSubject());
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
