package it.teammanager.futsalmontevarchi.repository;

import it.teammanager.futsalmontevarchi.entity.Calciatore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CalciatoreRepository extends JpaRepository<Calciatore, Long> {
	List<Calciatore> findByTeamId(Long teamId);
	Optional<Calciatore> findByIdAndTeamId(Long id, Long teamId);
}