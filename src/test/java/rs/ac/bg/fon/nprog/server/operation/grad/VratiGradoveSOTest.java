package rs.ac.bg.fon.nprog.server.operation.grad;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

class VratiGradoveSOTest {

	private VratiGradoveSO operation;
	private Repository repository;
	private Grad grad;
	private ArrayList<Grad> gradovi;
	private Adresa adresa;
	private ArrayList<Adresa> adrese;
	
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(RepositoryDBGeneric.class);
		operation = new VratiGradoveSO(repository);
		adrese = new ArrayList<>();
		grad = new Grad(1l, "Beograd", adrese);
		adresa = new Adresa(1l, "Makedonska", 8, grad);
		adrese.add(adresa);
		grad.setAdrese(adrese);
		gradovi = new ArrayList<>();
		gradovi.add(grad);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		repository = null;
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
		Mockito.doReturn(gradovi).when(repository).getAll(Mockito.any(Grad.class));

		operation.executeOperation(grad);
		assertEquals(gradovi, operation.getList());
	}

}
