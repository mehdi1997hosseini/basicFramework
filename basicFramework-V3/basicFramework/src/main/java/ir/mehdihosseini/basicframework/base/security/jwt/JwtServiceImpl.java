package ir.mehdihosseini.basicframework.base.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import ir.mehdihosseini.basicframework.base.security.entity.dto.UserLoginAcceptResponseDto;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

    private String secretkey = "";

    public JwtServiceImpl() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGenerator.generateKey();
            secretkey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (Exception ignored) {
        }
    }

    @Override
    public UserLoginAcceptResponseDto generateToken(UserDetails userDetails) {
        Map<String, Object> claim = new HashMap<>();
        claim.put("roles", userDetails.getAuthorities());

        Date expiration = new Date(System.currentTimeMillis() + 1000 * 60 + 15); // 15 minute

        String token = Jwts.builder()
                .claims(claim)
                .subject(userDetails.getUsername())
                .issuedAt(new Date())
                .expiration(expiration)
                .signWith(getKey())
                .compact();

        return new UserLoginAcceptResponseDto(token, expiration.toString());
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }

    @Override
    public boolean validationToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private SecretKey getKey() {
        byte[] decode = Decoders.BASE64.decode(secretkey);
        return Keys.hmacShaKeyFor(decode);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsTResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsTResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

}
