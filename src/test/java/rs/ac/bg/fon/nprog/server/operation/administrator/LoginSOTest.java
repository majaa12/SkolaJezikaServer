package rs.ac.bg.fon.nprog.server.operation.administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

public class LoginSOTest {

	private LoginSO operation;
	private Repository repository;
	private GenericEntity admin;


	@BeforeEach
	void setUp() throws Exception {
		repository = mock(RepositoryDBGeneric.class);
		operation = new LoginSO(repository);
		admin = new Administrator(2l, "Jovan", "Maric", "jovan", "jovan");
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		repository = null;
		admin = null;
	}

	@Test
	void preconditions_whenNotInstanceOf_thenException() {
		Jezik jezik = new Jezik();
		assertThrows(java.lang.Exception.class, () -> operation.preconditions(jezik), "Neodgovarajuci tip parametra");
	}

	@Test
	void preconditions_successful() throws Exception {
		Administrator admin = new Administrator();
		operation.preconditions(admin);
	}
	
	@Test
	void executeOperation_successful() throws Exception{
		Mockito.doReturn(admin).when(repository).get(Mockito.any(Administrator.class));
		
		operation.executeOperation(admin);
		assertEquals(admin, (Administrator)operation.getEntity());
	}

}
