package rs.ac.bg.fon.nprog.server.operation.administrator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;

public class LoginSOTest {

	private LoginSO operation;
	private GenericEntity admin;

	@BeforeEach
	void setUp() throws Exception {
		operation = new LoginSO();
		admin = new Administrator(1l, "Maja", "Colovic", "admin", "admin");
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
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
	void executeOperation_successful() throws Exception {
		operation.execute(admin);
		assertEquals(admin, (Administrator) operation.getEntity());
	}

}
