package es.ucm.fdi.is.launcher;

import javax.swing.UIManager;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.view.TiendaView;
import es.ucm.fdi.is.view.Utilidades;

import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

public class CargandoLauncher {

	SplashScreen screen;

	public CargandoLauncher() {
		// Iniciamos la splash screen
		splashScreenInit();

		// Consumir tiempo
		for (int i = 0; i <= 500; i++) {
			for (long j = 0; j < 50000; ++j) {
				@SuppressWarnings("unused")
				String waiting = " " + (j + i);
			}
			screen.setProgress("Cargando: " + i/5, i);
		}
		
		java.sql.Date ourJavaDateObject = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		System.out.println(ourJavaDateObject);
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				//Cargamos el programa cuando se haya acabado el loading
				TiendaView.getTiendaView();
			}
		});
		
		
		splashScreenDestruct();
	}

	private void splashScreenDestruct() {
		screen.setScreenVisible(false);
	}

	private void splashScreenInit() {
		ImageIcon myImage = new ImageIcon(Utilidades.loadImage("logo.png"));
		screen = new SplashScreen(myImage);
		screen.setLocationRelativeTo(null);
		screen.setProgressMax(400);
		screen.setScreenVisible(true);
	}

	public static void main(String[] args) throws TiendaDatabaseException {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		TiendaDatabase.iniciar();

		new CargandoLauncher();

	}

}