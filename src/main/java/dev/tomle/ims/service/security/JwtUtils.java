package dev.tomle.ims.service.security;

import java.io.Serial;
import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
	public static final long TOKEN_VALIDITY = 10 * 60 * 60;
	@Value("${jwt.token.secret}")
	private String jwtSecret; 

	public String generateJwtToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		SecretKey secretKey = generalKey();
		return Jwts.builder().claims(claims).subject(username)
	         .issuedAt(new Date(System.currentTimeMillis())) 
	         .expiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
	         .signWith(secretKey, Jwts.SIG.HS256).compact();
	} 

	public Boolean validateJwtToken(String token, String username) {
		String usernameFromToken = getUsernameFromToken(token);
		SecretKey secretKey = generalKey();
		Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
		Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
		return (usernameFromToken.equals(username) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		SecretKey secretKey = generalKey();
		final Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();
		return claims.getSubject();
	} 
	
	public Date getTokenExpiration(String token) {
		Claims claims = Jwts.parser().setSigningKey(generalKey()).build().parseClaimsJws(token).getPayload();
		return claims.getExpiration();
	}
	
	public SecretKey generalKey() {
		byte[] encodeKey = Base64.getDecoder().decode(jwtSecret);
		return Keys.hmacShaKeyFor(encodeKey);
	}
}
