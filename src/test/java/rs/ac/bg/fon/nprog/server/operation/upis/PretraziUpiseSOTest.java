/*package rs.ac.bg.fon.nprog.server.operation.upis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.Nivo;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import rs.ac.bg.fon.nprog.common.domain.TipKursa;
import rs.ac.bg.fon.nprog.common.domain.Upis;

public class PretraziUpiseSOTest {

	private PretraziUpiseSO operation;
	private Upis upis;
	private ArrayList<Upis> upisi;
	private Polaznik polaznik;
	private Administrator admin;
	private Kurs kurs;
	private TerminKursa tk;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private ArrayList<Adresa> adrese;
	private Grad grad;
	private SimpleDateFormat sdf;
	private ArrayList<TerminKursa> termini;

	@BeforeEach
	void setUp() throws Exception {
		operation = new PretraziUpiseSO();

		admin = new Administrator(1l, "Maja", "Colovic", "admin", "admin");
		polaznik = new Polaznik(1l, "Petar", "Jokic", "0203995710222", "0625454654", "petarjokic@gmail.com",
				"Jablanicka 65");
		jezik = new Jezik(2l, "Nemacki");
		profesor = new Profesor(1l, "Milena", "Petrovic", "0609584214", "milenapop@gmail.com", jezik);
		adrese = new ArrayList<>();
		grad = new Grad(1l, "Beograd", adrese);
		a = new Adresa(1l, "Vojvode Stepe", 33, grad);
		adrese.add(a);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-09-22";
		String zav = "2021-12-01";
		String dat = "2021-09-20";
		termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(1l, "Poslovni nemacki B1", Nivo.B1, TipKursa.Poslovni, jezik, termini);
		tk = new TerminKursa(1l, new java.sql.Date(sdf.parse(poc).getTime()), new java.sql.Date(sdf.parse(zav).getTime()), "utorak, petak 17:00-19:30", 6, 60,
				new BigDecimal("25000"), kurs, profesor, a);
		termini.add(tk);
		upis = new Upis(new java.sql.Date(sdf.parse(dat).getTime()), polaznik, admin, tk);
		upisi = new ArrayList<>();
		upisi.add(upis);

	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		upis = null;
		polaznik = null;
		admin = null;
		kurs = null;
		jezik = null;
		profesor = null;
		a = null;
		adrese = null;
		grad = null;
		termini = null;
		upisi = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Upis u = new Upis();
		operation.preconditions(u);
	}

	@Test
	void executeOperation_successful() throws Exception {
		Kurs k = new Kurs(1l, "nemacki", Nivo.B1, TipKursa.Poslovni, jezik, null);
		Polaznik p = new Polaznik(-1, "Pet", "ic", null, null, null, null);
		TerminKursa t = new TerminKursa();
		t.setKurs(k);

		Upis up;
		Upis pretraga = new Upis(null, p, admin, t);
		operation.execute(pretraga);
		
		
		
		assertEquals(1, operation.getList().size());
		assertEquals(upisi, operation.getList());
	}

}
*/