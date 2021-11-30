package rs.ac.bg.fon.nprog.server.operation.profesor;

import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za dodavanje novog
 * profesora u bazu podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class ZapamtiProfesoraSO extends AbstractGenericOperation {

	/**
	 * Proverava da li je prosledjeni parametar klase Profesor.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Profesor)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da zapamti novog profesora.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
