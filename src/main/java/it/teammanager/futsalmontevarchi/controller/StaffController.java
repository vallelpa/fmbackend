package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.StaffDTO;
import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.mapper.StaffMapper;
import it.teammanager.futsalmontevarchi.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/squadra/{teamId}/staff")
@RequiredArgsConstructor
public class StaffController {

	private final StaffService service;

	@GetMapping
	public List<StaffDTO> getByTeam(@PathVariable Long teamId) {
		return service.getByTeam(teamId).stream()
					   .map(StaffMapper::toDTO)
					   .toList();
	}

	@GetMapping("/{id}")
	public StaffDTO getById(@PathVariable Long teamId, @PathVariable Long id) {
		return StaffMapper.toDTO(service.findByIdAndTeamId(teamId, id));
	}

	@PostMapping
	public ResponseEntity<Void> add(@PathVariable Long teamId, @RequestBody StaffDTO dto) {
		Staff nuovo = StaffMapper.fromDTO(dto);
		nuovo.setTeamId(teamId);
		service.add(nuovo);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Long teamId, @PathVariable Long id, @RequestBody StaffDTO dto) {
		Staff aggiornato = StaffMapper.fromDTO(dto);
		aggiornato.setId(id);
		aggiornato.setTeamId(teamId);
		service.update(id, aggiornato);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long teamId, @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/export")
	public ResponseEntity<Void> exportJson() {
		service.exportJson();
		return ResponseEntity.ok().build();
	}
}
