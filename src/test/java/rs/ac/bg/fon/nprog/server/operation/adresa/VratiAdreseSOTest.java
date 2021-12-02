package rs.ac.bg.fon.nprog.server.operation.adresa;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

class VratiAdreseSOTest {

	private VratiAdreseSO operation;
	private Repository repository;
	private Adresa adresa;
	private Grad grad;
	private ArrayList<Adresa> adrese;
	
	@BeforeEach
	void setUp() throws Exception {
		repository = mock(RepositoryDBGeneric.class);
		operation = new VratiAdreseSO(repository);
		adrese = new ArrayList<>();
		grad = new Grad(1l, "Beograd", adrese);
		adresa = new Adresa(1l, "Makedonska", 8, grad);
		adrese.add(adresa);
		
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		repository = null;
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
	void executeOperation_successful() throws Exception{
		Mockito.doReturn(adrese).when(repository).getAll(Mockito.any(Adresa.class));
		
		operation.executeOperation(adresa);
		assertEquals(adrese, operation.getList());
	}

}
