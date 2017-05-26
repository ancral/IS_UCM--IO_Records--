package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;
import es.ucm.fdi.is.pedido.SAPedido;
import es.ucm.fdi.is.usuario.SAUsuario;
import es.ucm.fdi.is.usuario.Usuario;

public class TiendaController {
	
	/* Instancia única del controlador */
	private static TiendaController tiendaController = null;
	
	/* SERVICIOS DE APLICACIÓN */
	private static SADisco disco = FactoriaSA.getFactoria().generaSADisco();
	private static SAPedido pedido = FactoriaSA.getFactoria().generaSAPedido();
	private static SAUsuario usuario = FactoriaSA.getFactoria().generaSAUsuario();
	
	public static TiendaController getTiendaController() {
		if (tiendaController == null)
			tiendaController = new TiendaController();
		
		return tiendaController;
	}

	/* Constructor invisble */
	private TiendaController() {}
	
	/**
	 * Lee todos los discos del catálogo
	 */
	public void leerTodoCatalogo() {
		try {
			disco.leerTodos();
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Filtra el catálogo por el género musical que se le indique
	 * 
	 * @param genero Género musical
	 */
	public void leerPorGenero(GeneroDisco genero) {
		try {
			disco.leerPorGenero(genero);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Busca un disco en el catálogo
	 * 
	 * @param titulo Título deldisco
	 */
	public void buscarDisco(String titulo) {
		try {
			disco.buscarDisco(titulo);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Pone un pedido a esperas de que un empleado lo autorize y pase a ser una venta
	 * 
	 * @param ped Pedido
	 * @param usu Usuario que pone en cola el pedido
	 */
	public void finalizarPedido(Pedido ped, Usuario usu) {
		try {
			pedido.finalizarPedido(ped, usu); // Indica que este pedido pasa a la lista de espera para ser atendido
			usuario.asignarNuevoId(usu); // Asigna un nuevo identificador de pedido al usuario
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Elimina un pedido finalizado por el usuario antes de que el empleado lo haya aceptado.
	 * Una vez que el empleado ha aceptado un pedido ya no sería posible eliminarlo porque en
	 * este caso se trataría de una venta
	 * 
	 * @param ped Pedido a borrar
	 * @param usu Usuario que solicita borrar el pedido
	 */
	public void eliminarPedido(Pedido ped, Usuario usu) {
		try {
			pedido.eliminar(ped, usu);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addObserver(TiendaObserver observer) {
		disco.addObverser(observer);
		pedido.addObverser(observer);
		usuario.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		disco.removeObserver(observer);
		pedido.removeObserver(observer);
		usuario.addObverser(observer);
	}

}
