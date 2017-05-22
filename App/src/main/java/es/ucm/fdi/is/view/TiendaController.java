package es.ucm.fdi.is.view;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class TiendaController {
	
	private static TiendaController tiendaController = null;
	
	private SADisco disco;
	
	public static TiendaController getTiendaController(SADisco disco) {
		if (tiendaController == null)
			tiendaController = new TiendaController(disco);
		
		return tiendaController;
	}

	private TiendaController(SADisco disco) {
		this.disco = disco;
	}
	
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
