package it.teammanager.futsalmontevarchi.repository;

import it.teammanager.futsalmontevarchi.entity.Utenti;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utenti, Long> {
	Optional<Utenti> findByUsername(String username);
}
