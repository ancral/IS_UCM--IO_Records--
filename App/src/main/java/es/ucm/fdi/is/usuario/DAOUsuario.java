package es.ucm.fdi.is.usuario;

import java.util.List;

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.pedido.Pedido;

public interface DAOUsuario {
	
	public void crearPedido(Pedido pedido);
	public void eliminarPedido(Pedido pedido);
	public List<Pedido> verPedidosUsuario(Usuario usuario);
	public void addProductoPedido(Disco disco, Pedido pedido);
	public void actualizarPedido(Pedido antiguo, Pedido nuevo);

}
