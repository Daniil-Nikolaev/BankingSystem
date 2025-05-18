package by.aston.userservice.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
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

    public String generateToken(UUID userId) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidityMs))
                .signWith(getSigningKey(), SIGNATURE_ALGORITHM)
                .compact();
    }
}
