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

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

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

/**
 * Klasa koja predstavlja kontrolera koji istancira konkretne sistemske
 * operacije i poziva na njihovo izvrsenje.
 * 
 * @author Maja
 * @version 0.1
 */
public class Controller {

	/**
	 * Unarna veza kontrolera sa samim sobom - Singleton pattern. Staticki privatni
	 * atribut instance klase Controller.
	 */
	private static Controller instance;
	/**
	 * Lista klijentskih niti tipa List{@literal <ProcessClientsRequests>}.
	 */
	private final List<ProcessClientsRequests> clients;
	/**
	 * istanca glavne serverske forme tipa FrmMainServer.
	 */
	private FrmMainServer mainForm;

	/**
	 * Besparametarski privatni konstruktor koji istancira listu klijentskih niti i
	 * atributu mainForm pridruzuje glavnu serversku formu istanciranu u njenom
	 * kontroleru.
	 */
	private Controller() {
		clients = new ArrayList<>();
		mainForm = MainController.getInstance().getFrmMain();
	}

	/**
	 * Vraca jedinstvenu instancu kontrolera.
	 * 
	 * @return instance kao instanca tipa Controller
	 */
	public static Controller getInstance() {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
	}

	/**
	 * Metoda za dodavanje nove klijentske niti u listu klijenata.
	 * 
	 * @param client klijentska nit klase ProcessClientsRequests.
	 */
	public void dodajNit(ProcessClientsRequests client) {
		clients.add(client);
	}

	/**
	 * Metoda koja prolazi kroz listu klijentskih niti i jednu po jednu prekida.
	 */
	public void iskljuciNiti() {
		for (ProcessClientsRequests client : clients) {
			client.iskljuciNit();
		}
	}

	/**
	 * Vraca glavnu serversku formu.
	 * 
	 * @return mainForm klase FrmMainServer.
	 */
	public FrmMainServer getMainForm() {
		return mainForm;
	}

	/**
	 * Postavlja glavnu serversku formu.
	 * 
	 * @param mainForm Glavna serverska forma klase FrmMainServer.
	 */
	public void setMainForm(FrmMainServer mainForm) {
		this.mainForm = mainForm;
	}

	/**
	 * Prijavljivanje administratora sa prosledjenim kredencijalima na sistem.
	 * 
	 * @param a Objekat klase Administrator
	 * @return GenericEntity Administrator koji se prijavljuje na sistem.
	 * @throws Exception ukoliko je administrator sa prosledjenim kredencijalima vec
	 *                   prijavljen na sistem
	 */
	public GenericEntity login(Administrator a) throws Exception {
		// TODO
		List<Administrator> admins = mainForm.vratiPrijavljeneAdministratore();
		if (admins != null) {
			for (Administrator admin : admins) {
				if (admin.equals(a)) {
					throw new Exception("Administrator sa ovim nalogom je vec ulogovan!");
				}
			}
		}
		AbstractGenericOperation so = new LoginSO();
		so.execute(a);
		return ((LoginSO) so).getEntity();
	}

	/**
	 * Odjavljuje administratora sa sistema.
	 * 
	 * @param a Objekat klase Administrator koji se odjavljuje sa sistema.
	 */
	public void logOut(Administrator a) {
		mainForm.odjaviAdministratora(a);
	}

	// POLAZNIK
	/**
	 * Istancira i poziva sistemsku operaciju za cuvanje novog polaznika u bazi
	 * podataka.
	 * 
	 * @param p Objekat klase Polaznik koga treba sacuvati u bazi.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void zapamtiPolaznika(Polaznik p) throws Exception {
		AbstractGenericOperation so = new ZapamtiPolaznikaSO();
		so.execute(p);
	}

	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih polaznika iz baze
	 * podataka koji odgovaraju kriterijumima pretrage.
	 * 
	 * @param p Objekat klase Polaznik na osnovu kog se vrsi pretraga.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> pretraziPolaznike(Polaznik p) throws Exception {
		AbstractGenericOperation so = new PretraziPolaznikeSO();
		so.execute(p);
		return ((PretraziPolaznikeSO) so).getList();
	}

	// JEZIK
	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih jezika iz baze
	 * podataka.
	 * 
	 * @param j Objekat klase Jezik.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> vratiJezike(Jezik j) throws Exception {
		AbstractGenericOperation so = new VratiJezikeSO();
		so.execute(j);
		return ((VratiJezikeSO) so).getList();
	}

	// PROFESOR
	/**
	 * Istancira i poziva sistemsku operaciju za cuvanje novog profesora u bazi
	 * podataka.
	 * 
	 * @param profesor Objekat klase Profesor koga treba sacuvati u bazi.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void zapamtiProfesora(Profesor profesor) throws Exception {
		AbstractGenericOperation so = new ZapamtiProfesoraSO();
		so.execute(profesor);
	}

	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih profesora iz baze
	 * podataka koji odgovaraju kriterijumima pretrage.
	 * 
	 * @param p Objekat klase Profesor na osnovu kog se vrsi pretraga.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> pretraziProfesore(Profesor p) throws Exception {
		AbstractGenericOperation so = new PretraziProfesoreSO();
		so.execute(p);
		return ((PretraziProfesoreSO) so).getList();
	}

	// GRAD
	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih gradova iz baze
	 * podataka.
	 * 
	 * @param grad Objekat klase Grad.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> vratiGradove(Grad grad) throws Exception {
		AbstractGenericOperation so = new VratiGradoveSO();
		so.execute(grad);
		return ((VratiGradoveSO) so).getList();
	}

	// ADRESA
	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih adresa odredjenog
	 * grada iz baze podataka.
	 * 
	 * @param a Objekat klase Adresa.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> vratiAdrese(Adresa a) throws Exception {
		AbstractGenericOperation so = new VratiAdreseSO();
		so.execute(a);
		return ((VratiAdreseSO) so).getList();
	}

	// KURS
	/**
	 * Istancira i poziva sistemsku operaciju za cuvanje novog kursa u bazi
	 * podataka.
	 * 
	 * @param kurs Objekat klase Kurs koga treba sacuvati u bazi.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void zapamtiKurs(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new ZapamtiKursSO();
		so.execute(kurs);
	}

	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih kurseva iz baze
	 * podataka koji odgovaraju kriterijumima pretrage.
	 * 
	 * @param kurs Objekat klase Kurs na osnovu kog se vrsi pretraga.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> pretraziKurseve(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new PretraziKurseveSO();
		so.execute(kurs);
		return ((PretraziKurseveSO) so).getList();
	}

	/**
	 * Istancira i poziva sistemsku operaciju za izmenu postojeceg kursa u bazi
	 * podataka.
	 * 
	 * @param kurs Objekat klase Kurs koga treba izmeniti u bazi.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void izmeniKurs(Kurs kurs) throws Exception {
		AbstractGenericOperation so = new IzmeniKursSO();
		so.execute(kurs);
	}

	// UPIS
	/**
	 * Istancira i poziva sistemsku operaciju za cuvanje novog upisa u bazi
	 * podataka.
	 * 
	 * @param upis Objekat klase Upis koga treba sacuvati u bazi.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void zapamtiUpis(Upis upis) throws Exception {
		AbstractGenericOperation so = new ZapamtiUpisSO();
		so.execute(upis);
	}

	/**
	 * Istancira i poziva sistemsku operaciju za vracanje svih upisa iz baze
	 * podataka koji odgovaraju kriterijumima pretrage.
	 * 
	 * @param upis Objekat klase Upis na osnovu kog se vrsi pretraga.
	 * @return lista tipa List{@literal <GenericEntity>} koja predstavlja listu
	 *         generickih objekata koje operacija treba da vrati.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public List<GenericEntity> pretraziUpise(Upis upis) throws Exception {
		AbstractGenericOperation so = new PretraziUpiseSO();
		so.execute(upis);
		return ((PretraziUpiseSO) so).getList();
	}

	/**
	 * Istancira i poziva sistemsku operaciju za brisanje postojeceg upisa iz baze
	 * podataka.
	 * 
	 * @param upis Objekat klase Upis koga treba obrisati iz baze.
	 * @throws Exception ukoliko dodje do greske tokom izvrsavanja sistemske
	 *                   operacije.
	 */
	public void obrisiUpis(Upis upis) throws Exception {
		AbstractGenericOperation so = new ObrisiUpisSO();
		so.execute(upis);
		sacuvajUJsonFile(upis);
	}

	private void sacuvajUJsonFile(Upis upis) {
		try (FileWriter file = new FileWriter("obrisani_upisi.json", true)) {

			JsonObject obj = new JsonObject();
			
			obj.addProperty("Datum upisa", upis.getDatumUpis().toString());
			obj.addProperty("Polaznik", upis.getPolaznik().toString());
			obj.addProperty("Kurs", upis.getTerminKursa().getKurs().getNaziv());
			obj.addProperty("Nivo kursa", upis.getTerminKursa().getKurs().getNivo().toString());
			obj.addProperty("Tip kursa", upis.getTerminKursa().getKurs().getTipKursa().toString());
			obj.addProperty("Termin kursa", upis.getTerminKursa().toString());
		
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			
			gson.toJson(obj, file);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
