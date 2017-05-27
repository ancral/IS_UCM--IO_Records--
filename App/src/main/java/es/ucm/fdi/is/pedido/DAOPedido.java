package es.ucm.fdi.is.pedido;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.usuario.Usuario;

public interface DAOPedido {

	public void crearPedido(Pedido pedido) throws TiendaDatabaseException;
	public void meterDisco(Pedido pedido, Disco disco) throws TiendaDatabaseException;
	public boolean existeDisco(Pedido pedido, Disco disco) throws TiendaDatabaseException;
	public void eliminarDiscoPedido(Pedido pedido, Disco disco) throws TiendaDatabaseException;
	public void eliminarPedido(Pedido pedido) throws TiendaDatabaseException;
	public void finalizarPedido(Pedido pedido) throws TiendaDatabaseException;
	public ArrayList<Pedido> verPedidosUsuario(Usuario usuario) throws TiendaDatabaseException;
	public void addProductoPedido(Disco disco, Pedido pedido) throws TiendaDatabaseException;
	public void actualizarPedido(Pedido antiguo, Pedido nuevo) throws TiendaDatabaseException;
	public ArrayList<Pedido> verTodosPedidosParaVentas() throws TiendaDatabaseException;
	
}
