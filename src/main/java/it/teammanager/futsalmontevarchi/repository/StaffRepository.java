package it.teammanager.futsalmontevarchi.repository;

import it.teammanager.futsalmontevarchi.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	List<Staff> findByTeamId(Long teamId);

	Optional<Staff> findByIdAndTeamId(Long id, Long teamId);
}