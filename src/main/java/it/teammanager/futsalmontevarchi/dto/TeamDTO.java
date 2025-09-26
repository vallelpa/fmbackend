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
public class TeamDTO implements Serializable {
	private static final long serialVersionUID = 4198474189508648883L;
	private Long id;
	private String name;
	private String imageUrl;
	private String campionato;
}
