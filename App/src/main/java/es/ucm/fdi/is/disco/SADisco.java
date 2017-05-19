package es.ucm.fdi.is.disco;

import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SADisco extends TiendaObservable {
	
	public void crearDisco(Disco disco) throws TiendaDatabaseException;
	public boolean existeDisco(Disco disco) throws TiendaDatabaseException;
	public void descatalogarDisco(Disco disco) throws TiendaDatabaseException;
	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException;
	public List<Disco> leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException;
	public void crearOferta(Disco disco, OfertaDisco oferta) throws TiendaDatabaseException;
	public List<Disco> leerTodos() throws TiendaDatabaseException;
	public void notifyAll(Notificacion notificacion);

}
