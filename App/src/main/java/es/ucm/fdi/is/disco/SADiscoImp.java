package es.ucm.fdi.is.disco;

import java.util.ArrayList;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.NotificacionMensaje;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SADiscoImp implements SADisco {
	
	private static SADiscoImp saDisco = null;

	private static DAODisco dao = FactoriaIntegracion.getFactoria().generaDAODisco();
	private ArrayList<TiendaObserver> observadores;
	
	public static SADiscoImp getSADisco() {
		if (saDisco == null)
			saDisco = new SADiscoImp();
		
		return saDisco;
	}
	
	private SADiscoImp() {
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
		dao.descatalogarDisco(disco);
		notifyAll(new Notificacion(NotificacionMensaje.DISCO_BORRADO));
	}

	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		System.out.println(antiguo.getValoracion());
		System.out.println(nuevo.getValoracion());
		dao.actualizarDisco(antiguo, nuevo);
		
		notifyAll(new Notificacion(NotificacionMensaje.DISCO_ACTUALIZADO));
	}

	public void leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException {
		ArrayList<Disco> discos = new ArrayList<Disco>();
		discos.addAll(dao.leerPorGenero(genero));
		notifyAll(new Notificacion(NotificacionMensaje.LEER_POR_GENERO, discos));
	}

	public void crearOferta(Disco disco, OfertaDisco oferta) throws TiendaDatabaseException {
		Disco discoOferta = new Disco(disco, oferta);
		
		dao.actualizarDisco(disco, discoOferta);
		notifyAll(new Notificacion(NotificacionMensaje.NUEVO_PRECIO_DISCO, null));
	}
	
	public void buscarDisco(String titulo) throws TiendaDatabaseException {
		ArrayList<Disco> disco = new ArrayList<Disco>();
		disco.add(dao.leerDisco(titulo));
		if (disco.get(0) != null) // El primer elemento de la lista es distinto a null si se ha encontrado
			notifyAll(new Notificacion(NotificacionMensaje.BUSCAR_DISCO_ENCONTRADO, disco));
		else {
			notifyAll(new Notificacion(NotificacionMensaje.BUSCAR_DISCO_NO_ENCONTRADO, null));
		}
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
