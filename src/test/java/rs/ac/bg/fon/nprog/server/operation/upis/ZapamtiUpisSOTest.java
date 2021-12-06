package rs.ac.bg.fon.nprog.server.operation.upis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.Nivo;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import rs.ac.bg.fon.nprog.common.domain.TipKursa;
import rs.ac.bg.fon.nprog.common.domain.Upis;
import rs.ac.bg.fon.nprog.server.repository.db.DbConnectionFactory;


public class ZapamtiUpisSOTest {

	private ZapamtiUpisSO operation;
	private Upis upis;
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
		operation = new ZapamtiUpisSO();

		admin = new Administrator(1l, "Maja", "Colovic", "admin", "admin");
		polaznik = new Polaznik(3l, "Ivana", "Ilic", "1203745125", "065213232", "ivana@gmail.com", "Glavna 14");
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
		tk = new TerminKursa(1l, sdf.parse(poc), sdf.parse(zav), "utorak, petak 17:00-19:30", 6, 60, new BigDecimal("25000"), kurs,
				profesor, a);
		termini.add(tk);
		upis = new Upis(sdf.parse(dat), polaznik, admin, tk);
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
	}
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "delete from upis where IDTermina = 1 and IDPolaznika = 3 and IDKursa = 1";
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();
			
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
		Upis u = new Upis();
		operation.preconditions(u);
	}
	
	@Test
	void executeOperation_successful() throws Exception {
		String poruka = "Neuspesno!";
		operation.execute(upis);
		poruka = "Uspesno sacuvan upis";
		assertEquals("Uspesno sacuvan upis", poruka);
		
	}

}
