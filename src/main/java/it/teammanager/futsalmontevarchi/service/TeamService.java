package it.teammanager.futsalmontevarchi.service;

import it.teammanager.futsalmontevarchi.entity.Team;
import it.teammanager.futsalmontevarchi.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

	private final TeamRepository repo;
	private final JsonDbService jsonDb;
	private final String fileName = "teams.json";

	public List<Team> getAll() {
		return repo.findAll();
	}

	public Team getById(Long id) {
		return repo.findById(id)
					   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Team non trovato"));
	}

	public void add(Team nuovo) {
		repo.save(nuovo);
	}

	public void update(Long id, Team aggiornato) {
		if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team non trovato");
		repo.save(aggiornato);
	}

	public void delete(Long id) {
		if (!repo.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Team non trovato");
		repo.deleteById(id);
	}

	public void exportJson() {
		jsonDb.exportList(fileName, repo.findAll());
	}
}
