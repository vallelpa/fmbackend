package it.teammanager.futsalmontevarchi.service;

import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.repository.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StaffService {

	private final StaffRepository repo;
	private final JsonDbService jsonDb;
	private final String fileName = "staff.json";

	public List<Staff> getAll() {
		return repo.findAll();
	}

	public List<Staff> getByTeam(Long teamId) {
		return repo.findByTeamId(teamId);
	}

	public Staff findByIdAndTeamId(Long teamId, Long id) {
		return repo.findByIdAndTeamId(id, teamId)
					   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff non trovato"));
	}

	public Staff getById(Long id) {
		return repo.findById(id)
					   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff non trovato"));
	}

	public void add(Staff nuovo) {
		repo.save(nuovo);
	}

	public void update(Long id, Staff aggiornato) {
		if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff non trovato");
		repo.save(aggiornato);
	}

	public void delete(Long id) {
		if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Staff non trovato");
		repo.deleteById(id);
	}

	public void exportJson() {
		jsonDb.exportList(fileName, repo.findAll());
	}
}
