package it.teammanager.futsalmontevarchi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.entity.Team;
import it.teammanager.futsalmontevarchi.entity.Utenti;
import it.teammanager.futsalmontevarchi.repository.CalciatoreRepository;
import it.teammanager.futsalmontevarchi.repository.StaffRepository;
import it.teammanager.futsalmontevarchi.repository.TeamRepository;
import it.teammanager.futsalmontevarchi.repository.UserRepository;
import it.teammanager.futsalmontevarchi.service.JsonDbService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DbInitializer {

	private final JsonDbService jsonDb;
	private final CalciatoreRepository calciatoreRepo;
	private final TeamRepository teamRepo;
	private final StaffRepository staffRepo;
	private final UserRepository userRepo;

	@PostConstruct
	public void init() {
		var users = jsonDb.readList("user.json", new TypeReference<List<Utenti>>() {});

		if (users == null || users.isEmpty()) {
			String encodedPassword = new BCryptPasswordEncoder().encode("FutsalMontevarchi2025!");

			Utenti admin = Utenti.builder()
								 .id(1L)
								 .username("admin")
								 .password(encodedPassword)
								 .role("ROLE_USER,ROLE_ADMIN")
								 .build();

			userRepo.save(admin);
		} else {
			userRepo.saveAll(users);
		}

		var calciatori = jsonDb.readList("calciatori.json", new TypeReference<List<Calciatore>>() {
		});
		var teams = jsonDb.readList("teams.json", new TypeReference<List<Team>>() {
		});
		var staff = jsonDb.readList("staff.json", new TypeReference<List<Staff>>() {
		});

		calciatoreRepo.saveAll(calciatori);
		teamRepo.saveAll(teams);
		staffRepo.saveAll(staff);
	}
}
