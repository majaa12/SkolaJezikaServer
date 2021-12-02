package rs.ac.bg.fon.nprog.server.operation.polaznik;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za vracanje svih
 * polaznika iz baze podataka koji odgovaraju kriterijumima pretrage.
 * 
 * @author Maja
 * @version 0.1
 */
public class PretraziPolaznikeSO extends AbstractGenericOperation {

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase PretraziPolaznikeSO
	 * i poziva parametarski konstruktor nadklase sa prosledjenim repository
	 * objektom
	 * 
	 * @param repository koji izvrsava operacije nad bazom.
	 */
	public PretraziPolaznikeSO(Repository repository) {
		super(repository);
	}

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase
	 * PretraziPolaznikeSO.
	 */
	public PretraziPolaznikeSO() {

	}

	/**
	 * Privatni atribut list tipa List{@literal <GenericEntity>} koji predstavlja
	 * listu generickih objekata koje operacija treba da vrati.
	 */
	private List<GenericEntity> list;

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
	 * Poziva brokera baze da pronadje listu polaznika koji odgovaraju pretrazi.
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
