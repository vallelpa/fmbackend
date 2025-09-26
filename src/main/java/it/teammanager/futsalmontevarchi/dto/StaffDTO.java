package it.teammanager.futsalmontevarchi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffDTO implements Serializable {

	private static final long serialVersionUID = -2432871568082382545L;
	private Long id;
	private String tipo;
	private String nome;
	private String tipoDocumento;
	private String numeroDocumento;

}
