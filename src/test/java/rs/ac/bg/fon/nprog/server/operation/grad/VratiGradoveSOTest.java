package rs.ac.bg.fon.nprog.server.operation.grad;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;

public class VratiGradoveSOTest {

	private VratiGradoveSO operation;
	private Grad grad;
	private ArrayList<Grad> gradovi;

	@BeforeEach
	void setUp() throws Exception {
		operation = new VratiGradoveSO();
		grad = new Grad(1l, "Beograd", null);
		gradovi = new ArrayList<>();
		gradovi.add(grad);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Grad g = new Grad();
		operation.preconditions(g);
	}

	@Test
	void executeOperation_successful() throws Exception {
		Grad grad1 = new Grad(2l, "Novi Sad", null);
		gradovi.add(grad1);
		operation.execute(grad);
		List<GenericEntity> list = operation.getList();
		assertEquals(gradovi, list);
	}

}
