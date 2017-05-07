package es.ucm.fdi.is.launcher;

import java.sql.ResultSet;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;

public class Main {

	public static void main(String[] args) throws TiendaDatabaseException {
		TiendaDatabase.iniciar();
		ResultSet res = TiendaDatabase.ejecutarQuery("SELECT * FROM usuarios");
		
		System.out.println(TiendaDatabase.getValorColumna(res, "nombre"));
		System.out.println(TiendaDatabase.getValorColumna(res, "edad"));
		
		TiendaDatabase.cerrar();
		
	}
	
}
