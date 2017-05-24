package es.ucm.fdi.is.mvc;

import java.util.ArrayList;

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.usuario.Usuario;

public class Notificacion {
	
	private NotificacionMensaje notificacion;
	private ArrayList<Disco> discos;
	
	/**
	 * Utilizado para informar del usuario que provoca las notificaciones
	 */
	private Usuario usuario;
	
	public Notificacion(NotificacionMensaje notificacion) {
		this(notificacion, null, null);
	}
	
	public Notificacion(NotificacionMensaje notificacion, ArrayList<Disco> discos) {
		this(notificacion, discos, null);
	}
	
	public Notificacion(NotificacionMensaje notificacion, ArrayList<Disco> discos, Usuario usuario) {
		this.notificacion = notificacion;
		this.discos = discos;
		this.usuario = usuario;
	}
	
	public NotificacionMensaje getNotificacion() {
		return this.notificacion;
	}
	
	public ArrayList<Disco> getDiscos() {
		return this.discos;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

}
