package es.ucm.fdi.is.usuario;

import java.util.ArrayList;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.NotificacionMensaje;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SAUsuarioImp implements SAUsuario {
	
	private ArrayList<TiendaObserver> observadores;
	private static DAOUsuario dao = FactoriaIntegracion.getFactoria().generaDAOUsuario();
	
	private static SAUsuarioImp saUsuario = null;
	
	public static SAUsuarioImp getSAUsuario() {
		if (saUsuario == null)
			saUsuario = new SAUsuarioImp();
		
		return saUsuario;
	}
	
	private SAUsuarioImp() {
		this.observadores = new ArrayList<TiendaObserver>();
	}
	
	public void iniciarSesion(String usuario, String clave, Usuario usuarioSesion) throws TiendaDatabaseException {
		if (dao.comprobarLogin(usuario, clave, usuarioSesion))
			this.notifyAll(new Notificacion(NotificacionMensaje.SESION_INICIADA, null, usuarioSesion));
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
