package es.ucm.fdi.is.view;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.usuario.SAUsuario;

public class LoginController {
	
	private SAUsuario modelo;
	
	public LoginController(SAUsuario modelo) {
		this.modelo = modelo;
	}
	
	public void iniciarSesion(String usuario, char[] clave) {
		StringBuilder claveString = new StringBuilder();
		
		for (char c : clave)
			claveString.append(c);
		
		try {
			modelo.iniciarSesion(usuario, claveString.toString());
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addObserver(TiendaObserver observer) {
		modelo.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		modelo.removeObserver(observer);
	}

}
