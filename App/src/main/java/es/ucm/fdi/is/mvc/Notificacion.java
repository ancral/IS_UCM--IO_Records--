package es.ucm.fdi.is.mvc;

public enum Notificacion {
	
	SESION_INICIADA("Ha iniciado sesión correctamente"),
	ERROR_SESION("No se ha podido iniciar sesión");
	
	private String mensaje;
	
	Notificacion(String mensaje) {
		this.mensaje = mensaje;
	}
	
	Notificacion() {
		this.mensaje = null;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
}
