package rs.ac.bg.fon.nprog.server.operation.polaznik;

import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class ZapamtiPolaznikaSO extends AbstractGenericOperation {

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Polaznik)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.add(param);
	}
}
