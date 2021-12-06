package rs.ac.bg.fon.nprog.server.operation.profesor;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.server.repository.db.DbConnectionFactory;

public class ZapamtiProfesoraSOTest {

	private ZapamtiProfesoraSO operation;
	private Jezik jezik;
	private Profesor profesor;
	private static long id;

	@BeforeEach
	void setUp() throws Exception {
		operation = new ZapamtiProfesoraSO();
		jezik = new Jezik(1l, "Engleski");
		profesor = new Profesor(-1, "Uros", "Maric", "0623214589", "uros@gmail.com", jezik);
	}

	@AfterEach
	void tearDown() throws Exception {
		operation = null;
		jezik = null;
		profesor = null;
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		try {
			Connection con = DbConnectionFactory.getInstance().getConnection();
			String query = "delete from profesor where IDProfesora = " + id;
			System.out.println(query);
			PreparedStatement ps = con.prepareStatement(query);
			ps.executeUpdate();

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
		Profesor p = new Profesor();
		operation.preconditions(p);
	}

	@Test
	void executeOperation_successful() throws Exception {
		operation.execute(profesor);
		id = profesor.getIDProfesora();
		System.out.println(id);
		assertTrue(id > 0);
	}

}
