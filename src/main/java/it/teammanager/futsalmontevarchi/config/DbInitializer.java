package it.teammanager.futsalmontevarchi.config;

import com.fasterxml.jackson.core.type.TypeReference;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.entity.Team;
import it.teammanager.futsalmontevarchi.repository.CalciatoreRepository;
import it.teammanager.futsalmontevarchi.repository.StaffRepository;
import it.teammanager.futsalmontevarchi.repository.TeamRepository;
import it.teammanager.futsalmontevarchi.service.JsonDbService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbInitializer {

	private final JsonDbService jsonDb;
	private final CalciatoreRepository calciatoreRepo;
	private final TeamRepository teamRepo;
	private final StaffRepository staffRepo;

	public DbInitializer(JsonDbService jsonDb,
						 CalciatoreRepository calciatoreRepo,
						 TeamRepository teamRepo,
						 StaffRepository staffRepo) {
		this.jsonDb = jsonDb;
		this.calciatoreRepo = calciatoreRepo;
		this.teamRepo = teamRepo;
		this.staffRepo = staffRepo;
	}

	@PostConstruct
	public void init() {
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
