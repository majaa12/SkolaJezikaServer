package rs.ac.bg.fon.nprog.server.operation;

import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

/**
 * Apstraktna klasa koja predstavlja Apstraktnu genericku operaciju koju
 * nasledjuju sve konkretne sistemske operacije.
 * 
 * @author Maja
 * @version 0.1
 *
 */
public abstract class AbstractGenericOperation {

	/**
	 * Protected atribut repository koji je tipa interfejs Repository.
	 */
	protected final Repository repository;

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase
	 * AbstractGenericOperation i inicijalizuje atribut repository kao objekat klase
	 * RepositoryDBGeneric.
	 */
	public AbstractGenericOperation() {
		this.repository = new RepositoryDBGeneric();
	}

	/**
	 * Opste izvrsenje sistemske operacije koje sluzi za upravljanje transakcijom.
	 * Podrazumeva proveru preduslova za prosledjeni parametar, otvaranje konekcije
	 * na bazu, izvrsenje same operacije, commitovanje ako je sve u redu. Ukoliko
	 * dodje do greske poziva se rollback transakcije, a u svakom slucaju se zatvara
	 * konekcija na bazu.
	 * 
	 * @param param Objekat nad kojim se izvrsava operacija kao Object tip.
	 * @throws Exception ukoliko dodje do greske prilikom izvrsavanja operacije.
	 */
	public final void execute(Object param) throws Exception {
		try {
			preconditions(param);
			startTransaction();
			executeOperation(param);
			commitTransaction();
		} catch (Exception ex) {
			ex.printStackTrace();
			rollbackTransaction();
			throw ex;
		} finally {
			disconnect();
		}
	}

	/**
	 * Apstraktna protected metoda koja proverava preduslove za prosledjeni
	 * parametar.
	 * 
	 * @param param Objekat nad kojim se proveravaju preduslovi kao Object tip.
	 * @throws Exception ukoliko objekat ne zadovoljava preduslove.
	 */
	protected abstract void preconditions(Object param) throws Exception;

	/**
	 * Privatna metoda za uspostavljanje konekcije na bazu.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom otvaranje konekcije na
	 *                   bazu
	 */
	private void startTransaction() throws Exception {
		((DbRepository) repository).connect();
	}

	/**
	 * Apstraktna protected metoda koja je zaduzena za konkretno izvrsenje sistemske
	 * operacije.
	 * 
	 * @param param Objekat nad kojim se izvrsava konkretna operacija kao Object tip.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja operacije.
	 */
	protected abstract void executeOperation(Object param) throws Exception;

	/**
	 * Privatna metoda za potvrdu svih promena nastalih u transakciji, a od
	 * prethodnog commit-a/rollback-a
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom potvrdjivanja promena
	 *                   nastalih tokom transakcije.
	 */
	private void commitTransaction() throws Exception {
		((DbRepository) repository).commit();
	}

	/**
	 * Privatna metoda za ponistavanje efekata trenutne transakcije.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom ponistavanja efekata
	 *                   transakcije.
	 */
	private void rollbackTransaction() throws Exception {
		((DbRepository) repository).rollback();
	}

	/**
	 * Privatna metoda za zatvaranje konekcije na bazu.
	 * 
	 * @throws Exception ukoliko dodje do greske prilikom zatvaranja konekcije na
	 *                   bazu.
	 */
	private void disconnect() throws Exception {
		((DbRepository) repository).disconnect();
	}
}
