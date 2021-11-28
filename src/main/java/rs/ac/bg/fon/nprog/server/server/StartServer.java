package rs.ac.bg.fon.nprog.server.server;

import rs.ac.bg.fon.nprog.server.controller.Controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Properties;
import rs.ac.bg.fon.nprog.server.threads.ProcessClientsRequests;

public class StartServer extends Thread {

	ServerSocket ss;
	boolean end = false;

	@Override
	public void run() {

		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("config/server.config.properties"));
			ss = new ServerSocket(Integer.parseInt(properties.getProperty("port")));

			while (!end) {
				System.out.println("Waiting for connection...");
				Socket socket = ss.accept();
				System.out.println("Connected!");
				handleClient(socket);
			}
		} catch (SocketException ex) {
			System.out.println(ex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}

	}

	private void handleClient(Socket socket) {
		ProcessClientsRequests client = new ProcessClientsRequests(socket);
		client.start();
		Controller.getInstance().dodajNit(client);
	}

	public void StopServer() {
		this.end = true;
		Controller.getInstance().iskljuciNiti();
		try {
			this.ss.close();
		} catch (IOException ex) {
			// ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
		System.out.println("Server iskljucen!");
	}
}
