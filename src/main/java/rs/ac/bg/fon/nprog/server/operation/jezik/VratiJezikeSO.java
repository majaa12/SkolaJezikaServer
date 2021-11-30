package rs.ac.bg.fon.nprog.server.operation.jezik;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za vracanje svih jezika
 * iz baze podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class VratiJezikeSO extends AbstractGenericOperation {

	/**
	 * Privatni atribut list tipa List{@literal <GenericEntity>} koji predstavlja
	 * listu generickih objekata koje operacija treba da vrati.
	 */
	private List<GenericEntity> list;

	/**
	 * Proverava da li je prosledjeni parametar klase Jezik.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Jezik)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da pronadje listu svih jezika iz baze podataka.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		list = repository.getAll(param);
	}

	/**
	 * Vraca privatni atribut list sa generickim objektima koje je operacija
	 * vratila.
	 * 
	 * @return list kao List{@literal <GenericEntity>}
	 */
	public List<GenericEntity> getList() {
		return list;
	}
}
