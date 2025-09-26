package it.teammanager.futsalmontevarchi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Calciatore {
	@Id
	private Long id;
	private Integer numeroMaglia;
	private String cognomeNome;
	private Integer cartellini;
	private LocalDate scadenzaVisitaMedica;
	private LocalDate dataNascita;
	private String matricolaFIGC;
	private String tipoDocumento;
	private String numeroDocumento;
	private boolean capitano;
	private boolean viceCapitano;
	private Long teamId;
}
