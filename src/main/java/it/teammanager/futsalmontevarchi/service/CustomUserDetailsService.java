package it.teammanager.futsalmontevarchi.service;

import it.teammanager.futsalmontevarchi.entity.Utenti;
import it.teammanager.futsalmontevarchi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Utenti utenti = userRepository.findByUsername(username)
							.orElseThrow(() -> new UsernameNotFoundException("Utente non trovato: " + username));

		List<GrantedAuthority> authorities = Arrays.stream(utenti.getRole().split(","))
													 .map(String::trim)
													 .map(SimpleGrantedAuthority::new)
													 .collect(Collectors.toList());

		return new org.springframework.security.core.userdetails.User(
				utenti.getUsername(),
				utenti.getPassword(),
				authorities
		);
	}

}
