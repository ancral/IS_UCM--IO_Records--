package es.ucm.fdi.is.disco;

import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabaseException;

public interface DAODisco {

	public void crearDisco(Disco disco) throws TiendaDatabaseException;
	public Disco leerDisco(String titulo) throws TiendaDatabaseException;
	public List<Disco> leerTodos() throws TiendaDatabaseException;
	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException;
	public void borrarDisco(Disco disco) throws TiendaDatabaseException;
	public List<Disco> leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException;
	public boolean existeDisco(Disco disco) throws TiendaDatabaseException;
}
