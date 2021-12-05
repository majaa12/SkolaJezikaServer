package rs.ac.bg.fon.nprog.server.operation.polaznik;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.DbConnectionFactory;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

public class ZapamtiPolaznikaSOTest {

	private ZapamtiPolaznikaSO operation;
	private Polaznik polaznik;
	private static Repository<GenericEntity> repository;
	private static long id;

	@BeforeEach
	void setUp() throws Exception {
		operation = new ZapamtiPolaznikaSO();
		polaznik = new Polaznik(-1, "Maja", "Colovic", "1234567891012", "0611234567", "maja@gmail.com", "Makedonska 3");
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		polaznik = null;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			DbConnectionFactory.getInstance().getConnection();
			Polaznik p = new Polaznik(id, "Maja", "Colovic", "1234567891012", "0611234567", "maja@gmail.com",
					"Makedonska 3");
			repository = new RepositoryDBGeneric();
			repository.delete(p);
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
		Polaznik p = new Polaznik();
		operation.preconditions(p);
	}

	@Test
	void executeOperation_successful() throws Exception {
		operation.execute(polaznik);
		id = polaznik.getIDPolaznika();
		System.out.println(id);
		assertTrue(id > 0);
	}

}
