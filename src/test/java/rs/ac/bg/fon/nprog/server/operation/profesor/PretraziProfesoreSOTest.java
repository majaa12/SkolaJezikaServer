package rs.ac.bg.fon.nprog.server.operation.profesor;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;

public class PretraziProfesoreSOTest {

	private PretraziProfesoreSO operation;
	private Profesor profesor;
	private Jezik jezik;
	private ArrayList<Profesor> profesori;

	@BeforeEach
	void setUp() throws Exception {
		operation = new PretraziProfesoreSO();
		jezik = new Jezik(2l, "Nemacki");
		profesor = new Profesor(1l, "Milena", "Petrovic", "0609584214", "milenapop@gmail.com", jezik);
		profesori = new ArrayList<>();
		profesori.add(profesor);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		jezik = null;
		profesor = null;
		profesori = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Profesor p = new Profesor();
		operation.preconditions(p);
	}

	@Test
	void executeOperation_successful() throws Exception {
		Profesor pretraga = new Profesor(-1, "Mil", "Pet", null, null, jezik);
		operation.execute(pretraga);
		assertEquals(profesori, operation.getList());
	}

}
