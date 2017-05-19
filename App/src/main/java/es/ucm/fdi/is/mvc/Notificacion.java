package es.ucm.fdi.is.mvc;

import java.util.ArrayList;

import es.ucm.fdi.is.disco.Disco;

public class Notificacion {
	
	private NotificacionMensaje notificacion;
	private ArrayList<Disco> discos;
	
	public Notificacion(NotificacionMensaje notificacion) {
		this.notificacion = notificacion;
		this.discos = null;
	}
	
	public Notificacion(NotificacionMensaje notificacion, ArrayList<Disco> discos) {
		this.notificacion = notificacion;
		this.discos = discos;
	}
	
	public NotificacionMensaje getNotificacion() {
		return this.notificacion;
	}
	
	public ArrayList<Disco> getDiscos() {
		return this.discos;
	}

}
