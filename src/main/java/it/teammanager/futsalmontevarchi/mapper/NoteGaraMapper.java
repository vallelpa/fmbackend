package it.teammanager.futsalmontevarchi.mapper;

import it.teammanager.futsalmontevarchi.dto.NoteGaraInputDTO;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.entity.NoteGara;
import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.service.CalciatoreService;
import it.teammanager.futsalmontevarchi.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class NoteGaraMapper {

	private final CalciatoreService calciatoreService;
	private final StaffService staffService;

	public NoteGara toEventoSportivo(NoteGaraInputDTO dto) {
		return NoteGara.builder()
					   .dataEvento(dto.getDataEvento())
					   .squadraCasa(dto.getSquadraCasa())
					   .squadraTrasferta(dto.getSquadraTrasferta())
					   .campionato(dto.getCampionato())
					   .calciatori(mapCalciatori(dto))
					   .staff(mapStaff(dto))
					   .build();
	}

	private List<Calciatore> mapCalciatori(NoteGaraInputDTO dto) {
		return dto.getCalciatoriIds().stream()
					   .map(id -> {
						   Calciatore c = calciatoreService.getById(id);
						   // Imposta capitano e vice
						   c.setCapitano(id.equals(dto.getCapitanoId()));
						   c.setViceCapitano(id.equals(dto.getViceCapitanoId()));
						   return c;
					   })
					   .collect(Collectors.toList());
	}

	private List<Staff> mapStaff(NoteGaraInputDTO dto) {
		return dto.getStaff().stream()
					   .map(s -> {
						   // Recupera lo staff dal DB usando lo staffService
						   Staff staff = staffService.getById(s.getId());
						   staff.setTipo(s.getTipo());
						   return staff;
					   })
					   .collect(Collectors.toList());
	}
}
