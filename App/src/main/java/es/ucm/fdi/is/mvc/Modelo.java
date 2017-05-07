package es.ucm.fdi.is.mvc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;

public class Modelo implements TiendaObservable {
	private ArrayList<TiendaObserver> observadores;
	
	public Modelo() {
		this.observadores = new ArrayList<TiendaObserver>();
	}
	
	public void iniciarSesion(String usuario, String clave) throws TiendaDatabaseException {
		String sql = "SELECT * FROM usuarios WHERE nombre = '" + usuario + "'"; /*+ "' AND " +
					  "clave = '" + clave + "'";*/
		
		ResultSet res = TiendaDatabase.ejecutarQuery(sql);
		
		try {
			if (res.next())
				System.out.println(res.getString("nombre"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addObverser(TiendaObserver observer) {
		this.observadores.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		this.observadores.remove(observer);
	}
	
	public void notifyAll(String mensaje) {
		for (TiendaObserver o : observadores)
			o.notify(mensaje);
	}

}
