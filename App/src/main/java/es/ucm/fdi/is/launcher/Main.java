package es.ucm.fdi.is.launcher;

import javax.swing.SwingUtilities;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.usuario.SAUsuarioImp;
import es.ucm.fdi.is.view.LoginController;
import es.ucm.fdi.is.view.LoginView;

public class Main {

	public static void main(String[] args) throws TiendaDatabaseException {
		SAUsuarioImp modelo = new SAUsuarioImp();
		final LoginController loginControl = new LoginController(modelo);
		
		TiendaDatabase.iniciar();
	
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				new LoginView(loginControl);
			}
		});
		
		// TiendaDatabase.cerrar();
	}
	
}
