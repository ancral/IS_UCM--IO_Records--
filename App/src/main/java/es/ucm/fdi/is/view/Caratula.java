package es.ucm.fdi.is.view;

import javax.swing.*;

public class Caratula extends JLabel {

	private static final long serialVersionUID = -2527744143645528102L;
	
	public Caratula() {
		super(Utilidades.createImage("caratulas/cover.jpg", 200, 200));
	}
	
	public Caratula(String imagenPath) {
		super(Utilidades.createImage(imagenPath, 300, 300));
	}

}
