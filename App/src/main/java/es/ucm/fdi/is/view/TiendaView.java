package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class TiendaView extends JFrame implements TiendaObserver {

	private static final long serialVersionUID = 5963169495489228054L;
	
	/* Patrón Singleton en la vistas */
	private static TiendaView tiendaView = null;
	
	private LoginController loginController;
	private JLabel usuario;
	
	/* Constructor invisible */
	private TiendaView(LoginController control) {
		super("I/O Records > Catálogo");
		this.loginController = control;
		control.addObserver(this);
		initGUI();
	}
	
	/* Devuelve la única instancia del objeto */
	public static TiendaView getTiendaView(LoginController control) {
		if (tiendaView == null)
			tiendaView = new TiendaView(control);
		
		return tiendaView;
	}
	
	private void initGUI() {
		JPanel main = new JPanel();
		this.setContentPane(main);
		main.setBackground(new Color(76, 79, 127));
		
		BorderLayout mainLayout = new BorderLayout();
		main.setLayout(mainLayout);
		
		/* ------------------------------------------------
		 * MENÚ SUPERIOR
		 * ------------------------------------------------ */
		this.setJMenuBar(new MenuSuperior(this));
		
		/* ------------------------------------------------
		 * TOOLBAR DE LA VENTANA
		 * ------------------------------------------------ */
		main.add(new BarraSuperior(), BorderLayout.NORTH);
		
		/* ------------------------------------------------
		 * CATÁLOGO DE DISCOS
		 * ------------------------------------------------ */
		main.add(new CatalogoDiscos(), BorderLayout.WEST);
		
		/* ------------------------------------------------
		 * BARRA LATERAL > GÉNEROS MUSICALES E INFO
		 * ------------------------------------------------ */
		main.add(new BarraLateral(), BorderLayout.EAST);
		
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // centra la ventana
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void notify(final Notificacion notificacion) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				handleEvent(notificacion); // it updates the visual components
			}
		});
	}

	public void handleEvent(Notificacion notificacion) {
		switch (notificacion) {
		
		case SESION_INICIADA:
			this.usuario.setIcon(Utilidades.createImage("iconos/user-ok.png", 48, 48));
			break;
		case ERROR_SESION:
			this.usuario.setIcon(Utilidades.createImage("iconos/user.png", 48, 48));
			break;
		default:
			break;
			
		}
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
			JButton op1 = new JButton("Panel", Utilidades.createImage("iconos/admin.png", 25, 25));
			op1.setToolTipText("Panel de administración del catálogo");
			JButton op2 = new JButton("Pedidos", Utilidades.createImage("iconos/pedidos.png", 25, 25));
			op2.setToolTipText("Ver pedidos");
			JButton op3 = new JButton("Carrito", Utilidades.createImage("iconos/compra.png", 25, 25));
			op3.setToolTipText("Discos añadidos al carrito");
			
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
			usuario = new JLabel(Utilidades.createImage("iconos/user.png", 48, 48));
			usuario.setToolTipText("Iniciar sesión");
						
			usuario.addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					LoginView.getLoginView(TiendaView.this.loginController).setVisible(true);
				}

				public void mousePressed(MouseEvent e) {	}

				public void mouseReleased(MouseEvent e) {}

				public void mouseEntered(MouseEvent e) {}

				public void mouseExited(MouseEvent e) {}
				
			});
			
			toolBarPanel.add(usuario);
		}
		
	}
	
	private class CatalogoDiscos extends JScrollPane {
		
		private static final long serialVersionUID = 3809398699373783819L;

		public CatalogoDiscos() {
			
			JPanel catalogo = new JPanel();
			catalogo.setBackground(new Color(190, 190, 242));
			this.setViewportView(catalogo);
			this.setPreferredSize(new Dimension(670, 440));
			this.getVerticalScrollBar().setUnitIncrement(16); // aumenta la velocidad de barra de scroll
			
			final int filas = 5;
			final int columnas = 3;
			GridLayout catalogoLayout = new GridLayout(filas, columnas, 5, 5);
			catalogo.setLayout(catalogoLayout);
			
			for (int i = 0; i < filas; i++) {
				for (int j = 0; j < columnas; j++) {
					Caratula car = new Caratula("Disco (" + Integer.toString(i) + ", " + Integer.toString(j) + ")");
					car.addMouseListener(new MouseListener() {

						public void mouseClicked(MouseEvent e) {
							TiendaView.this.setVisible(false);
							// hacemos visible la información del disco
							DiscoView.getDiscoView(TiendaView.this).setVisible(true); 
						}

						public void mousePressed(MouseEvent e) {}

						public void mouseReleased(MouseEvent e) {}

						public void mouseEntered(MouseEvent e) {}

						public void mouseExited(MouseEvent e) {}
						
					});
					
					catalogo.add(car);
				}
			}
		}
	}
	
	private class BarraLateral extends JPanel {
		
		private static final long serialVersionUID = -7227943628939157168L;

		public BarraLateral() {
			/* ------------------------------------------------
			 * LISTA DE CATEGORÍAS
			 * ------------------------------------------------ */
			BoxLayout panelDchoLay = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setLayout(panelDchoLay);
			this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40));
			this.setBackground(new Color(76, 79, 127));
			
			// TÍTULO > GÉNEROS MUSICALES
			// ------------------------------------------
			JPanel tituloC = new JPanel(); // metemos el título dentro de un panel
			tituloC.setBackground(null);
			JLabel tituloCat = new JLabel("Géneros musicales");
			tituloCat.setFont(new Font("sans-serif", Font.BOLD, 20));
			tituloCat.setForeground(Color.WHITE);
			tituloCat.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
			tituloC.add(tituloCat);
			// main.add(panelDcho, BorderLayout.EAST);
			this.add(tituloC);


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
			this.add(comboIcon);
			
			// TÍTULO > INFORMACIÓN DEL DISCO
			// ------------------------------------------
			JPanel info = new JPanel();
			info.setBackground(null);
			this.add(info);
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
			
			this.add(infoPanel);
		}
		
	}	

}
