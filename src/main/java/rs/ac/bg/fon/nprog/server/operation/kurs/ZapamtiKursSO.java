package rs.ac.bg.fon.nprog.server.operation.kurs;

import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class ZapamtiKursSO extends AbstractGenericOperation {

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Kurs)) {
			throw new Exception("Neodogovarajuci tip parametra!");
		}
	}

	@Override
	protected void executeOperation(Object param) throws Exception {

		repository.add(param);
		Kurs kurs = (Kurs) param;
		ArrayList<TerminKursa> termini = kurs.getTermini();
		for (TerminKursa terminKursa : termini) {
			repository.add(terminKursa);
		}
	}

}
