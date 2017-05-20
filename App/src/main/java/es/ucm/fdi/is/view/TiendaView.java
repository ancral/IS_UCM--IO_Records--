package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


@SuppressWarnings("restriction")
public class TiendaView extends JFrame implements TiendaObserver {

	private static final long serialVersionUID = 5963169495489228054L;
	
	/* Patrón Singleton en la vistas */
	private static TiendaView tiendaView = null;
	
	private LoginController loginController;
	private BarraLateral barraLateral;
	private JLabel usuario;
	private JLabel pieInfo;
	
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
		main.add(CatalogoDiscos.getCatalogoDiscos(this), BorderLayout.WEST);
		
		/* ------------------------------------------------
		 * BARRA LATERAL > GÉNEROS MUSICALES E INFO
		 * ------------------------------------------------ */
		barraLateral = BarraLateral.getBarraLateral(CatalogoDiscos.getCatalogoDiscos(this));
		main.add(barraLateral, BorderLayout.EAST);
		
		JPanel pie = new JPanel(new BorderLayout());
		pie.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		pie.setBackground(Color.BLACK);
		pieInfo = new JLabel("Pon el cursor encima de un disco para ver su nombre");
		pieInfo.setForeground(Color.WHITE);
		pie.add(pieInfo, BorderLayout.WEST);
		
		main.add(pie, BorderLayout.SOUTH);
		
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

	public void handleEvent(final Notificacion notificacion) {
		new JFXPanel();
		
		switch (notificacion.getNotificacion()) {
		
		case SESION_INICIADA:
			this.usuario.setIcon(Utilidades.createImage("iconos/user-ok.png", 48, 48));
			
			Platform.runLater(new Runnable() {

				public void run() {
					TrayNotification tray = new TrayNotification();
					tray.setTitle("BIENVENIDO");
					tray.setMessage(notificacion.getNotificacion().getMensaje());
					tray.setNotificationType(NotificationType.SUCCESS);
					tray.setAnimationType(AnimationType.FADE);
		            tray.showAndDismiss(Duration.millis(1500));
		            
		            AudioClip audio = new AudioClip(Utilidades.class
		            		.getClassLoader().getResource("audio/Notify.wav").toString());
		            audio.play();	  
				}
				
			});
			
			break;
		case ERROR_SESION:
			this.usuario.setIcon(Utilidades.createImage("iconos/user.png", 48, 48));
			
			Platform.runLater(new Runnable() {

				public void run() {
					TrayNotification tray = new TrayNotification();
					tray.setTitle("¡ERROR!");
					tray.setMessage(notificacion.getNotificacion().getMensaje());
					tray.setNotificationType(NotificationType.ERROR);
					tray.setAnimationType(AnimationType.POPUP);
		            tray.showAndWait();
		            
		            AudioClip audio = new AudioClip(Utilidades.class
		            		.getClassLoader().getResource("audio/sad.wav").toString());
		            audio.play();
				}
				
			});
			
			break;
		default:
			break;
			
		}
	}
	
	public void showTituloDisco(String titulo) {
		this.pieInfo.setText(titulo);
	}
	
	public void showDiscoInfo(Disco disco) {
		barraLateral.showDiscoInfo(disco);
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
			
			op1.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					PanelView.getPanelView(TiendaView.this).setVisible(true);
				}
				
			});
			
			op1.setToolTipText("Panel de administración del catálogo");
			JButton op2 = new JButton("Pedidos", Utilidades.createImage("iconos/pedidos.png", 25, 25));
			
			op2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					PedidosView.getPedidosView(TiendaView.this).setVisible(true);
				}
				
			});
			
			op2.setToolTipText("Ver pedidos");
			JButton op3 = new JButton("Carrito", Utilidades.createImage("iconos/compra.png", 25, 25));
			
			op3.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					CarritoView.getCarritoView(TiendaView.this).setVisible(true);
				}
				
			});
			
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
			final JTextField field = new JTextField(15);
			field.setMaximumSize(new Dimension(30, 30));
			field.setToolTipText("Introduce el nombre del disco que quieres buscar");
			busqueda.add(field);
			busqueda.add(new JLabel(Utilidades.createImage("iconos/search.png", 28, 28)));
			busqueda.setBorder(new TitledBorder("Buscar en el catálogo"));
			field.addKeyListener(new KeyListener() {
				
				public void keyTyped(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyReleased(KeyEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						ArrayList<Disco> discoBusqueda = new ArrayList<Disco>();
						Disco encontrado = null;
						try {
							//Hacemos una busqueda en la BD de los discos con ese genero
							encontrado = FactoriaIntegracion.getFactoria()
									.generaDAODisco().leerDisco(field.getText());
							discoBusqueda.add(encontrado);
						} catch (TiendaDatabaseException e1) {
							e1.printStackTrace();
						}
						if(encontrado==null)
						{
							field.setText("No se ha encontrado el disco");
							field.select(0,1000000);
						}else
						{
							//Actualizamos el catalogo con el disco
							CatalogoDiscos.getCatalogoDiscos(TiendaView.this).
							actualizar(discoBusqueda);
						}
					}
				}
			});
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

}
