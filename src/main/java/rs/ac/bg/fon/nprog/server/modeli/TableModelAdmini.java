package rs.ac.bg.fon.nprog.server.modeli;

import rs.ac.bg.fon.nprog.common.domain.Administrator;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class TableModelAdmini extends AbstractTableModel {

	private final ArrayList<Administrator> lista;
	private final String[] kolone = new String[] { "ID administratora", "Ime", "Prezime" };
	private final Class[] klase = new Class[] { Long.class, String.class, String.class };

	public TableModelAdmini() {
		lista = new ArrayList<>();
	}

	public TableModelAdmini(ArrayList<Administrator> lista) {
		this.lista = lista;
	}

	@Override
	public int getRowCount() {
		if (lista == null) {
			return 0;
		}
		return lista.size();
	}

	@Override
	public int getColumnCount() {
		return kolone.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Administrator a = lista.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return a.getIDAdministratora();
		case 1:
			return a.getIme();
		case 2:
			return a.getPrezime();
		default:
			return "n/a";
		}
	}

	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		return klase[columnIndex];
	}

	public void loginAdmin(Administrator a) {
		lista.add(a);
		fireTableDataChanged();
	}

	public void logoutAdmin(Administrator a) {
		lista.remove(a);
		fireTableDataChanged();
	}

	public ArrayList<Administrator> vratiAdmine() {
		return lista;
	}
}
