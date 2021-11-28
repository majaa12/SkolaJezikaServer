package rs.ac.bg.fon.nprog.server.operation.polaznik;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class PretraziPolaznikeSO extends AbstractGenericOperation {

	private List<GenericEntity> list;

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Polaznik)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {
		list = repository.getAll(param);
	}

	public List<GenericEntity> getList() {
		return list;
	}
}
