package rs.ac.bg.fon.nprog.server.operation.kurs;

import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import java.util.ArrayList;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za dodavanje novog kursa
 * u bazu podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class ZapamtiKursSO extends AbstractGenericOperation {

	/**
	 * Proverava da li je prosledjeni parametar klase Kurs.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Kurs)) {
			throw new Exception("Neodogovarajuci tip parametra!");
		}
	}

	/**
	 * Poziva brokera baze da zapamti novi kurs, a zatim i da zapamti sve termine
	 * tog kursa.
	 */
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
