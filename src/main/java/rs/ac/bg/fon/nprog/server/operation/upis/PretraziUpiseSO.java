package rs.ac.bg.fon.nprog.server.operation.upis;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Upis;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za vracanje svih upisa
 * iz baze podataka koji odgovaraju kriterijumima pretrage.
 * 
 * @author Maja
 * @version 0.1
 */
public class PretraziUpiseSO extends AbstractGenericOperation {

	/**
	 * Privatni atribut list tipa List{@literal <GenericEntity>} koji predstavlja
	 * listu generickih objekata koje operacija treba da vrati.
	 */
	private List<GenericEntity> list;

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
	 * Poziva brokera baze da pronadje listu upisa koji odgovaraju pretrazi.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		list = repository.getAll(param);
	}

	/**
	 * Vraca privatni atribut list.
	 * 
	 * @return list kao List{@literal <GenericEntity>}
	 */
	public List<GenericEntity> getList() {
		return list;
	}
}
