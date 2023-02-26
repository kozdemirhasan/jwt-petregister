package de.kozdemir.petregister.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.time.Period;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    // Private Schlüssel -  JWT verlangt min 256 bit
    private static final String SECRET = "3777217A25432A462D4A614E635266556A586E3272357538782F413F4428472B";

    public boolean isValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        boolean expired = extractExpiration(token).before(Date.from(Instant.now())); //Ablaufdatum prüfen

        return username.equals(userDetails.getUsername()) && !expired;
    }

    public String extractUsername(String token) {
//        return extractClaim(token, Claims::getSubject);
        return extractClaims(token).getSubject();
    }

    public Date extractExpiration(String token) {
//        return extractClaim(token, Claims::getExpiration);
        return extractClaims(token).getExpiration();
    }

    /* Alternative 
    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        return resolver.apply(extractClaims(token));
    }
    */

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(Date.from(now)) //Erscheinungsdatum
                .setExpiration(Date.from(now.plus(Period.ofDays(1)))) //Ablaufdatum
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact(); // Zusammenbauen
    }

    private Key getSecretKey() {
        byte[] bytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(bytes);

    }

}
