package es.ucm.fdi.is.view;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.usuario.SAUsuario;

public class LoginController {
	
	private static LoginController loginController = null;
	
	private SAUsuario modelo;
	
	
	private LoginController(SAUsuario modelo) {
		this.modelo = modelo;
	}
	
	public static LoginController getLoginController(SAUsuario modelo) {
		if (loginController == null)
			loginController = new LoginController(modelo);
		
		return loginController;
	}
	
	public void iniciarSesion(String usuario, char[] clave) {		
		String claveString = new String(clave);
		
		try {
			modelo.iniciarSesion(usuario, claveString);
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
