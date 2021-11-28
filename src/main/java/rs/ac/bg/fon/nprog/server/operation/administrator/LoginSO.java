package rs.ac.bg.fon.nprog.server.operation.administrator;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class LoginSO extends AbstractGenericOperation {

	GenericEntity entity;

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Administrator)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		entity = (Administrator) repository.get(param);
	}

	public GenericEntity getEntity() {
		return entity;
	}
}
