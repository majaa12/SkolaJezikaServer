package rs.ac.bg.fon.nprog.server.operation.upis;

import rs.ac.bg.fon.nprog.common.domain.Upis;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class ZapamtiUpisSO extends AbstractGenericOperation {

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Upis)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
