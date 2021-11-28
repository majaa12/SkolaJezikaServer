package rs.ac.bg.fon.nprog.server.operation.profesor;

import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class ZapamtiProfesoraSO extends AbstractGenericOperation {

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Profesor)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
