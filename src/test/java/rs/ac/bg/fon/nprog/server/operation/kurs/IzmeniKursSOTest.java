package rs.ac.bg.fon.nprog.server.operation.kurs;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.Nivo;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import rs.ac.bg.fon.nprog.common.domain.TipKursa;
import rs.ac.bg.fon.nprog.server.repository.Repository;

public class IzmeniKursSOTest {

	private IzmeniKursSO operation;
	private static Repository<GenericEntity> repository;
	private Kurs kurs;
	private TerminKursa tk;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private ArrayList<Adresa> adrese;
	private Grad grad;
	private SimpleDateFormat sdf;
	private ArrayList<TerminKursa> termini;

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		// rollback
	}

	@BeforeEach
	void setUp() throws Exception {
		operation = new IzmeniKursSO();

		jezik = new Jezik(2l, "Nemacki");
		profesor = new Profesor(1l, "Milena", "Petrovic", "0609584214", "milenapop@gmail.com", jezik);
		adrese = new ArrayList<>();
		grad = new Grad(1l, "Beograd", adrese);
		a = new Adresa(1l, "Vojvode Stepe", 33, grad);
		adrese.add(a);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-09-22";
		String zav = "2021-12-01";
		termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(1l, "Poslovni NEMACKI B1", Nivo.B1, TipKursa.Poslovni, jezik, termini);
		tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "utorak, petak 17:00-19:30", 6, 60,
				new BigDecimal("25000"), kurs, profesor, a);
		termini.add(tk);
		TerminKursa termin = new TerminKursa(0, sdf.parse(poc), sdf.parse(zav), "utorak, sreda 10:00-12:30", 10, 50,
				new BigDecimal("20000"), kurs, profesor, a);
		termini.add(termin);

	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		jezik = null;
		profesor = null;
		grad = null;
		adrese = null;
		a = null;
		termini = null;
		tk = null;
		kurs = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Kurs k = new Kurs();
		operation.preconditions(k);
	}

	@Test
	void executeOperation_successful() throws Exception {
		operation.execute(kurs);
		assertEquals("Poslovni NEMACKI B1", kurs.getNaziv());
		assertEquals(2, kurs.getTermini().size());
	}

}
