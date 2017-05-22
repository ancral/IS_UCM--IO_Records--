package es.ucm.fdi.is.usuario;

import java.util.ArrayList;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.NotificacionMensaje;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SAUsuarioImp implements SAUsuario {
	
	private ArrayList<TiendaObserver> observadores;
	private DAOUsuario dao;
	
	private static SAUsuarioImp saUsuario = null;
	
	public static SAUsuarioImp getSAUsuario() {
		if (saUsuario == null)
			saUsuario = new SAUsuarioImp();
		
		return saUsuario;
	}
	
	private SAUsuarioImp() {
		this.observadores = new ArrayList<TiendaObserver>();
		this.dao = FactoriaIntegracion.getFactoria().generaDAOUsuario();
	}
	
	public void iniciarSesion(String usuario, String clave) throws TiendaDatabaseException {
		if (this.dao.comprobarLogin(usuario, clave))
			this.notifyAll(new Notificacion(NotificacionMensaje.SESION_INICIADA));
		else
			this.notifyAll(new Notificacion(NotificacionMensaje.ERROR_SESION));
	}

	public void darseAlta(Usuario usuario) {
		//dao.crearUsuario(usuario, null, null);
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
	
	public void notifyAll(Notificacion notificacion) {
		for (TiendaObserver o : observadores)
			o.notify(notificacion);
	}

}
