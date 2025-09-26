package it.teammanager.futsalmontevarchi.repository;

import it.teammanager.futsalmontevarchi.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}