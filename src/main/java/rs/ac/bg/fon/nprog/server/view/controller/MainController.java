package rs.ac.bg.fon.nprog.server.view.controller;

import rs.ac.bg.fon.nprog.server.forms.FrmMainServer;
import rs.ac.bg.fon.nprog.server.forms.FrmSettingsDataBase;
import rs.ac.bg.fon.nprog.server.forms.FrmSettingsServer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import rs.ac.bg.fon.nprog.server.server.StartServer;

public class MainController {

	private final FrmMainServer frmMain;
	private static MainController instance;
	private StartServer serverskaNit;

	private MainController(FrmMainServer frmMain) {
		this.frmMain = frmMain;
		addActionListeners();
	}

	public static MainController getInstance() {
		if (instance == null) {
			instance = new MainController(new FrmMainServer());
		}
		return instance;
	}

	private void addActionListeners() {
		frmMain.addBtnStartServerActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				prepareView("START", Color.green, false);
				if (serverskaNit == null || !serverskaNit.isAlive()) {
					serverskaNit = new StartServer();
				}
				serverskaNit.start();
			}
		});

		frmMain.addBtnStopServerActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (serverskaNit != null || serverskaNit.isAlive()) {
					serverskaNit.StopServer();
					prepareView("STOP", Color.red, true);
				}
			}
		});

		frmMain.addBtnJMIServerActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerController serverController = new ServerController(new FrmSettingsServer(frmMain, true));
				serverController.openForm();
			}
		});

		frmMain.addBtnJMIDataBaseActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DataBaseController dataBaseController = new DataBaseController(new FrmSettingsDataBase(frmMain, true));
				dataBaseController.openForm();
			}
		});

	}

	public void openForm() {
		prepareView("STOP", Color.red, true);
		frmMain.setVisible(true);
		frmMain.setLocationRelativeTo(null);
	}

	private void prepareView(String statusText, Color color, boolean status) {
		frmMain.getPanelAdministratori().setVisible(!status);
		frmMain.getLblStatus().setText(statusText);
		frmMain.getLblStatus().setForeground(color);
		frmMain.getBtnStartServer().setEnabled(status);
		frmMain.getBtnStopServer().setEnabled(!status);
	}

	public FrmMainServer getFrmMain() {
		return frmMain;
	}

}
