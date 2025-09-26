package it.teammanager.futsalmontevarchi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class JsonDbService {

	private final ObjectMapper mapper;

	public JsonDbService(ObjectMapper mapper) {
		this.mapper = mapper;
		mapper.findAndRegisterModules(); // Supporta LocalDate, Enum, ecc.
	}

	public <T> List<T> readList(String filename, TypeReference<List<T>> type) {
		try {
			return mapper.readValue(
					new ClassPathResource("db/" + filename).getInputStream(),
					type
			);
		} catch (IOException e) {
			throw new UncheckedIOException("Errore nella lettura di db/" + filename, e);
		}
	}

	public void exportList(String filename, List<?> data) {
		try {
			Path path = Paths.get("src/main/resources/db/" + filename);
			ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
			writer.writeValue(path.toFile(), data);
		} catch (IOException e) {
			throw new UncheckedIOException("Errore nell'esportazione di " + filename, e);
		}
	}

}