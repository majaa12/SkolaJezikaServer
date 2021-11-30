package rs.ac.bg.fon.nprog.server.operation.upis;

import rs.ac.bg.fon.nprog.common.domain.Upis;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za dodavanje novog
 * upisa u bazu podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class ZapamtiUpisSO extends AbstractGenericOperation {

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
	 * Poziva brokera baze da zapamti novi upis.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
