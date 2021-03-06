package rs.ac.bg.fon.nprog.server.forms;

import javax.swing.JButton;
import javax.swing.JDialog;
import java.awt.event.ActionListener;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class FrmSettingsDataBase extends JDialog {

	/**
	 * Creates new form FrmSettingsDataBase
	 */
	public FrmSettingsDataBase(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		JPanel = new javax.swing.JPanel();
		lblURL = new javax.swing.JLabel();
		txtURL = new javax.swing.JTextField();
		lblUsername = new javax.swing.JLabel();
		txtUsername = new javax.swing.JTextField();
		lblPassword = new javax.swing.JLabel();
		btnSave = new javax.swing.JButton();
		btnEdit = new javax.swing.JButton();
		txtPassword = new javax.swing.JPasswordField();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Settings - DataBase");

		JPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Konfiguracija baze"));

		lblURL.setText("URL:");

		lblUsername.setText("Username:");

		lblPassword.setText("Password:");

		btnSave.setText("Save");

		btnEdit.setText("Edit");

		javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
		JPanel.setLayout(JPanelLayout);
		JPanelLayout.setHorizontalGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(JPanelLayout.createSequentialGroup().addContainerGap().addGroup(JPanelLayout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(JPanelLayout.createSequentialGroup()
								.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblUsername).addComponent(lblURL))
								.addGap(18, 18, 18)
								.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtURL, javax.swing.GroupLayout.DEFAULT_SIZE, 278,
												Short.MAX_VALUE)
										.addComponent(txtUsername)))
						.addGroup(JPanelLayout.createSequentialGroup().addComponent(lblPassword).addGap(18, 18, 18)
								.addComponent(txtPassword))
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								JPanelLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(btnEdit)
										.addGap(18, 18, 18).addComponent(btnSave)))
						.addContainerGap()));
		JPanelLayout.setVerticalGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(JPanelLayout.createSequentialGroup().addGap(20, 20, 20)
						.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblURL).addComponent(txtURL, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblUsername)
								.addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblPassword)
								.addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(18, 18, 18)
						.addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(btnSave).addComponent(btnEdit))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap().addComponent(JPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap()
						.addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	// Variables declaration - do not modify
	private javax.swing.JPanel JPanel;
	private javax.swing.JButton btnEdit;
	private javax.swing.JButton btnSave;
	private javax.swing.JLabel lblPassword;
	private javax.swing.JLabel lblURL;
	private javax.swing.JLabel lblUsername;
	private javax.swing.JPasswordField txtPassword;
	private javax.swing.JTextField txtURL;
	private javax.swing.JTextField txtUsername;
	// End of variables declaration

	public JButton getBtnEdit() {
		return btnEdit;
	}

	public JButton getBtnSave() {
		return btnSave;
	}

	public JPasswordField getTxtPassword() {
		return txtPassword;
	}

	public JTextField getTxtURL() {
		return txtURL;
	}

	public JTextField getTxtUsername() {
		return txtUsername;
	}

	public void addBtnEditActionListener(ActionListener actionListener) {
		btnEdit.addActionListener(actionListener);
	}

	public void addBtnSaveActionListener(ActionListener actionListener) {
		btnSave.addActionListener(actionListener);
	}

}
