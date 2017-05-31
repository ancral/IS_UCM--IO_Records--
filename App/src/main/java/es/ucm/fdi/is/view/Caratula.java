package es.ucm.fdi.is.view;

import java.awt.Color;

import javax.swing.*;

import es.ucm.fdi.is.disco.Disco;

public class Caratula extends JLabel {

	private static final long serialVersionUID = -2527744143645528102L;
	private Disco disco;
	
	public Caratula(Disco disco, int columnas, int filas) {
		super(Utilidades.createImage("caratulas/" + disco.getCaratula(), 
				filas < 3 ? 680 / filas : 220,
				filas < 3 ? 680 / filas : 220));

		this.disco = disco;
		
		// Si el catálogo tiene menos de 3 filas el borde rompe la estética
		if (filas >= 3)
			this.setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, Color.GRAY));
		
	}
	
	public Disco getDisco() {
		return this.disco;
	}

}
