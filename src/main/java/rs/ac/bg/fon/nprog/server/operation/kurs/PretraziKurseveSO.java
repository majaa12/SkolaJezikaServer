package rs.ac.bg.fon.nprog.server.operation.kurs;

import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.TerminKursa;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;

public class PretraziKurseveSO extends AbstractGenericOperation {

	private List<GenericEntity> list;

	@Override
	protected void preconditions(Object param) throws Exception {
		if (!(param instanceof Kurs)) {
			throw new Exception("Neodgovarajuci tip parametra");
		}
	}

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

	public List<GenericEntity> getList() {
		return list;
	}
}
