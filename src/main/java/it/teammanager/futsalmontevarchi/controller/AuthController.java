package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.AuthRequest;
import it.teammanager.futsalmontevarchi.dto.AuthResponse;
import it.teammanager.futsalmontevarchi.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AuthRequest request) {
		var auth = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
		);
		var user = (UserDetails) auth.getPrincipal();
		var token = jwtUtils.generateToken(user);
		return ResponseEntity.ok(new AuthResponse(token));
	}
}
