package rs.ac.bg.fon.nprog.server.view.controller;

import rs.ac.bg.fon.nprog.server.forms.FrmSettingsDataBase;
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

public class DataBaseController {

	private final FrmSettingsDataBase frmSettingsDataBase;
	private final Properties properties;

	public DataBaseController(FrmSettingsDataBase frmSettingsDataBase) {
		this.frmSettingsDataBase = frmSettingsDataBase;
		this.properties = new Properties();
		addActionListener();
	}

	private void addActionListener() {

		frmSettingsDataBase.addBtnEditActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frmSettingsDataBase.getTxtURL().setEnabled(true);
				frmSettingsDataBase.getTxtUsername().setEnabled(true);
				frmSettingsDataBase.getTxtPassword().setEnabled(true);
				frmSettingsDataBase.getBtnSave().setEnabled(true);
				frmSettingsDataBase.getBtnEdit().setEnabled(false);
			}
		});

		frmSettingsDataBase.addBtnSaveActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sacuvaj();
			}

			private void sacuvaj() {
				String url = frmSettingsDataBase.getTxtURL().getText().trim();
				String username = frmSettingsDataBase.getTxtUsername().getText().trim();
				String password = String.copyValueOf(frmSettingsDataBase.getTxtPassword().getPassword());

				try {
					properties.setProperty("url", url);
					properties.store(new FileOutputStream("config/db.config.properties"), null);

					properties.setProperty("username", username);
					properties.store(new FileOutputStream("config/db.config.properties"), null);

					properties.setProperty("password", password);
					properties.store(new FileOutputStream("config/db.config.properties"), null);
					JOptionPane.showMessageDialog(frmSettingsDataBase, "Uspesno promenjena podesavanja baze");
					frmSettingsDataBase.dispose();
				} catch (FileNotFoundException ex) {
					Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
				} catch (IOException ex) {
					Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		});
	}

	public void openForm() {
		frmSettingsDataBase.setLocationRelativeTo(null);
		try {
			properties.load(new FileInputStream("config/db.config.properties"));
		} catch (Exception ex) {
			Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
		}
		prepareView();
		frmSettingsDataBase.setVisible(true);
	}

	private void prepareView() {
		frmSettingsDataBase.getBtnSave().setEnabled(false);
		frmSettingsDataBase.getTxtPassword().setEnabled(false);
		frmSettingsDataBase.getTxtURL().setEnabled(false);
		frmSettingsDataBase.getTxtUsername().setEnabled(false);

		String url = properties.getProperty("url");
		String username = properties.getProperty("username");
		String password = properties.getProperty("password");
		frmSettingsDataBase.getTxtURL().setText(url);
		frmSettingsDataBase.getTxtUsername().setText(username);
		frmSettingsDataBase.getTxtPassword().setText(password);
	}
}
