package it.teammanager.futsalmontevarchi.dto;

import it.teammanager.futsalmontevarchi.entity.TipoStaff;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteGaraStaffInputDTO implements Serializable {
	private static final long serialVersionUID = 476504006451951755L;
	private Long id;
	private TipoStaff tipo;
}
