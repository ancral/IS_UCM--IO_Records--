package es.ucm.fdi.is.mvc;

import java.util.ArrayList;

import es.ucm.fdi.is.usuario.Usuario;

public class Notificacion {
	
	private NotificacionMensaje notificacion;
	private ArrayList<?> discosOpedidos;
	
	/**
	 * Utilizado para informar del usuario que provoca las notificaciones
	 */
	private Usuario usuario;
	
	public Notificacion(NotificacionMensaje notificacion) {
		this(notificacion, null, null);
	}
	
	public Notificacion(NotificacionMensaje notificacion, ArrayList<?> discosOpedidos) {
		this(notificacion, discosOpedidos, null);
	}
	
	public Notificacion(NotificacionMensaje notificacion, ArrayList<?> discosOpedidos, Usuario usuario) {
		this.notificacion = notificacion;
		this.discosOpedidos = discosOpedidos;
		this.usuario = usuario;
	}
	
	public NotificacionMensaje getNotificacion() {
		return this.notificacion;
	}
	
	public ArrayList<?> getDiscosOpedido() {
		return this.discosOpedidos;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Usuario getUsuario() {
		return this.usuario;
	}

}
