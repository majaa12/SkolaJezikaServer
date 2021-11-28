package rs.ac.bg.fon.nprog.server.operation;

import rs.ac.bg.fon.nprog.server.repository.Repository;
import rs.ac.bg.fon.nprog.server.repository.db.DbRepository;
import rs.ac.bg.fon.nprog.server.repository.db.impl.RepositoryDBGeneric;

public abstract class AbstractGenericOperation {

	protected final Repository repository;

	public AbstractGenericOperation() {
		this.repository = new RepositoryDBGeneric();
	}

	public final void execute(Object param) throws Exception {
		try {
			preconditions(param);
			startTransaction();
			executeOperation(param);
			commitTransaction();
		} catch (Exception ex) {
			ex.printStackTrace();
			rollbackTransaction();
			throw ex;
		} finally {
			disconnect();
		}
	}

	protected abstract void preconditions(Object param) throws Exception;

	private void startTransaction() throws Exception {
		((DbRepository) repository).connect();
	}

	protected abstract void executeOperation(Object param) throws Exception;

	private void commitTransaction() throws Exception {
		((DbRepository) repository).commit();
	}

	private void rollbackTransaction() throws Exception {
		((DbRepository) repository).rollback();
	}

	private void disconnect() throws Exception {
		((DbRepository) repository).disconnect();
	}
}
