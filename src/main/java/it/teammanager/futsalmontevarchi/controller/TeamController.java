package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.TeamDTO;
import it.teammanager.futsalmontevarchi.entity.Team;
import it.teammanager.futsalmontevarchi.mapper.TeamMapper;
import it.teammanager.futsalmontevarchi.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/squadra")
@RequiredArgsConstructor
public class TeamController {

	private final TeamService service;

	@GetMapping
	public List<TeamDTO> getAll() {
		return service.getAll().stream()
					   .map(TeamMapper::toDTO)
					   .toList();
	}

	@GetMapping("/{id}")
	public TeamDTO getById(@PathVariable Long id) {
		return TeamMapper.toDTO(service.getById(id));
	}

	@PostMapping
	public ResponseEntity<Void> add(@RequestBody TeamDTO dto) {
		service.add(TeamMapper.fromDTO(dto));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody TeamDTO dto) {
		Team aggiornato = TeamMapper.fromDTO(dto);
		aggiornato.setId(id);
		service.update(id, aggiornato);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/export")
	public ResponseEntity<Void> exportJson() {
		service.exportJson();
		return ResponseEntity.ok().build();
	}
}
