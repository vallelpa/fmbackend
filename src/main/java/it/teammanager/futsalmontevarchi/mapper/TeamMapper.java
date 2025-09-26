package it.teammanager.futsalmontevarchi.mapper;

import it.teammanager.futsalmontevarchi.dto.TeamDTO;
import it.teammanager.futsalmontevarchi.entity.Team;

public class TeamMapper {

	public static TeamDTO toDTO(Team team) {
		if (team == null) return null;

		return TeamDTO.builder()
					   .id(team.getId())
					   .name(team.getName())
					   .imageUrl(team.getImageUrl())
					   .campionato(team.getCampionato())
					   .build();
	}

	public static Team fromDTO(TeamDTO dto) {
		if (dto == null) return null;

		return Team.builder()
					   .id(dto.getId())
					   .name(dto.getName())
					   .imageUrl(dto.getImageUrl())
					   .campionato(dto.getCampionato())
					   .build();
	}
}

