package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.usuario.SAUsuario;

public class LoginController {
	
	private static LoginController loginController = null;
	
	private static SAUsuario modelo = FactoriaSA.getFactoria().generaSAUsuario();
	
	
	private LoginController() {}
	
	public static LoginController getLoginController() {
		if (loginController == null)
			loginController = new LoginController();
		
		return loginController;
	}
	
	public void iniciarSesion(String usuario, char[] clave) {		
		String claveString = new String(clave);
		
		try {			
			/*
			 * Creamos un cliente vacío que luego se inicializará correctamente
			 * cuando se inicie sesión y se pase la notificación a la vista
			 */
			
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
