package rs.ac.bg.fon.nprog.server.repository.db;

import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Interfejs koji sadrzi default-ne metode kojima je odmah data implementacija.
 * Nasledjuje Repository{@literal <T>} interfejs.
 * 
 * @author Maja
 * @version 0.1
 *
 */
public interface DbRepository<T> extends Repository<T> {

	/**
	 * Default-na metoda za uspostavljanje konekcije na bazu.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom otvaranje konekcije na
	 *                   bazu
	 */
	default public void connect() throws Exception {
		DbConnectionFactory.getInstance().getConnection();
	}

	/**
	 * Default-na metoda za zatvaranje konekcije na bazu.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom zatvaranja konekcije na
	 *                   bazu
	 */
	default public void disconnect() throws Exception {
		DbConnectionFactory.getInstance().getConnection().close();
	}

	/**
	 * Default-na metoda za potvrdu svih promena nastalih u transakciji, a od
	 * prethodnog commit-a/rollback-a
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom potvrdjivanja promena
	 *                   nastalih tokom transakcije.
	 */
	default public void commit() throws Exception {
		DbConnectionFactory.getInstance().getConnection().commit();
	}

	/**
	 * Default-na metoda za ponistavanje efekata trenutne transakcije.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom ponistavanja efekata
	 *                   transakcije.
	 */
	default public void rollback() throws Exception {
		DbConnectionFactory.getInstance().getConnection().rollback();
	}
}
