package it.teammanager.futsalmontevarchi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

	// Chiave fissa sicura: almeno 32 caratteri (256 bit)
	private final String jwtSecret = "vP9sK3x!7dLq@eZrT8mWbC4nYfGhJkLp"; // usa una stringa casuale e lunga in produzione
	private final Duration jwtExpiration = Duration.ofHours(2);

	private final ObjectMapper objectMapper = new ObjectMapper();

	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("roles", userDetails.getAuthorities().stream()
									.map(GrantedAuthority::getAuthority)
									.collect(Collectors.toList()));

		return Jwts.builder()
					   .setClaims(claims)
					   .setSubject(userDetails.getUsername())
					   .setIssuedAt(new Date())
					   .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration.toMillis()))
					   .signWith(getSigningKey(), SignatureAlgorithm.HS256)
					   .compact();
	}

	public String extractUsername(String token) {
		return extractAllClaims(token).getSubject();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		return extractAllClaims(token).getExpiration().before(new Date());
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder()
					   .setSigningKey(getSigningKey())
					   .build()
					   .parseClaimsJws(token)
					   .getBody();
	}

	private Key getSigningKey() {
		// Converte la chiave fissa in byte e la valida per HMAC-SHA256
		byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}

