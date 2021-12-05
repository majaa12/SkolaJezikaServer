package rs.ac.bg.fon.nprog.server.operation.polaznik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;

public class PretraziPolaznikeSOTest {

	private PretraziPolaznikeSO operation;
	private Polaznik polaznik;
	private ArrayList<Polaznik> polaznici;

	@BeforeEach
	void setUp() throws Exception {
		operation = new PretraziPolaznikeSO();
		polaznik = new Polaznik(3l, "Ivana", "Ilic", "1203745125", "065213232", "ivana@gmail.com", "Glavna 14");
		polaznici = new ArrayList<>();
		polaznici.add(polaznik);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		polaznik = null;
		polaznici = null;

	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Polaznik p = new Polaznik();
		operation.preconditions(p);
	}

	@Test
	void executeOperation_successful() throws Exception {
		Polaznik pretraga = new Polaznik(-1, "iv", "il", null, null, null, null);
		operation.execute(pretraga);
		assertEquals(polaznici, operation.getList());
	}

}
