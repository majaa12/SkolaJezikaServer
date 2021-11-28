package rs.ac.bg.fon.nprog.server.threads;

import rs.ac.bg.fon.nprog.server.controller.Controller;
import rs.ac.bg.fon.nprog.common.domain.Administrator;
import rs.ac.bg.fon.nprog.common.domain.Adresa;
import rs.ac.bg.fon.nprog.common.domain.Grad;
import rs.ac.bg.fon.nprog.common.domain.Jezik;
import rs.ac.bg.fon.nprog.common.domain.Kurs;
import rs.ac.bg.fon.nprog.common.domain.Polaznik;
import rs.ac.bg.fon.nprog.common.domain.Profesor;
import rs.ac.bg.fon.nprog.common.domain.Upis;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import rs.ac.bg.fon.nprog.common.transfer.Receiver;
import rs.ac.bg.fon.nprog.common.transfer.Request;
import rs.ac.bg.fon.nprog.common.transfer.Response;
import rs.ac.bg.fon.nprog.common.transfer.Sender;

public class ProcessClientsRequests extends Thread {

	Socket socket;
	private boolean end = false;

	public ProcessClientsRequests(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);

		while (!end) {
			try {
				Request request = (Request) receiver.receive();
				Response response = new Response();

				try {
					switch (request.getOperation()) {
					case LOGIN:
						Administrator admin = (Administrator) request.getArgument();
						Administrator a = (Administrator) Controller.getInstance().login(admin);
						response.setResult(a);
						Controller.getInstance().getMainForm().prijaviAdministratora(a);
						break;
					case ZAPAMTI_POLAZNIKA:
						Controller.getInstance().zapamtiPolaznika((Polaznik) request.getArgument());
						response.setResult("Uspesno sacuvan polaznik!");
						break;
					case PRETRAZI_POLAZNIKE:
						response.setResult(
						Controller.getInstance().pretraziPolaznike((Polaznik) request.getArgument()));
						break;
					case VRATI_JEZIKE:
						response.setResult(Controller.getInstance().vratiJezike((Jezik) request.getArgument()));
						break;
					case ZAPAMTI_PROFESORA:
						Controller.getInstance().zapamtiProfesora((Profesor) request.getArgument());
						response.setResult("Uspesno sacuvan profesor!");
						break;
					case PRETRAZI_PROFESORE:
						response.setResult(
						Controller.getInstance().pretraziProfesore((Profesor) request.getArgument()));
						break;
					case VRATI_GRADOVE:
						response.setResult(Controller.getInstance().vratiGradove((Grad) request.getArgument()));
						break;
					case VRATI_ADRESE:
						response.setResult(Controller.getInstance().vratiAdrese((Adresa) request.getArgument()));
						break;
					case SACUVAJ_KURS:
						Controller.getInstance().zapamtiKurs((Kurs) request.getArgument());
						response.setResult("Uspesno sacuvan kurs!");
						break;
					case PRETRAZI_KURSEVE:
						response.setResult(Controller.getInstance().pretraziKurseve((Kurs) request.getArgument()));
						break;
					case IZMENI_KURS:
						Controller.getInstance().izmeniKurs((Kurs) request.getArgument());
						response.setResult("Uspesno izmenjen kurs!");
						break;
					case ZAPAMTI_UPIS:
						Controller.getInstance().zapamtiUpis((Upis) request.getArgument());
						response.setResult("Uspesno sacuvan upis!");
						break;
					case PRETRAZI_UPISE:
						response.setResult(Controller.getInstance().pretraziUpise((Upis) request.getArgument()));
						break;
					case OBRISI_UPIS:
						Controller.getInstance().obrisiUpis((Upis) request.getArgument());
						response.setResult("Uspesno obrisan upis!");
						break;
					case LOGOUT:
						Controller.getInstance().logOut((Administrator) request.getArgument());// izbacuje iz tabele
						iskljuciNit();
						break;
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					response.setException(ex);
				}
				sender.send(response);
			} catch (Exception ex) {
				Logger.getLogger(ProcessClientsRequests.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void iskljuciNit() {
		end = true;
		try {
			socket.close();
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
		System.out.println("Iskljucena nit klijenta!");
	}
}
