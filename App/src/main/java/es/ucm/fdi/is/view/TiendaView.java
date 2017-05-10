package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TiendaView extends JFrame {

	private static final long serialVersionUID = 5963169495489228054L;
	
	public TiendaView() {
		super("I/O Records > Catálogo");
		initGUI();
	}
	
	public void initGUI() {
		JPanel main = new JPanel();
		this.setContentPane(main);
		
		BorderLayout mainLayout = new BorderLayout();
		main.setLayout(mainLayout);
		
		/* ------------------------------------------------
		 * TOOLBAR DE LA VENTANA
		 * ------------------------------------------------ */
		main.add(new BarraSuperior(), BorderLayout.NORTH);
		
		/* ------------------------------------------------
		 * CATÁLOGO DE DISCOS
		 * ------------------------------------------------ */
		JPanel catalogo = new JPanel();
		main.add(catalogo, BorderLayout.WEST);
		
		final int filas = 5;
		final int columnas = 3;
		GridLayout catalogoLayout = new GridLayout(filas, columnas, 10, 10);
		catalogo.setLayout(catalogoLayout);
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				catalogo.add(new Caratula());
			}
		}
		
		
		/* ------------------------------------------------
		 * LISTA DE CATEGORÍAS
		 * ------------------------------------------------ */
		JPanel categorias = new JPanel();
		BoxLayout categoriasLayout = new BoxLayout(categorias, BoxLayout.Y_AXIS);
		categorias.setLayout(categoriasLayout);
		main.add(categorias, BorderLayout.EAST);
		
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		categorias.add(new JLabel("Categoria X"));
		
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // centra la ventana
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class BarraSuperior extends JToolBar {
		
		private static final long serialVersionUID = 5141185337233797115L;

		public BarraSuperior() {
			this.setFloatable(false);
			
			JPanel toolBarPanel = new JPanel();
			FlowLayout toolBarLayout = new FlowLayout();
			toolBarPanel.setLayout(toolBarLayout);
			this.add(toolBarPanel);

			// LOGOTIPO
			JLabel logo = new JLabel(Utilidades.createImage("logo.png", 270, 61));
			toolBarPanel.add(logo);
			
			toolBarPanel.add(new JSeparator(SwingConstants.VERTICAL));
			
			// SEPARADOR
			JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
			sep1.setPreferredSize(new Dimension(1,50));
			
			toolBarPanel.add(sep1);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			
			// BOTONES
			JButton op1 = new JButton("Opción 1");
			JButton op2 = new JButton("Opción 2");
			JButton op3 = new JButton("Opción 3");
			
			toolBarPanel.add(op1);
			toolBarPanel.add(op2);
			toolBarPanel.add(op3);
			
			// SEPARADOR
			JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
			sep2.setPreferredSize(new Dimension(1,50));
			
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			toolBarPanel.add(sep2);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			
			// BARRA DE BÚSQUEDA
			JPanel busqueda = new JPanel();
			JTextField field = new JTextField(20);
			field.setMaximumSize(new Dimension(30, 30));
			busqueda.add(field);
			busqueda.add(new JLabel(Utilidades.createImage("iconos/search.png", 28, 28)));
			busqueda.setBorder(new TitledBorder("Buscar en el catálogo"));
			toolBarPanel.add(busqueda);
			
			// SEPARADOR
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			JSeparator sep3 = new JSeparator(SwingConstants.VERTICAL);
			sep3.setPreferredSize(new Dimension(1,50));
			
			// BOTÓN DE USUARIO
			toolBarPanel.add(sep3);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			JLabel usuario = new JLabel(Utilidades.createImage("iconos/user.png", 48, 48));
			toolBarPanel.add(usuario);
		}
		
	}

}
