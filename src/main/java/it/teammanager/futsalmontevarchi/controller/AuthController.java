package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.LoginRequest;
import it.teammanager.futsalmontevarchi.dto.LoginResponse;
import it.teammanager.futsalmontevarchi.security.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

/*	private final AuthService authService;

	public AuthController(AuthService authService) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) {
		return authService.login(request);
	}*/
}