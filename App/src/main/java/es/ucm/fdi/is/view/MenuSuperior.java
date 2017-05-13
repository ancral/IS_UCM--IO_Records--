package es.ucm.fdi.is.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MenuSuperior extends JMenuBar {

	private static final long serialVersionUID = 6629207966576815877L;
	
	public MenuSuperior(final JFrame window) {
		this.setBackground(new Color(76, 79, 127));
		
		JMenu ayuda = new JMenu("Ayuda");
		ayuda.setForeground(Color.WHITE);
		
		JMenuItem acerca = new JMenuItem("Acerca I/O Records");
		acerca.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				AcercaTienda acercaDialog = new AcercaTienda(window);
				acercaDialog.setVisible(true);
			}
			
		});
		ayuda.add(acerca);
		ayuda.addSeparator();
		
		JMenuItem salir = new JMenuItem("Salir");
		salir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
		ayuda.add(salir);
		
		this.add(ayuda);
	}
	
private class AcercaTienda extends JDialog {
		
		private static final long serialVersionUID = -5345963891956226804L;
		private String about1 = "Esta es una aplicación programada en Java para la asignatura de Ingeniería del Software.";
		private String about2 = "Desarrollada utilizando una arquitectura multicapa y los patrones MVC,";
		private String about3 =  "Factoría abstracta, Singleton, DAO, Servicio de aplicación, Transfer y Observer.";
		private String about4 = "Utiliza la base de datos relacional SQLite";
		private String credits = "Desarrollada por David Arroyo, Ignacio Cepeda, Ángel Cruz, Hao Hao He y Carla Peñarrieta";
		
		public AcercaTienda(JFrame window) {
			super(window, "Acerca de I/O Records", true);
			
			JPanel panel = new JPanel();
			
			panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
			
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			this.setContentPane(panel);
			
			addLabel(about1);
			addLabel(about2);
			addLabel(about3);
			addLabel(about4);
			
			// Add UCM logo centered
			JLabel ucm = new JLabel(new ImageIcon(Utilidades.loadImage("ucm.png")));
			ucm.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(ucm);
			
			addLabel(credits);
			
			this.pack();
			this.setResizable(false);
			this.setLocationRelativeTo(null); // To center the dialog
		}
		
		/**
		 * Add a centered JLabel to the panel
		 * 
		 * @param text The JLabel's text
		 */
		private void addLabel(String text) {
			JLabel label = new JLabel(text);
			label.setAlignmentX(Component.CENTER_ALIGNMENT);
			this.add(label);
		}
	}

}
