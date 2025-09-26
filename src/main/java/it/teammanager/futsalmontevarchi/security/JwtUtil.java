package it.teammanager.futsalmontevarchi.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

	private static final String SECRET_KEY = "my-secret-key-very-strong-for-jwt-demo-123456"; // cambia con uno più sicuro
	private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 ora

	private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

	public String generateToken(String username) {
		return Jwts.builder()
					   .setSubject(username)
					   .setIssuer("spring-boot-app")
					   .setIssuedAt(new Date())
					   .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					   .signWith(key, SignatureAlgorithm.HS256)
					   .compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder()
					   .setSigningKey(key)
					   .build()
					   .parseClaimsJws(token)
					   .getBody()
					   .getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException e) {
			return false;
		}
	}
}