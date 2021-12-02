package rs.ac.bg.fon.nprog.server.operation.jezik;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

public class VratiJezikeSOTest {

	private VratiJezikeSO operation;
	private Repository<GenericEntity> repository;
	private Jezik jezik;
	private ArrayList<Jezik> jezici;

	@BeforeEach
	void setUp() throws Exception {
		repository = mock(RepositoryDBGeneric.class);
		operation = new VratiJezikeSO(repository);
		jezik = new Jezik(1l, "Italijanski");
		jezici = new ArrayList<>();
		jezici.add(jezik);
	}

	@AfterEach
	void tearDown() throws Exception {
		repository = null;
		operation = null;
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
		Mockito.doReturn(jezici).when(repository).getAll(Mockito.any(Jezik.class));

		operation.executeOperation(jezik);
		assertEquals(jezici, operation.getList());
	}

}
