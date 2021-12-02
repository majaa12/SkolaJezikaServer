package rs.ac.bg.fon.nprog.server.operation.administrator;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.repository.Repository;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za prijavljivanje
 * administratora na sistem.
 * 
 * @author Maja
 * @version 0.1
 */
public class LoginSO extends AbstractGenericOperation {

	/**
	 * Parametarski konstruktor koji inicijalizuje objekat klase LoginSO i poziva
	 * parametarski konstruktor nadklase sa prosledjenim repository objektom
	 * 
	 * @param repository koji izvrsava operacije nad bazom.
	 */
	public LoginSO(Repository repository) {
		super(repository);
	}

	/**
	 * Besparametarski konstruktor koji inicijalizuje objekat klase LoginSO.
	 */
	public LoginSO() {

	}

	/**
	 * Atribut entity klase GenericEntity.
	 */
	GenericEntity entity;

	/**
	 * Proverava da li je prosledjeni parametar klase Administrator.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Administrator)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da pronadje administratora sa kredencijalima koje ima i
	 * prosledjeni objekat.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		entity = (Administrator) repository.get(param);
	}

	/**
	 * Vraca privatni atribut entity.
	 * 
	 * @return entity kao GenericEntity
	 */
	public GenericEntity getEntity() {
		return entity;
	}
}
