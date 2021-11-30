package rs.ac.bg.fon.nprog.server.operation.kurs;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

/**
 * Klasa koja predstavlja konkretnu sistemsku operaciju za vracanje svih kurseva
 * iz baze podataka koji odgovaraju kriterijumima pretrage.
 * 
 * @author Maja
 * @version 0.1
 */
public class PretraziKurseveSO extends AbstractGenericOperation {

	/**
	 * Privatni atribut list tipa List{@literal <GenericEntity>} koji predstavlja
	 * listu generickih objekata koje operacija treba da vrati.
	 */
	private List<GenericEntity> list;

	/**
	 * Proverava da li je prosledjeni parametar klase Kurs.
	 */
	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Kurs)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

	/**
	 * Poziva brokera baze da pronadje listu kurseva koji odgovaraju pretrazi. Zatim
	 * prolazi kroz tu listu i za svaki kurs trazi njegove termine iz baze podataka.
	 */
	@Override
	protected void executeOperation(Object param) throws Exception {
		list = repository.getAll(param); // vraca listu kurseva sa null terminima

		for (GenericEntity genericEntity : list) {
			Kurs k = (Kurs) genericEntity;

			TerminKursa tk = new TerminKursa();
			tk.setKurs(k); // termin koji ima samo taj kurs(null)

			List<GenericEntity> list1 = repository.getAll(tk);
			ArrayList<TerminKursa> termini = new ArrayList<>();
			for (GenericEntity ge : list1) {
				tk = (TerminKursa) ge;
				termini.add(tk);
			}
			k.setTermini(termini);
		}
	}

	/**
	 * Vraca privatni atribut list.
	 * 
	 * @return list kao List{@literal <GenericEntity>}
	 */
	public List<GenericEntity> getList() {
		return list;
	}
}
