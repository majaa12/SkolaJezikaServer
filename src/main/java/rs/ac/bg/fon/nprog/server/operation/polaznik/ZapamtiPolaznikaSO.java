package rs.ac.bg.fon.nprog.server.operation.polaznik;

import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za dodavanje novog
 * polaznika u bazu podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class ZapamtiPolaznikaSO extends AbstractGenericOperation {

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase ZapamtiPolaznikaSO i
	 * poziva parametarski konstruktor nadklase sa prosledjenim repository objektom
	 * 
	 * @param repository koji izvrsava operacije nad bazom.
	 */
	public ZapamtiPolaznikaSO(Repository repository) {
		super(repository);
	}

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase ZapamtiPolaznikaSO.
	 */
	public ZapamtiPolaznikaSO() {

	}
	
	/**
	 * Proverava da li je prosledjeni parametar klase Polaznik.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Polaznik)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da zapamti novog polaznika.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
