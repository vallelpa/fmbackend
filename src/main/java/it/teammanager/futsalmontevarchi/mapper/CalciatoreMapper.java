package it.teammanager.futsalmontevarchi.mapper;

import it.teammanager.futsalmontevarchi.dto.CalciatoreDTO;
import it.teammanager.futsalmontevarchi.entity.Calciatore;

public class CalciatoreMapper {

	public static CalciatoreDTO toDTO(Calciatore calciatore) {
		if (calciatore == null) return null;

		return CalciatoreDTO.builder()
					   .id(calciatore.getId())
					   .numeroMaglia(calciatore.getNumeroMaglia())
					   .nome(calciatore.getCognomeNome()) // Mappato su "nome"
					   .cartellini(calciatore.getCartellini())
					   .scadenzaVisitaMedica(calciatore.getScadenzaVisitaMedica())
					   .dataNascita(calciatore.getDataNascita())
					   .matricolaFigc(calciatore.getMatricolaFIGC())
					   .tipoDocumento(calciatore.getTipoDocumento())
					   .numeroDocumento(calciatore.getNumeroDocumento())
					   .build();
	}


	public static Calciatore fromDTO(CalciatoreDTO dto) {
		if (dto == null) return null;

		return Calciatore.builder()
					   .id(dto.getId())
					   .numeroMaglia(dto.getNumeroMaglia())
					   .cognomeNome(dto.getNome()) // Inverso di "nome" → "cognomeNome"
					   .cartellini(dto.getCartellini())
					   .scadenzaVisitaMedica(dto.getScadenzaVisitaMedica())
					   .dataNascita(dto.getDataNascita())
					   .matricolaFIGC(dto.getMatricolaFigc())
					   .tipoDocumento(dto.getTipoDocumento())
					   .numeroDocumento(dto.getNumeroDocumento())
					   .build();
	}
}