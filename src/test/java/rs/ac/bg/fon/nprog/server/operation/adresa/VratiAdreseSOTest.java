package rs.ac.bg.fon.nprog.server.operation.adresa;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;

public class VratiAdreseSOTest {

	private VratiAdreseSO operation;
	private Adresa adresa;
	private Adresa a;
	private Grad grad;
	private ArrayList<Adresa> adrese;

	@BeforeEach
	void setUp() throws Exception {
		operation = new VratiAdreseSO();
		grad = new Grad(1l, "Beograd", null);
		adrese = new ArrayList<>();
		adresa = new Adresa(1l, "Vojvode Stepe", 33, grad);
		adrese.add(adresa);
		a = new Adresa(2l, "Narodnih heroja", 50, grad);
		adrese.add(a);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		adrese = null;
		grad = null;
		adresa = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Adresa a = new Adresa();
		operation.preconditions(a);
	}

	@Test
	void executeOperation_successful() throws Exception {
		operation.execute(adresa);
		List<GenericEntity> list = operation.getList();
		assertEquals(2, list.size());
		assertEquals(adrese, list);
	}

}
