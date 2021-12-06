/*package rs.ac.bg.fon.nprog.server.operation.kurs;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
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

public class PretraziKurseveSOTest {

	private PretraziKurseveSO operation;
	private Kurs kurs;
	private TerminKursa tk;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private ArrayList<Adresa> adrese;
	private Grad grad;
	private SimpleDateFormat sdf;
	private ArrayList<TerminKursa> termini;
	private ArrayList<Kurs> kursevi;

	@BeforeEach
	void setUp() throws Exception {
		operation = new PretraziKurseveSO();

		jezik = new Jezik(3l, "Francuski");
		profesor = new Profesor(5l, "Zarko", "Jankovic", "0623232658", "zarejank@gmail.com", jezik);
		adrese = new ArrayList<>();
		grad = new Grad(2l, "Novi Sad", adrese);
		a = new Adresa(4l, "Radnicka", 15, grad);
		adrese.add(a);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-10-01";
		String zav = "2021-12-01";
		Date pocetak = sdf.parse(poc);
		Date zavrsetak = sdf.parse(zav);
		termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(4l, "Opsti francuski B2", Nivo.B2, TipKursa.Opsti, jezik, null);
		tk = new TerminKursa(4l, new java.sql.Date(pocetak.getTime()), new java.sql.Date(zavrsetak.getTime()), "ponedeljak, sreda 19:00-20:45", 10, 60,
				new BigDecimal("25000"), kurs, profesor, a);
		termini.add(tk);
		kurs.setTermini(termini);

		kursevi = new ArrayList<>();
		kursevi.add(kurs);

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
		kursevi = null;
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
		Kurs pretraga = new Kurs(-1, "francuski", Nivo.B2, null, jezik, null);
		operation.execute(pretraga);

		assertEquals(kursevi, operation.getList());
	}
}
*/