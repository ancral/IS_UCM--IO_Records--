package es.ucm.fdi.is.disco;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SADisco extends TiendaObservable {
	
	public void crearDisco(Disco disco) throws TiendaDatabaseException;
	public boolean existeDisco(Disco disco) throws TiendaDatabaseException;
	public void descatalogarDisco(Disco disco) throws TiendaDatabaseException;
	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException;
	public void leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException;
	public void crearOferta(Disco disco, OfertaDisco oferta) throws TiendaDatabaseException;
	public void leerTodos() throws TiendaDatabaseException;
	public void actualizarValoracion(Disco disco, Float valoracion) throws TiendaDatabaseException;
	public void notifyAll(Notificacion notificacion);

}
