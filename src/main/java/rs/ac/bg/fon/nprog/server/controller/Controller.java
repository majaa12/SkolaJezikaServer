package rs.ac.bg.fon.nprog.server.controller;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.GenericEntity;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.common.domain.Upis;
import rs.ac.bg.fon.nprog.server.forms.FrmMainServer;
import java.util.ArrayList;
import java.util.List;
import rs.ac.bg.fon.nprog.server.operation.AbstractGenericOperation;
import rs.ac.bg.fon.nprog.server.operation.adresa.VratiAdreseSO;
import rs.ac.bg.fon.nprog.server.operation.grad.VratiGradoveSO;
import rs.ac.bg.fon.nprog.server.operation.jezik.VratiJezikeSO;
import rs.ac.bg.fon.nprog.server.operation.kurs.IzmeniKursSO;
import rs.ac.bg.fon.nprog.server.operation.kurs.PretraziKurseveSO;
import rs.ac.bg.fon.nprog.server.operation.kurs.ZapamtiKursSO;
import rs.ac.bg.fon.nprog.server.operation.administrator.LoginSO;
import rs.ac.bg.fon.nprog.server.operation.polaznik.PretraziPolaznikeSO;
import rs.ac.bg.fon.nprog.server.operation.polaznik.ZapamtiPolaznikaSO;
import rs.ac.bg.fon.nprog.server.operation.profesor.PretraziProfesoreSO;
import rs.ac.bg.fon.nprog.server.operation.profesor.ZapamtiProfesoraSO;
import rs.ac.bg.fon.nprog.server.operation.upis.ObrisiUpisSO;
import rs.ac.bg.fon.nprog.server.operation.upis.PretraziUpiseSO;
import rs.ac.bg.fon.nprog.server.operation.upis.ZapamtiUpisSO;
import rs.ac.bg.fon.nprog.server.threads.ProcessClientsRequests;
import rs.ac.bg.fon.nprog.server.view.controller.MainController;

public class Controller {

	private static Controller instance;
	private final List<ProcessClientsRequests> clients;
	private FrmMainServer mainForm;

	private Controller() {
		clients = new ArrayList<>();
		mainForm = MainController.getInstance().getFrmMain();
	}

	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	public void dodajNit(ProcessClientsRequests client) {
		clients.add(client);
	}

	public void iskljuciNiti() {
		for (ProcessClientsRequests client : clients) {
			client.iskljuciNit();
		}
	}

	public FrmMainServer getMainForm() {
		return mainForm;
	}

	public void setMainForm(FrmMainServer mainForm) {
		this.mainForm = mainForm;
	}

	public GenericEntity login(Administrator a) throws Exception {
		// TODO
		List<Administrator> admins = mainForm.vratiPrijavljeneAdministratore();
		if (admins != null) {
			for (Administrator admin : admins) {
				if (admin.equals(a)) {
					throw new Exception("Korisnik sa ovim nalogom je vec ulogovan!");
				}
			}
		}
		AbstractGenericOperation so = new LoginSO();
		so.execute(a);
		return ((LoginSO) so).getEntity();
	}

	public void logOut(Administrator a) {
		mainForm.odjaviAdministratora(a);
	}

	// POLAZNIK
	public void zapamtiPolaznika(Polaznik p) throws Exception {
		AbstractGenericOperation so = new ZapamtiPolaznikaSO();
		so.execute(p);
	}

	public List<GenericEntity> pretraziPolaznike(Polaznik p) throws Exception {
		AbstractGenericOperation so = new PretraziPolaznikeSO();
		so.execute(p);
		return ((PretraziPolaznikeSO) so).getList();
	}

	// JEZIK
	public List<GenericEntity> vratiJezike(Jezik j) throws Exception {
		AbstractGenericOperation so = new VratiJezikeSO();
		so.execute(j);
		return ((VratiJezikeSO) so).getList();
	}

	// PROFESOR
	public void zapamtiProfesora(Profesor profesor) throws Exception {
		AbstractGenericOperation so = new ZapamtiProfesoraSO();
		so.execute(profesor);
	}

	public List<GenericEntity> pretraziProfesore(Profesor p) throws Exception {
		AbstractGenericOperation so = new PretraziProfesoreSO();
		so.execute(p);
		return ((PretraziProfesoreSO) so).getList();
	}

	// GRAD
	public List<GenericEntity> vratiGradove(Grad grad) throws Exception {
		AbstractGenericOperation so = new VratiGradoveSO();
		so.execute(grad);
		return ((VratiGradoveSO) so).getList();
	}

	// ADRESA
	public List<GenericEntity> vratiAdrese(Adresa a) throws Exception {
		AbstractGenericOperation so = new VratiAdreseSO();
		so.execute(a);
		return ((VratiAdreseSO) so).getList();
	}

	// KURS
	public void zapamtiKurs(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new ZapamtiKursSO();
		so.execute(kurs);
	}

	public List<GenericEntity> pretraziKurseve(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new PretraziKurseveSO();
		so.execute(kurs);
		return ((PretraziKurseveSO) so).getList();
	}

	public void izmeniKurs(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new IzmeniKursSO();
		so.execute(kurs);
	}

	// UPIS
	public void zapamtiUpis(Upis upis) throws Exception {
		AbstractGenericOperation so = new ZapamtiUpisSO();
		so.execute(upis);
	}

	public List<GenericEntity> pretraziUpise(Upis upis) throws Exception {
		AbstractGenericOperation so = new PretraziUpiseSO();
		so.execute(upis);
		return ((PretraziUpiseSO) so).getList();
	}

	public void obrisiUpis(Upis upis) throws Exception {
		AbstractGenericOperation so = new ObrisiUpisSO();
		so.execute(upis);
	}
}
