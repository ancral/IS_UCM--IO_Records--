package es.ucm.fdi.is.view;

import java.awt.Color;

import javax.swing.*;

import es.ucm.fdi.is.disco.Valoracion;

public class Caratula extends JLabel {

	private static final long serialVersionUID = -2527744143645528102L;
	private String titulo;
	private String autor;
	private Float precio;
	private Valoracion valoracion;
	
	public Caratula(String titulo) {
		this("caratulas/cover.jpg", titulo);
		// this.setToolTipText(this.titulo);
	}
	
	public Caratula(String imagenPath, String titulo) {
		super(Utilidades.createImage(imagenPath, 210, 210));
		this.titulo = titulo;
		
		this.setBorder(BorderFactory.createMatteBorder(2, 2, 4, 2, Color.GRAY));
	}
	
	public String getTitulo() {
		return this.titulo;
	}

}