package es.ucm.fdi.is.usuario;

import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SAUsuario extends TiendaObservable {
	
	public void darseAlta(Usuario usuario);
	public void darseBaja(Usuario usuario);
	public Usuario verDatosPersonales(Usuario usuario);

}
