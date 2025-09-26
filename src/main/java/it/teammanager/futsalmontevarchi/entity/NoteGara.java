package it.teammanager.futsalmontevarchi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NoteGara {
	private LocalDate dataEvento;
	private String squadraCasa;
	private String squadraTrasferta;
	private String campionato;
	private List<Calciatore> calciatori;
	private List<Staff> staff;
}
