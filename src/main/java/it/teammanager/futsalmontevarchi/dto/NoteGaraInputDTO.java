package it.teammanager.futsalmontevarchi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteGaraInputDTO implements Serializable {
	private static final long serialVersionUID = -89965492745156632L;
	private LocalDate dataEvento;
	private String squadraCasa;
	private String squadraTrasferta;
	private String campionato;
	private List<Long> calciatoriIds;
	private Long capitanoId;
	private Long viceCapitanoId;
	private List<NoteGaraStaffInputDTO> staff;
}
