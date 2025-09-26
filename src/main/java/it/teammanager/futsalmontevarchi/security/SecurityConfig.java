package it.teammanager.futsalmontevarchi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

	private static final List<String> ALLOWED_ORIGINS = List.of(
			"http://localhost:4200",
			"https://vallelpa.github.io"
	);


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.csrf(csrf -> csrf.disable()) // disabilitiamo CSRF per REST API
				.cors(Customizer.withDefaults())
				.authorizeHttpRequests(auth -> auth
													   .requestMatchers(
															   "/api/**",
															   "/swagger-ui/**",
															   "/v3/api-docs/**",
															   "/swagger-ui.html"
													   ).permitAll()
													   .anyRequest().authenticated()
				);

		return http.build();
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(ALLOWED_ORIGINS);
		configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowCredentials(true); // necessario se usi cookie o token di sessione

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}
}
