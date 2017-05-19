package es.ucm.fdi.is.disco;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SADiscoImp implements SADisco {

	private DAODisco dao;
	private ArrayList<TiendaObserver> observadores;
	
	public SADiscoImp()
	{
		this.dao = FactoriaIntegracion.getFactoria().generaDAODisco();
		this.observadores = new ArrayList<TiendaObserver>();
	}
	
	public void crearDisco(Disco disco) throws TiendaDatabaseException {
		if (!dao.existeDisco(disco)) {
			dao.crearDisco(disco);
			notifyAll(Notificacion.DISCO_CREADO);
		}
		else {
			notifyAll(Notificacion.DISCO_CREADO_EXISTE);
		}
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
		observadores.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observadores.remove(observer);
	}

	@Override
	public void notifyAll(Notificacion notificacion) {
		for (TiendaObserver o : observadores)
			o.notify(notificacion);
	}

}
