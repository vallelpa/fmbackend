package it.teammanager.futsalmontevarchi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.repository.CalciatoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalciatoreService {

	private final CalciatoreRepository repo;
	private final JsonDbService jsonDb;
	private final String fileName = "calciatori.json";

	public List<Calciatore> getAll() {
		return repo.findAll();
	}

	public List<Calciatore> getByTeam(Long teamId) {
		return repo.findByTeamId(teamId);
	}

	public Calciatore findByIdAndTeamId(Long teamId, Long id) {
		return repo.findByIdAndTeamId(id, teamId)
					   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calciatore non trovato"));
	}

	public Calciatore getById(Long id) {
		return repo.findById(id)
					   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Calciatore non trovato"));
	}

	public void add(Calciatore nuovo) {
		repo.save(nuovo);
	}

	public void update(Long id, Calciatore aggiornato) {
		if (!repo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calciatore non trovato");
		}
		repo.save(aggiornato);
	}

	public void delete(Long id) {
		if (!repo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Calciatore non trovato");
		}
		repo.deleteById(id);
	}

	public void exportJson() {
		List<Calciatore> all = repo.findAll();
		jsonDb.exportList(fileName, all);
	}
}