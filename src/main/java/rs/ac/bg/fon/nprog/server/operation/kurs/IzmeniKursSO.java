package rs.ac.bg.fon.nprog.server.operation.kurs;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za izmenu postojeceg
 * kursa iz baze podataka.
 * 
 * @author Maja
 * @version 0.1
 */
public class IzmeniKursSO extends AbstractGenericOperation {

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
	 * Poziva brokera baze da izmeni kurs prosledjen kao parametar ove metode. Zatim
	 * prolazi kroz listu njegovih termina i neke od njih brise, neke menja, a nove
	 * termine dodaje u listu.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		repository.edit(param);
		Kurs kurs = (Kurs) param;
		ArrayList<TerminKursa> termini = kurs.getTermini(); // termini u programu

		TerminKursa tk = new TerminKursa();
		tk.setKurs(kurs);
		List<GenericEntity> terminiBaza = repository.getAll(tk); // vrati sve termine tog kursa

		boolean nasao;
		for (GenericEntity ge : terminiBaza) {
			nasao = false;
			tk = (TerminKursa) ge;
			for (TerminKursa t : termini) {
				if (tk.getIDTermina() == t.getIDTermina()) {
					if (tk.equals(t)) {
						System.out.println("Nema edita!!!");
						nasao = true;
						break;
					}
					repository.edit(t);
					nasao = true;
					break;
				}
			}
			if (nasao == false) {
				repository.delete(tk);
			}
		}

		for (TerminKursa t : termini) {
			if (t.getIDTermina() == 0) {
				repository.add(t);
			}
		}
	}
}
