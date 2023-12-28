package dev.tomle.ims.application.security.impl;

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
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final long TOKEN_VALIDITY = 10 * 60 * 60;
	@Value("${jwt.token.secret}")
	private String jwtSecret; 

	public String generateJwtToken(String username) {
		Map<String, Object> claims = new HashMap<>();
		SecretKey secretKey = generalKey();
		return Jwts.builder().setClaims(claims).setSubject(username) 
	         .setIssuedAt(new Date(System.currentTimeMillis())) 
	         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000)) 
	         .signWith(secretKey, SignatureAlgorithm.HS512).compact();
	} 

	public Boolean validateJwtToken(String token, String username) {
		String usernameFromToken = getUsernameFromToken(token);
		SecretKey secretKey = generalKey();
		Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		Boolean isTokenExpired = claims.getExpiration().before(new Date()); 
		return (usernameFromToken.equals(username) && !isTokenExpired);
	}

	public String getUsernameFromToken(String token) {
		SecretKey secretKey = generalKey();
		final Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
		return claims.getSubject();
	} 
	
	public Date getTokenExpiration(String token) {
		Claims claims = Jwts.parserBuilder().setSigningKey(generalKey()).build().parseClaimsJws(token).getBody();
		return claims.getExpiration();
	}
	
	public SecretKey generalKey() {
		byte[] encodeKey = Base64.getDecoder().decode(jwtSecret);
		return Keys.hmacShaKeyFor(encodeKey);
	}
}
