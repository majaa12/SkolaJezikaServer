package rs.ac.bg.fon.nprog.server.view.controller;

import rs.ac.bg.fon.nprog.server.forms.FrmSettingsServer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ServerController {

	private final FrmSettingsServer frmSettingServer;
	private final Properties properties;

	public ServerController(FrmSettingsServer frmSettingServer) {
		this.frmSettingServer = frmSettingServer;
		this.properties = new Properties();
		addActionListener();
	}

	private void addActionListener() {

		frmSettingServer.addBtnSaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sacuvaj();

			}

			private void sacuvaj() {
				String port = frmSettingServer.getTxtPort().getText().trim();
				int p = Integer.parseInt(port);
				if (p <= 0) {
					JOptionPane.showMessageDialog(frmSettingServer, "Port ne sme biti negativan!", "Greska",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				properties.setProperty("port", port);
				try {
					properties.store(new FileOutputStream("config/server.config.properties"), null);
					JOptionPane.showMessageDialog(frmSettingServer, "Uspesno promenjena podesavanja porta");
					frmSettingServer.dispose();
				} catch (FileNotFoundException ex) {
					Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});

		frmSettingServer.addBtnEditActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmSettingServer.getTxtPort().setEnabled(true);
				frmSettingServer.getBtnSave().setEnabled(true);
				frmSettingServer.getBtnEdit().setEnabled(false);
			}
		});

	}

	public void openForm() {
		frmSettingServer.setLocationRelativeTo(null);
		try {
			properties.load(new FileInputStream("config/server.config.properties"));
		} catch (Exception ex) {
			Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
		}
		prepareView();
		frmSettingServer.setVisible(true);
	}

	private void prepareView() {
		frmSettingServer.getBtnSave().setEnabled(false);
		frmSettingServer.getTxtPort().setEnabled(false);

		String port = properties.getProperty("port");
		frmSettingServer.getTxtPort().setText(port);
	}
}
