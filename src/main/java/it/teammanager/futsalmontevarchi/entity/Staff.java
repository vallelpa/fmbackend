package it.teammanager.futsalmontevarchi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Staff {
	@Id
	private Long id;
	@Enumerated(EnumType.STRING)
	private TipoStaff tipo;
	private String cognomeNome;
	private String numeroDocumento;
	private Long teamId;
}
