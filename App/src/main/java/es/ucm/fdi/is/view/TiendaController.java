package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class TiendaController {
	
	private static TiendaController tiendaController = null;
	
	private static SADisco disco = FactoriaSA.getFactoria().generaSADisco();
	
	public static TiendaController getTiendaController() {
		if (tiendaController == null)
			tiendaController = new TiendaController();
		
		return tiendaController;
	}

	private TiendaController() {}
	
	public void leerTodoCatalogo() {
		try {
			disco.leerTodos();
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void leerPorGenero(GeneroDisco genero) {
		try {
			disco.leerPorGenero(genero);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void buscarDisco(String titulo) {
		try {
			disco.buscarDisco(titulo);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addObserver(TiendaObserver observer) {
		disco.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		disco.removeObserver(observer);
	}

}
