package es.ucm.fdi.is.usuario;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SAUsuario extends TiendaObservable {
	
	public void iniciarSesion(String usuario, String clave, Usuario usuarioSesion) throws TiendaDatabaseException;
	public void darseAlta(Usuario usuario);
	public void darseBaja(Usuario usuario);
	public Usuario verDatosPersonales(Usuario usuario);
	public void notifyAll(Notificacion notificacion);

}
