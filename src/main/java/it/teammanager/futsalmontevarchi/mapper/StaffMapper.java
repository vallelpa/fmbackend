package it.teammanager.futsalmontevarchi.mapper;

import it.teammanager.futsalmontevarchi.dto.StaffDTO;
import it.teammanager.futsalmontevarchi.entity.Staff;

public class StaffMapper {

	public static StaffDTO toDTO(Staff staff) {
		if (staff == null) return null;

		String tipoDescrizione = staff.getTipo() != null ? staff.getTipo().getDescrizione() : null;

		return StaffDTO.builder()
					   .id(staff.getId())
					   .tipo(tipoDescrizione)
					   .nome(staff.getCognomeNome())
					   .numeroDocumento(staff.getNumeroDocumento())
					   .build();
	}

	public static Staff fromDTO(StaffDTO dto) {
		if (dto == null) return null;

		return Staff.builder()
					   .id(dto.getId())
					   .cognomeNome(dto.getNome())
					   .numeroDocumento(dto.getNumeroDocumento())
					   .build();
	}
}



