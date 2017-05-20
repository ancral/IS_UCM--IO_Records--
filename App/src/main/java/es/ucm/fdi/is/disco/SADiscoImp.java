package es.ucm.fdi.is.disco;

import java.util.ArrayList;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.NotificacionMensaje;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SADiscoImp implements SADisco {

	private DAODisco dao;
	private ArrayList<TiendaObserver> observadores;
	
	public SADiscoImp() {
		this.dao = FactoriaIntegracion.getFactoria().generaDAODisco();
		this.observadores = new ArrayList<TiendaObserver>();
	}
	
	public void crearDisco(Disco disco) throws TiendaDatabaseException {
		if (!dao.existeDisco(disco)) {
			dao.crearDisco(disco);
			notifyAll(new Notificacion(NotificacionMensaje.DISCO_CREADO));
		}
		else {
			notifyAll(new Notificacion(NotificacionMensaje.DISCO_CREADO_EXISTE));
		}
	}

	public boolean existeDisco(Disco disco) throws TiendaDatabaseException {
		return dao.existeDisco(disco);
	}

	public void descatalogarDisco(Disco disco) throws TiendaDatabaseException {
		dao.borrarDisco(disco);
		notifyAll(new Notificacion(NotificacionMensaje.DISCO_BORRADO));
	}

	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		dao.actualizarDisco(antiguo, nuevo);
		notifyAll(new Notificacion(NotificacionMensaje.DISCO_ACTUALIZADO));
	}

	public void leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		discos.addAll(dao.leerPorGenero(genero));
		notifyAll(new Notificacion(NotificacionMensaje.LEER_POR_GENERO, discos));
	}

	public void crearOferta(Disco disco, OfertaDisco oferta) throws TiendaDatabaseException {
		Disco discoOferta = new Disco(disco,oferta);
		
		dao.actualizarDisco(disco, discoOferta);
	}

	public void leerTodos() throws TiendaDatabaseException {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		discos.addAll(dao.leerTodos());
		notifyAll(new Notificacion(NotificacionMensaje.LEER_TODOS, discos));
	}
	
	public void actualizarValoracion(Disco disco, Float valoracion) throws TiendaDatabaseException {
		Disco discoValoracion = new Disco(disco,valoracion);
		
		dao.actualizarDisco(disco, discoValoracion);
	}
	
	public void addObverser(TiendaObserver observer) {
		observadores.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observadores.remove(observer);
	}

	public void notifyAll(Notificacion notificacion) {
		for (TiendaObserver o : observadores)
			o.notify(notificacion);
	}

	

}
