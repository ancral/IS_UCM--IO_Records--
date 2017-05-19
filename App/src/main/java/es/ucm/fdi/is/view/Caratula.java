package es.ucm.fdi.is.view;

import java.awt.Color;

import javax.swing.*;

import es.ucm.fdi.is.disco.Disco;

public class Caratula extends JLabel {

	private static final long serialVersionUID = -2527744143645528102L;
	private Disco disco;
	
	public Caratula(Disco disco) {
		super(Utilidades.createImage("caratulas/" + disco.getTitulo() + ".jpg", 210, 210));
		this.disco = disco;
		
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, Color.GRAY));
	}
	
	public Disco getDisco() {
		return this.disco;
	}

}
