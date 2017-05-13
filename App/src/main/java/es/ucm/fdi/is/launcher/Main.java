package es.ucm.fdi.is.launcher;

import javax.swing.SwingUtilities;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.view.DiscoView;
import es.ucm.fdi.is.view.LoginController;
import es.ucm.fdi.is.view.TiendaView;

public class Main {

	public static void main(String[] args) throws TiendaDatabaseException {
		
		TiendaDatabase.iniciar();

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				TiendaView.getTiendaView(LoginController.getLoginController(FactoriaSA.getFactoria().generaSAUsuario()));
				// DiscoView.getDiscoView();
			}
		});
	}
	
}
