package es.ucm.fdi.is.usuario;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SAUsuario extends TiendaObservable {
	
	public void iniciarSesion(String usuario, String clave) throws TiendaDatabaseException;
	public void darseAlta(Usuario usuario) throws TiendaDatabaseException;
	public void darseBaja(Usuario usuario);
	public Usuario verDatosPersonales(Usuario usuario);
	public void asignarNuevoId(Usuario usuario) throws TiendaDatabaseException;
	public void notifyAll(Notificacion notificacion);

}
