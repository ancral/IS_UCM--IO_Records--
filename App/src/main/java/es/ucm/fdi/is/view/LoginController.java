package es.ucm.fdi.is.view;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Modelo;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class LoginController {
	
	private Modelo modelo;
	
	public LoginController(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public void iniciarSesion(String usuario, String clave) {
		try {
			modelo.iniciarSesion(usuario, clave);
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
