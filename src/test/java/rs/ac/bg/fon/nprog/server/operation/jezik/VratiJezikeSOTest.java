package rs.ac.bg.fon.nprog.server.operation.jezik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;

public class VratiJezikeSOTest {

	private VratiJezikeSO operation;
	private Jezik jezik;
	private ArrayList<Jezik> jezici;

	@BeforeEach
	void setUp() throws Exception {
		operation = new VratiJezikeSO();
		jezik = new Jezik(1l, "Engleski");
		jezici = new ArrayList<>();
		jezici.add(jezik);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		jezik = null;
		jezici = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Polaznik p = new Polaznik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(p), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Jezik j = new Jezik();
		operation.preconditions(j);
	}

	@Test
	void executeOperation_successful() throws Exception {

		jezici.add(new Jezik(2l, "Nemacki"));
		jezici.add(new Jezik(3l, "Francuski"));
		jezici.add(new Jezik(4l, "Italijanski"));
		jezici.add(new Jezik(5l, "Spanski"));
		jezici.add(new Jezik(6l, "Ruski"));
		operation.execute(jezik);
		List<GenericEntity> list = operation.getList();
		assertEquals(6, list.size());
		assertEquals(jezici, list);
	}

}
