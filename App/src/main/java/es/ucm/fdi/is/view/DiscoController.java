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
	
	public void actualizarDisco(Disco antiguo, Disco nuevo) {
		try {
			disco.actualizarDisco(antiguo, nuevo);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void anyadirAlPedido(Pedido ped, Disco disco, Usuario usuario) {
		try {
			// pedido.crearPedido(ped);
			pedido.addProducto(ped, disco, usuario);
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
