package it.teammanager.futsalmontevarchi.controller;

import it.teammanager.futsalmontevarchi.dto.NoteGaraInputDTO;
import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.entity.NoteGara;
import it.teammanager.futsalmontevarchi.entity.Staff;
import it.teammanager.futsalmontevarchi.entity.TipoStaff;
import it.teammanager.futsalmontevarchi.mapper.NoteGaraMapper;
import it.teammanager.futsalmontevarchi.service.NotegaraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/notegara")
@RequiredArgsConstructor
public class NotaGaraController {

	private final NotegaraService notegaraService;
	private final NoteGaraMapper noteGaraMapper;


//	@GetMapping("/genera")
//	public ResponseEntity<byte[]> generaDocx() throws Exception {
	@PostMapping("/genera")
	public ResponseEntity<byte[]> generaDocx(@RequestBody NoteGaraInputDTO input) throws Exception {
		NoteGara noteGara = noteGaraMapper.toEventoSportivo(input);

		// Crea file temporaneo DOCX
		String nomeFile = "nota_gara_" +
								  noteGara.getSquadraCasa().replace(" ", "_") + "_" +
								  noteGara.getSquadraTrasferta().replace(" ", "_");
		Path docxPath = Files.createTempFile(nomeFile, ".docx");

		// Genera il DOCX
		notegaraService.generaNotaGaraDocx(noteGara, docxPath);

		// Leggi i byte
		byte[] fileBytes = Files.readAllBytes(docxPath);

		// Prepara headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.wordprocessingml.document"));
		headers.setContentDisposition(ContentDisposition.attachment().filename(nomeFile+".docx").build());

		// Pulisci file temporaneo
		Files.deleteIfExists(docxPath);

		return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
	}

	@GetMapping("/genera/pdf")
	public ResponseEntity<byte[]> generaPdf() throws Exception {
		NoteGara evento = mockEvento();

		// Crea file temporanei
		Path docxPath = Files.createTempFile("nota_gara", ".docx");
		Path pdfPath = docxPath.resolveSibling("nota_gara.pdf");

		// Genera il DOCX
		notegaraService.generaNotaGaraDocx(evento, docxPath);

		// Converte in PDF
		try (InputStream docxInputStream = Files.newInputStream(docxPath);
			 OutputStream pdfOutputStream = Files.newOutputStream(pdfPath)) {
			notegaraService.convertDocxToPdf(docxInputStream, pdfOutputStream);
		}

		// Leggi i byte del PDF
		byte[] fileBytes = Files.readAllBytes(pdfPath);

		// Prepara headers
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.setContentDisposition(ContentDisposition.attachment().filename("nota_gara.pdf").build());

		// Pulisci file temporanei
		Files.deleteIfExists(docxPath);
		Files.deleteIfExists(pdfPath);

		return new ResponseEntity<>(fileBytes, headers, HttpStatus.OK);
	}

	private NoteGara mockEvento() {
		NoteGara evento = new NoteGara();
		evento.setDataEvento(LocalDate.of(2025, 9, 25));
		evento.setSquadraCasa("Verona Futsal");
		evento.setSquadraTrasferta("Padova Five");
		evento.setCampionato("SERIE C2 GIRONE D");

		List<Calciatore> calciatori = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			Calciatore c = new Calciatore();
			c.setNumeroMaglia(i);
			c.setDataNascita(LocalDate.of(1995 + (i % 5), i % 12 + 1, i % 28 + 1));
			c.setCognomeNome("Cognome" + i + " Nome" + i);
			c.setMatricolaFIGC(i % 2 == 0 ? "FIGC" + (1000 + i) : null);
			c.setTipoDocumento("CI");
			c.setNumeroDocumento("DOC" + (2000 + i));
			calciatori.add(c);
		}
		Calciatore c = new Calciatore();
		c.setNumeroMaglia(44);
		c.setDataNascita(LocalDate.of(1995 + (12 % 5), 12 % 12 + 1, 12 % 28 + 1));
		c.setCognomeNome("Ciagli Edoardo");
		c.setMatricolaFIGC(12 % 2 == 0 ? "FIGC" + (1000 + 12) : null);
		c.setTipoDocumento("CI");
		c.setNumeroDocumento("DOC" + (2000 + 12));
		c.setCapitano(true);
		calciatori.add(c);

		c = new Calciatore();
		c.setNumeroMaglia(44);
		c.setDataNascita(LocalDate.of(1995 + (12 % 5), 12 % 12 + 1, 12 % 28 + 1));
		c.setCognomeNome("Falanga Gennaro");
		c.setMatricolaFIGC(12 % 2 == 0 ? "FIGC" + (1000 + 12) : null);
		c.setTipoDocumento("CI");
		c.setNumeroDocumento("DOC" + (2000 + 12));
		c.setViceCapitano(true);
		calciatori.add(c);
		evento.setCalciatori(calciatori);

		List<Staff> staff = List.of(
				new Staff(1L,TipoStaff.ALLENATORE, "Massi Mirko", "XY987654", 1L),
				new Staff(2L,TipoStaff.ALLENATORE_IN_SECONDA, "Notari Francensco", "XY123456",1L),
				new Staff(3L,TipoStaff.DIRIGENTE_ARBITRO, "Vallelonga Pasquale", "XY654321",1L),
				new Staff(4L,TipoStaff.MASSAGGIATORE, "Giallo Giovanni", "523454321",1L)
		);
		evento.setStaff(staff);

		return evento;
	}
}

