package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class DiscoController {
	
	private static DiscoController discoController = null;
	
	private static SADisco disco = FactoriaSA.getFactoria().generaSADisco();
	
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
	
	public void addObserver(TiendaObserver observer) {
		disco.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		disco.removeObserver(observer);
	}

}
