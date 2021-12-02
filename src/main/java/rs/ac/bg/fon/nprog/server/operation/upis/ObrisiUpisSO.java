package rs.ac.bg.fon.nprog.server.operation.upis;

import rs.ac.bg.fon.nprog.common.domain.Upis;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za brisanje postojeceg
 * upisa iz baze podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class ObrisiUpisSO extends AbstractGenericOperation {

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase ObrisiUpisSO i
	 * poziva parametarski konstruktor nadklase sa prosledjenim repository objektom
	 * 
	 * @param repository koji izvrsava operacije nad bazom.
	 */
	public ObrisiUpisSO(Repository repository) {
		super(repository);
	}

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase ObrisiUpisSO.
	 */
	public ObrisiUpisSO() {

	}

	/**
	 * Proverava da li je prosledjeni parametar klase Upis.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Upis)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da obrise prosledjeni upis.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.delete(param);
	}
}
