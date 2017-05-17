package es.ucm.fdi.is.disco;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SADiscoImp implements SADisco {

	private DAODisco dao;
	private ArrayList<TiendaObserver> observers;
	
	public SADiscoImp()
	{
		this.dao = FactoriaIntegracion.getFactoria().generaDAODisco();
		this.observers = new ArrayList<TiendaObserver>();
	}
	
	public void crearDisco(Disco disco) throws TiendaDatabaseException{
		dao.crearDisco(disco);
	}

	public boolean existeDisco(Disco disco) throws TiendaDatabaseException {
		return dao.existeDisco(disco);
	}

	public void descatalogarDisco(Disco disco) throws TiendaDatabaseException {
		dao.borrarDisco(disco);
	}

	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		dao.actualizarDisco(antiguo, nuevo);
	}

	public List<Disco> leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException {
		return dao.leerPorGenero(genero);
	}

	public void crearOferta(Disco disco, OfertaDisco oferta) throws TiendaDatabaseException {
		Disco discoOferta = new Disco(disco,oferta);
		
		dao.actualizarDisco(disco, discoOferta);
	}

	public List<Disco> leerTodos() throws TiendaDatabaseException {
		return dao.leerTodos();
	}

	public void addObverser(TiendaObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observers.remove(observer);
	}

}
