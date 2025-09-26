package it.teammanager.futsalmontevarchi.entity;

import jakarta.persistence.Entity;
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
public class Team {
	@Id
	private Long id;
	private String name;
	private String imageUrl;
	private String campionato;
}
