package rs.ac.bg.fon.nprog.server.operation.adresa;

import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import java.util.List;

import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za vracanje svih adresa
 * odredjenog grada iz baze podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class VratiAdreseSO extends AbstractGenericOperation {

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase VratiAdreseSO i
	 * poziva parametarski konstruktor nadklase sa prosledjenim repository objektom
	 * 
	 * @param repository koji izvrsava operacije nad bazom.
	 */
	public VratiAdreseSO(Repository repository) {
		super(repository);
	}

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase VratiAdreseSO.
	 */
	public VratiAdreseSO() {

	}

	/**
	 * Privatni atribut list tipa List{@literal <GenericEntity>} koji predstavlja
	 * listu generickih objekata koje operacija treba da vrati.
	 */
	private List<GenericEntity> list;

	/**
	 * Proverava da li je prosledjeni parametar klase Adresa.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Adresa)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da pronadje listu adresa grada koji je setovan na
	 * prosledjenom objektu adrese.
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
