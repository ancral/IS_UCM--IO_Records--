package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;
import es.ucm.fdi.is.pedido.SAPedido;
import es.ucm.fdi.is.usuario.Usuario;

public class DiscoController {
	
	private static DiscoController discoController = null;
	
	private static SADisco disco = FactoriaSA.getFactoria().generaSADisco();
	private static SAPedido pedido = FactoriaSA.getFactoria().generaSAPedido();
	
	public static DiscoController getDiscoController() {
		
		if (discoController == null)
			discoController = new DiscoController();
		
		return discoController;
	}
	
	private DiscoController() {}
	
	
	public void crearDisco(Disco dis) {
		try {
			disco.crearDisco(dis);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
		
	/**
	 * Actualiza un disco antiguo por uno nuevo dado
	 * 
	 * @param antiguo El disco antiguo
	 * @param nuevo El disco nuevo
	 */
	public void actualizarDisco(Disco antiguo, Disco nuevo) {
		try {
			disco.actualizarDisco(antiguo, nuevo);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Añade un disco al pedido (se utiliza en la vista del carrito)
	 * 
	 * @param ped El pedido
	 * @param disco El disco que se quiere añadir
	 * @param usuario Usuario que realiza la acción
	 */
	public void anyadirAlPedido(Pedido ped, Disco disco, Usuario usuario) {
		try {
			pedido.addProducto(ped, disco, usuario);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Elimina un disco de un pedido. Únicamente se puede eliminar un disco de
	 * un pedido cuando no ha sido finalizado, es decir, no ha pasado a la vista de Pedidos
	 * 
	 * @param ped Pedido que se quiere actualizar
	 * @param disco Disco que se quiere borrar
	 * @param usuario Usuario que realiza la acción
	 */
	public void eliminarDiscoPedido(Pedido ped, Disco disco, Usuario usuario) {
		try {
			pedido.eliminarDiscoPedido(ped, disco, usuario);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addObserver(TiendaObserver observer) {
		disco.addObverser(observer);
		pedido.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		disco.removeObserver(observer);
		pedido.removeObserver(observer);
	}
	
	
}
