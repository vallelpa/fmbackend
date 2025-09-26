package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.CalciatoreDTO;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.mapper.CalciatoreMapper;
import it.teammanager.futsalmontevarchi.service.CalciatoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/squadra/{teamId}/calciatori")
@RequiredArgsConstructor
public class CalciatoreController {

	private final CalciatoreService service;

	@GetMapping
	public List<CalciatoreDTO> getCalciatoriByTeam(@PathVariable Long teamId) {
		return service.getByTeam(teamId).stream()
					   .sorted(Comparator.comparing(
							   Calciatore::getNumeroMaglia,
							   Comparator.nullsLast(Comparator.naturalOrder())
					   ))
					   .map(CalciatoreMapper::toDTO)
					   .toList();
	}



	@GetMapping("/{id}")
	public CalciatoreDTO getCalciatoreById(@PathVariable Long teamId, @PathVariable Long id) {
		return CalciatoreMapper.toDTO(service.findByIdAndTeamId(teamId, id));
	}

	@PostMapping
	public ResponseEntity<Void> addCalciatore(@PathVariable Long teamId, @RequestBody CalciatoreDTO dto) {
		Calciatore nuovo = CalciatoreMapper.fromDTO(dto);
		nuovo.setTeamId(teamId);
		service.add(nuovo);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateCalciatore(@PathVariable Long teamId, @PathVariable Long id, @RequestBody CalciatoreDTO dto) {
		Calciatore aggiornato = CalciatoreMapper.fromDTO(dto);
		aggiornato.setId(id);
		aggiornato.setTeamId(teamId);
		service.update(id, aggiornato);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCalciatore(@PathVariable Long teamId, @PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/export")
	public ResponseEntity<Void> exportJson() {
		service.exportJson();
		return ResponseEntity.ok().build();
	}
}

