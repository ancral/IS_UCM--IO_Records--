package es.ucm.fdi.is.pedido;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.NotificacionMensaje;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.usuario.Usuario;

public class SAPedidoImp implements SAPedido {

	private static DAOPedido dao = FactoriaIntegracion.getFactoria().generaDAOPedido();
	private ArrayList<TiendaObserver> observers;
	
	private static SAPedidoImp saPedido = null;
	
	public static SAPedidoImp getSAPedido() {
		if (saPedido == null)
			saPedido = new SAPedidoImp();
		
		return saPedido;
	}
	
	private SAPedidoImp()
	{
		this.observers = new ArrayList<TiendaObserver>();
	}
	
	public void crearPedido(Pedido pedido) throws TiendaDatabaseException {
		dao.crearPedido(pedido);
	}

	public void devolverPedido(Pedido pedido) throws TiendaDatabaseException {
		dao.eliminarPedido(pedido);
	}

	public List<Pedido> verPedidosUsuario(Usuario usuario) throws TiendaDatabaseException {
		return dao.verPedidosUsuario(usuario);
	}

	public void addProducto(Pedido pedido, Disco disco, Usuario usuario) throws TiendaDatabaseException {
		if (dao.existeDisco(pedido, disco)) {
			notifyAll(new Notificacion(NotificacionMensaje.ERROR_DISCO_YA_CARRITO, null, usuario));
		}
		else {
			dao.meterDisco(pedido, disco); // Añadimos el disco al pedido que se le pasa por parámetro (se guarda en la BD)
			pedido.meterDisco(disco); // Añadimos el disco al pedido que se mantiene en memoria
			usuario.setPedido(pedido); // Asignamos el pedido al usuario
			this.notifyAll(new Notificacion(NotificacionMensaje.ANYADIR_CARRITO, null, usuario));
		}
	}
	
	public void eliminarDiscoPedido(Pedido pedido, Disco disco, Usuario usuario) throws TiendaDatabaseException {
		dao.eliminarDiscoPedido(pedido, disco);
		pedido.quitarDisco(disco);
		this.notifyAll(new Notificacion(NotificacionMensaje.BORRADO_CARRITO, null, usuario));
	}
	
	public void finalizarPedido(Pedido pedido, Usuario usuario) throws TiendaDatabaseException {
		dao.finalizarPedido(pedido);
		pedido.setFinalizado(1);
		usuario.getPedidos().add(pedido);
		this.notifyAll(new Notificacion(NotificacionMensaje.CARRITO_FINALIZADO, null, usuario));
	}

	public void modificarPedido(Pedido antiguo, Pedido nuevo) throws TiendaDatabaseException  {
		dao.actualizarPedido(antiguo, nuevo);
	}

	public void eliminar(Pedido pedido, Usuario usu) throws TiendaDatabaseException  {
		dao.eliminarPedido(pedido); // Se elimina de la base de datos el pedido
		usu.getPedidos().remove(pedido); // Eliminamos el pedido de la lista que mantiene el usuario
		this.notifyAll(new Notificacion(NotificacionMensaje.PEDIDO_ELIMINADO, null, usu));
	}

	public void addObverser(TiendaObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observers.remove(observer);
	}

	public void notifyAll(Notificacion notificacion) {
		for (TiendaObserver o : this.observers) {
			o.notify(notificacion);
		}
	}

}
