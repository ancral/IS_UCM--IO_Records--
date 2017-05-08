package es.ucm.fdi.is.usuario;

import java.util.ArrayList;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SAUsuarioImp implements SAUsuario {
	
	private ArrayList<TiendaObserver> observadores;
	private DAOUsuario dao;
	
	public SAUsuarioImp() {
		this.observadores = new ArrayList<TiendaObserver>();
		this.dao = new DAOUsuarioImp();
	}
	
	public void iniciarSesion(String usuario, String clave) throws TiendaDatabaseException {
		if (this.dao.comprobarLogin(usuario, clave))
			this.notifyAll("Ha iniciado sesión correctamente");
		else
			this.notifyAll("No se ha podido iniciar sesión");
	}

	public void darseAlta(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public void darseBaja(Usuario usuario) {
		// TODO Auto-generated method stub
		
	}

	public Usuario verDatosPersonales(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
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
