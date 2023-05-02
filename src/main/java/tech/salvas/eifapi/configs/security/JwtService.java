package tech.salvas.eifapi.configs.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {
    @Value("${eif-api.jwt.secret}")
    private String secretKey;

    @Value("${eif-api.jwt.token-expiration}")
    private String tokenExpiration;

    public String extractSubjectFromToken(String jwt) {
        return extractClaim(jwt, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String generateToken(AppUserDetails appUserDetails) {
        return generateToken(new HashMap<>(), appUserDetails);
    }

    public String generateToken(Map<String, Object> extractClaims, AppUserDetails appUserDetails) {
        Calendar calendar = Calendar.getInstance();
        Date issued = calendar.getTime();
        calendar.add(Calendar.SECOND, Integer.parseInt(tokenExpiration));
        Date exp = calendar.getTime();

        return Jwts
                .builder()
                .setClaims(extractClaims)
                .setSubject(appUserDetails.getUsername())
                .setIssuedAt(issued)
                .setExpiration(exp)
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, AppUserDetails appUserDetails) {
        final String subject = extractSubjectFromToken(token);
        return (subject.equals(appUserDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(getSignInKey()).build().parseClaimsJws(token).getBody();
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
