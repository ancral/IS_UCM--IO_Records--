package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.disco.GeneroDisco;

public class TiendaView extends JFrame {

	private static final long serialVersionUID = 5963169495489228054L;
	
	public TiendaView() {
		super("I/O Records > Catálogo");
		initGUI();
	}
	
	public void initGUI() {
		JPanel main = new JPanel();
		this.setContentPane(main);
		main.setBackground(new Color(76, 79, 127));
		
		BorderLayout mainLayout = new BorderLayout();
		main.setLayout(mainLayout);
		
		/* ------------------------------------------------
		 * MENÚ SUPERIOR
		 * ------------------------------------------------ */
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(76, 79, 127));
		
		JMenu ayuda = new JMenu("Ayuda");
		ayuda.setForeground(Color.WHITE);
		
		ayuda.add(new JMenuItem("Acerca I/O Records"));
		ayuda.addSeparator();
		ayuda.add(new JMenuItem("Salir"));
		
		menuBar.add(ayuda);
		this.setJMenuBar(menuBar);
		
		/* ------------------------------------------------
		 * TOOLBAR DE LA VENTANA
		 * ------------------------------------------------ */
		main.add(new BarraSuperior(), BorderLayout.NORTH);
		
		/* ------------------------------------------------
		 * CATÁLOGO DE DISCOS
		 * ------------------------------------------------ */
		JPanel catalogo = new JPanel();
		catalogo.setBackground(new Color(190, 190, 242));
		JScrollPane sp = new JScrollPane(catalogo);
		sp.setPreferredSize(new Dimension(670, 440));
		sp.getVerticalScrollBar().setUnitIncrement(16); // aumenta la velocidad de barra de scroll
		main.add(sp, BorderLayout.WEST);
		
		final int filas = 5;
		final int columnas = 3;
		GridLayout catalogoLayout = new GridLayout(filas, columnas, 5, 5);
		catalogo.setLayout(catalogoLayout);
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				catalogo.add(new Caratula("Disco (" + Integer.toString(i) + ", " + Integer.toString(j) + ")"));
			}
		}
		
		
		/* ------------------------------------------------
		 * LISTA DE CATEGORÍAS
		 * ------------------------------------------------ */
		JPanel panelDcho = new JPanel(); // panel principal que contiene todos los demás
		BoxLayout panelDchoLay = new BoxLayout(panelDcho, BoxLayout.Y_AXIS);
		panelDcho.setLayout(panelDchoLay);
		panelDcho.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		panelDcho.setBackground(new Color(76, 79, 127));
		
		// TÍTULO > GÉNEROS MUSICALES
		// ------------------------------------------
		JPanel tituloC = new JPanel(); // metemos el título dentro de un panel
		tituloC.setBackground(null);
		JLabel tituloCat = new JLabel("Géneros musicales");
		tituloCat.setFont(new Font("sans-serif", Font.BOLD, 20));
		tituloCat.setForeground(Color.WHITE);
		tituloCat.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
		tituloC.add(tituloCat);
		main.add(panelDcho, BorderLayout.EAST);
		panelDcho.add(tituloC);


		// ICONO Y SELECTOR DE GÉNEROS MUSICALES
		// ------------------------------------------
		JPanel comboIcon = new JPanel();
		TitledBorder catBorder = new TitledBorder("Filtrar catálogo");
		catBorder.setTitleColor(Color.WHITE);
		comboIcon.setBorder(catBorder);
		FlowLayout comboLyt = new FlowLayout();
		comboIcon.setLayout(comboLyt);
		comboIcon.setBackground(null);
		
		// COMBO-BOX DE GÉNEROS MUSICALES	
		// ------------------------------------------
		comboIcon.add(new JLabel(Utilidades.createImage("iconos/categorias.png", 50, 50)));
		JComboBox<GeneroDisco> menuCat = new JComboBox<GeneroDisco>(GeneroDisco.values());
		comboIcon.add(menuCat);
		panelDcho.add(comboIcon);
		
		// TÍTULO > INFORMACIÓN DEL DISCO
		// ------------------------------------------
		JPanel info = new JPanel();
		info.setBackground(null);
		panelDcho.add(info);
		JLabel tituloInfo = new JLabel("Información");
		tituloInfo.setFont(new Font("sans-serif", Font.BOLD, 20));
		tituloInfo.setForeground(Color.WHITE);
		tituloInfo.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
		info.add(tituloInfo);
		
		// INFORMACIÓN DEL DISCO
		// ------------------------------------------
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(null);
		BoxLayout infoLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
		infoPanel.setLayout(infoLayout);
		TitledBorder infoBorder = new TitledBorder("Acerca del disco");
		infoBorder.setTitleColor(Color.WHITE);
		infoPanel.setBorder(infoBorder);
		
		FlowLayout tituloLy = new FlowLayout();
		FlowLayout autorLy = new FlowLayout();
		FlowLayout precioLy = new FlowLayout();
		FlowLayout valoracionLy = new FlowLayout();
		
		JPanel titulo = new JPanel();
		titulo.setBackground(null);
		
		JPanel autor = new JPanel();
		autor.setBackground(null);
		JPanel precio = new JPanel();
		precio.setBackground(null);
		JPanel valoracion = new JPanel();
		valoracion.setBackground(null);
		
		titulo.setLayout(tituloLy);
		autor.setLayout(autorLy);
		precio.setLayout(precioLy);
		valoracion.setLayout(valoracionLy);
		
		titulo.add(new JLabel(Utilidades.createImage("iconos/disc-title.png", 32, 32)));
		JLabel tituloLb = new JLabel("Nombre del disco");
		tituloLb.setForeground(Color.WHITE);
		titulo.add(tituloLb);
		
		autor.add(new JLabel(Utilidades.createImage("iconos/disc-author.png", 32, 32)));
		JLabel autorLb = new JLabel("Autor del disco");
		autorLb.setForeground(Color.WHITE);
		autor.add(autorLb);
		
		valoracion.add(new JLabel(Utilidades.createImage("iconos/valoracion.png", 32, 32)));
		JLabel valoracionLb = new JLabel("Valoracion X/5");
		valoracionLb.setForeground(Color.WHITE);
		valoracion.add(valoracionLb);
		
		precio.add(new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32)));
		JLabel precioLb = new JLabel("Precio de venta");
		precioLb.setForeground(Color.WHITE);
		precio.add(precioLb);
		
		infoPanel.add(titulo);
		infoPanel.add(autor);
		infoPanel.add(precio);
		infoPanel.add(valoracion);
		
		panelDcho.add(infoPanel);
		
		
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
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, new Color(56, 56, 56)));
			
			JPanel toolBarPanel = new JPanel();
			FlowLayout toolBarLayout = new FlowLayout();
			toolBarPanel.setLayout(toolBarLayout);
			this.add(toolBarPanel);

			// LOGOTIPO
			// ------------------------------------------
			JLabel logo = new JLabel(Utilidades.createImage("logo.png", 270, 61));
			toolBarPanel.add(logo);
			
			toolBarPanel.add(new JSeparator(SwingConstants.VERTICAL));
			
			// SEPARADOR
			// ------------------------------------------
			JSeparator sep1 = new JSeparator(SwingConstants.VERTICAL);
			sep1.setPreferredSize(new Dimension(1,50));
			
			toolBarPanel.add(sep1);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			
			// BOTONES
			// ------------------------------------------
			JButton op1 = new JButton("Menú#1");
			op1.setToolTipText("Texto del botón 1");
			JButton op2 = new JButton("Menú#2");
			op2.setToolTipText("Texto del botón 2");
			JButton op3 = new JButton("Menú#3");
			op3.setToolTipText("Texto del botón 3");
			
			toolBarPanel.add(op1);
			toolBarPanel.add(op2);
			toolBarPanel.add(op3);
			
			// SEPARADOR
			// ------------------------------------------
			JSeparator sep2 = new JSeparator(SwingConstants.VERTICAL);
			sep2.setPreferredSize(new Dimension(1,50));
			
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			toolBarPanel.add(sep2);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			
			// BARRA DE BÚSQUEDA
			// ------------------------------------------
			JPanel busqueda = new JPanel();
			JTextField field = new JTextField(15);
			field.setMaximumSize(new Dimension(30, 30));
			field.setToolTipText("Introduce el nombre del disco que quieres buscar");
			busqueda.add(field);
			busqueda.add(new JLabel(Utilidades.createImage("iconos/search.png", 28, 28)));
			busqueda.setBorder(new TitledBorder("Buscar en el catálogo"));
			toolBarPanel.add(busqueda);
			
			// SEPARADOR
			// ------------------------------------------
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			JSeparator sep3 = new JSeparator(SwingConstants.VERTICAL);
			sep3.setPreferredSize(new Dimension(1,50));
			
			// BOTÓN DE USUARIO
			// ------------------------------------------
			toolBarPanel.add(sep3);
			toolBarPanel.add(Box.createHorizontalStrut(5)); // espacio en blanco
			JLabel usuario = new JLabel(Utilidades.createImage("iconos/user.png", 48, 48));
			toolBarPanel.add(usuario);
		}
		
	}

}
