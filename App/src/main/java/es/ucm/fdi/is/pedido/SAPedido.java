package es.ucm.fdi.is.pedido;

import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;
import es.ucm.fdi.is.usuario.Usuario;

public interface SAPedido extends TiendaObservable {
	
	public void crearPedido(Pedido pedido) throws TiendaDatabaseException;
	public void devolverPedido(Pedido pedido) throws TiendaDatabaseException;
	public List<Pedido> verPedidosUsuario(Usuario usuario) throws TiendaDatabaseException;
	public void addProducto(Pedido pedido, Disco disco, Usuario usuario) throws TiendaDatabaseException;
	public void eliminarDiscoPedido(Pedido pedido, Disco disco, Usuario usuario) throws TiendaDatabaseException;
	public void modificarPedido(Pedido antiguo, Pedido nuevo) throws TiendaDatabaseException;
	public void finalizarPedido(Pedido pedido, Usuario usuario) throws TiendaDatabaseException;
	public void eliminar(Pedido pedido, Usuario usuario) throws TiendaDatabaseException;
	public void notifyAll(Notificacion notificacion);
	public void verTodosPedidosParaVentas() throws TiendaDatabaseException;
}
