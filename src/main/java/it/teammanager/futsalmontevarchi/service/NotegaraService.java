package it.teammanager.futsalmontevarchi.service;

import it.teammanager.futsalmontevarchi.entity.Calciatore;
import it.teammanager.futsalmontevarchi.entity.NoteGara;
import it.teammanager.futsalmontevarchi.entity.Staff;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.docx4j.Docx4J;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class NotegaraService {

	public void generaNotaGaraDocx(NoteGara evento, Path outputPath) throws Exception {
		try (XWPFDocument doc = new XWPFDocument()) {

			// Logo
			XWPFParagraph logoPar = doc.createParagraph();
			logoPar.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun logoRun = logoPar.createRun();
			try (InputStream is = getClass().getResourceAsStream("/static/logo.png")) {
				logoRun.addPicture(is, Document.PICTURE_TYPE_PNG, "logo.png", Units.toEMU(100), Units.toEMU(100));
			}

			// Intestazione
			XWPFParagraph header = doc.createParagraph();
			header.setAlignment(ParagraphAlignment.CENTER);
			XWPFRun run = header.createRun();
			run.setBold(true);
			run.setFontSize(14);
			run.setText("FUTSAL MONTEVARCHI");
			run.addBreak();
			run.setText("Distinta Giocatori partecipanti alla gara:");
			run.addBreak();
			run.setText(evento.getSquadraCasa() + " – " + evento.getSquadraTrasferta());
			run.addBreak();
			run.setText(evento.getCampionato() + " CALCIO A 5 da disputare il : " +
								evento.getDataEvento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

			// Tabella calciatori
			XWPFTable table = doc.createTable();
			table.setWidth("100%");
			XWPFTableRow headerRow = table.getRow(0);
			headerRow.getCell(0).setText("N°");
			headerRow.addNewTableCell().setText("Data di nascita");
			headerRow.addNewTableCell().setText("Cognome e Nome");
			headerRow.addNewTableCell().setText("Matricola F.I.G.C.");
			headerRow.addNewTableCell().setText("Tipo\nDocumento");
			headerRow.addNewTableCell().setText("N° Documento");

			for (Calciatore c : evento.getCalciatori()) {
				XWPFTableRow row = table.createRow();
				row.getCell(0).setText(String.valueOf(c.getNumeroMaglia()));
				row.getCell(1).setText(c.getDataNascita().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				StringBuilder nomeCompleto = new StringBuilder(c.getCognomeNome());
				if (c.isCapitano()) {
					nomeCompleto.append(" CAP");
				}
				if (c.isViceCapitano()) {
					nomeCompleto.append(" V.CAP");
				}
				row.getCell(2).setText(nomeCompleto.toString());
				row.getCell(3).setText(c.getMatricolaFIGC() != null ? c.getMatricolaFIGC() : "");
				row.getCell(4).setText(c.getTipoDocumento());
				row.getCell(5).setText(c.getNumeroDocumento());
			}

			// Staff
			XWPFParagraph staffTitle = doc.createParagraph();
			staffTitle.setSpacingBefore(300);
			XWPFRun staffRun = staffTitle.createRun();
			staffRun.setBold(true);

			XWPFTable staffTable = doc.createTable();
			staffTable.setWidth("100%");

			List<Staff> staffList = evento.getStaff();
			if (staffList != null && !staffList.isEmpty()) {
				// Intestazione con primo record
				Staff primo = staffList.get(0);
				XWPFTableRow staffHeader = staffTable.getRow(0);
				staffHeader.getCell(0).setText(primo.getTipo().getDescrizione().toUpperCase() + ": " + primo.getCognomeNome());
				staffHeader.addNewTableCell().setText("Documento n° " + primo.getNumeroDocumento());

				// Righe successive
				for (int i = 1; i < staffList.size(); i++) {
					Staff s = staffList.get(i);
					XWPFTableRow row = staffTable.createRow();
					String ruoloNome = s.getTipo().getDescrizione().toUpperCase() + ": " + s.getCognomeNome();
					row.getCell(0).setText(ruoloNome);
					row.getCell(1).setText("Documento n° " + s.getNumeroDocumento());
				}
			}


			// Firma arbitro
			XWPFParagraph parArbitro = doc.createParagraph();
			parArbitro.setAlignment(ParagraphAlignment.LEFT);
			parArbitro.setSpacingBefore(300);
			XWPFRun runArbitro = parArbitro.createRun();
			runArbitro.setBold(true);
			runArbitro.setText("L'ARBITRO");
			runArbitro.addBreak();
			runArbitro.setText("Firma: ____________________________");

			// Firma dirigente
			XWPFParagraph parDirigente = doc.createParagraph();
			parDirigente.setAlignment(ParagraphAlignment.LEFT);
			XWPFRun runDirigente = parDirigente.createRun();
			runDirigente.setBold(true);
			runDirigente.setText("IL DIRIGENTE ACCOMPAGNATORE");
			runDirigente.addBreak();
			runDirigente.setText("Firma: ____________________________");

			try (FileOutputStream out = new FileOutputStream(outputPath.toFile())) {
				doc.write(out);
			}
		}
	}

	public void convertDocxToPdf(InputStream docxInputStream, OutputStream pdfOutputStream) throws Exception {
		// Carica il documento Word da un InputStream
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(docxInputStream);

		// Usa il metodo con due argomenti
		Docx4J.toPDF(wordMLPackage, pdfOutputStream);
	}
}
