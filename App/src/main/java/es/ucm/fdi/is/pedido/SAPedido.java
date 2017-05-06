package es.ucm.fdi.is.pedido;

import java.util.List;

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.usuario.Usuario;

public interface SAPedido {
	
	public void crearPedido(Pedido pedido);
	public void devolverPedido(Pedido pedido);
	public List<Pedido> verPedidosUsuario(Usuario usuario);
	public void addProducto(Pedido pedido, Disco disco);
	public void modificarPedido(Pedido antiguo, Pedido nuevo);
	public void eliminar(Pedido pedido);

}
