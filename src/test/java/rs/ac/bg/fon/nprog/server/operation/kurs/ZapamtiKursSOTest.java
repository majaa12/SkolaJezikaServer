package rs.ac.bg.fon.nprog.server.operation.kurs;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
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
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

public class ZapamtiKursSOTest {

	private ZapamtiKursSO operation;
	private static Repository<GenericEntity> repository;
	private static Kurs kurs;
	private TerminKursa tk;
	private Jezik jezik;
	private Profesor profesor;
	private Adresa a;
	private ArrayList<Adresa> adrese;
	private Grad grad;
	private SimpleDateFormat sdf;
	private ArrayList<TerminKursa> termini;
	private static long id;
	
 	
	@BeforeEach
	void setUp() throws Exception {
		operation = new ZapamtiKursSO();
		
		jezik = new Jezik(4l, "Italijanski");		
		profesor = new Profesor(6l, "Tijana", "Markovic", "0632365825", "tijanamark@gmail.com", jezik);
		adrese = new ArrayList<>();
		grad = new Grad(1l, "Beograd", adrese);
		a = new Adresa(1l, "Vojvode Stepe", 33, grad);
		adrese.add(a);
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		String poc = "2021-10-10";
		String zav = "2021-12-10";
		termini = new ArrayList<TerminKursa>();
		kurs = new Kurs(-1, "Kurs", Nivo.A1, TipKursa.Opsti, jezik, termini);
		tk = new TerminKursa(-1, sdf.parse(poc), sdf.parse(zav), "sreda", 8, 55, new BigDecimal("15000"), kurs,
				profesor, a);
		termini.add(tk);
		
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
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			DbConnectionFactory.getInstance().getConnection();
			repository = new RepositoryDBGeneric();
			ArrayList<TerminKursa> termini = kurs.getTermini();
			for (TerminKursa terminKursa : termini) {
				repository.delete(terminKursa);
			}
			repository.delete(kurs);
			DbConnectionFactory.getInstance().getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
			DbConnectionFactory.getInstance().getConnection().rollback();
		} finally {
			DbConnectionFactory.getInstance().getConnection().close();
		}
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
		id = kurs.getIDKursa();
		System.out.println(id);
		assertTrue(id > 0);
	}

}
