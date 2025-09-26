package it.teammanager.futsalmontevarchi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CalciatoreDTO implements Serializable {
	private static final long serialVersionUID = -4408207677290292231L;
	private Long id;
	private Integer numeroMaglia;
	private String nome;
	private Integer cartellini;
	private LocalDate scadenzaVisitaMedica;
	private LocalDate dataNascita;
	private String matricolaFigc;
	private String tipoDocumento;
	private String numeroDocumento;
}
