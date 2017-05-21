package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Cancion;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.SADiscoImp;
import es.ucm.fdi.is.disco.Valoracion;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DiscoView extends JFrame implements TiendaObserver {

	private static final long serialVersionUID = -5306391966978836217L;
	
	private static DiscoView discoView = null;
	
	/**
	 * Referencia a la vista del catálogo para poder hacerlo visible al
	 * accionar el botón de regresar
	 */
	private TiendaView tiendaView;
	private Disco disco;
	
	private DiscoView(TiendaView view, Disco disco) {
		super("I/O Records > Ventana de disco");
		this.tiendaView = view;
		this.disco = disco;
		initGUI();
	}
	
	public static DiscoView getDiscoView(TiendaView view,Disco disco) {
		return new DiscoView(view,disco);
	}
	
	private void initGUI() {
		// CREACIÓN DEL CONTENEDOR PRINCIPAL
		// ----------------------------------------------
		JPanel main = new JPanel();
		BoxLayout mainLayout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(mainLayout);
		main.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
		this.setContentPane(main);
		
		/* ------------------------------------------------
		 * MENÚ SUPERIOR
		 * ------------------------------------------------ */
		this.setJMenuBar(new MenuSuperior(this));
		
		// BOTÓN PARA REGRESAR AL CATÁLOGO
		// ----------------------------------------------
		JPanel botones = new JPanel();
		BorderLayout botonesLy = new BorderLayout();
		botones.setLayout(botonesLy);
		botones.setBorder(BorderFactory.createEmptyBorder(0, 0, 8, 0));
		
		JPanel botonesLine = new JPanel();
		FlowLayout botonesLineLy = new FlowLayout();
		botonesLine.setLayout(botonesLineLy);
		
		JButton regresar = new JButton("Volver al catálogo", Utilidades.createImage("iconos/volver.png", 32, 32));
		regresar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DiscoView.this.setVisible(false); // ocultamos esta vista
				DiscoView.this.tiendaView.setVisible(true); // hacemos visible el catálogo
			}
			
		});
		botonesLine.add(regresar);
		
		// BOTÓN PARA MODIFICAR DISCO
		// ----------------------------------------------
		JButton modificar = new JButton(Utilidades.createImage("iconos/modificar.png", 32, 32));
		modificar.setToolTipText("Modificar disco del catálogo");
		
		modificar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ModificarDisco();
			}
			
		});
		
		botonesLine.add(modificar);
		
		// BOTÓN PARA DESCATALOGAR DISCO
		// ----------------------------------------------
		JButton descatalogar = new JButton(Utilidades.createImage("iconos/borrar.png", 32, 32));
		descatalogar.setToolTipText("Descatalogar disco del catálogo");
		botonesLine.add(descatalogar);
		
		// BOTÓN PARA PONER OFERTA
		// ----------------------------------------------
		JButton oferta = new JButton(Utilidades.createImage("iconos/oferta.png", 32, 32));
		oferta.setToolTipText("Establecer una oferta en el disco");
		
		oferta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String ofertaIn = 
						JOptionPane.showInputDialog(DiscoView.this, 
								"Introduce la oferta para este disco", "Añadir oferta a disco",
								JOptionPane.QUESTION_MESSAGE);
				System.out.println(ofertaIn); // TODO: test
			}
			
		});
		
		botonesLine.add(oferta);
		
		botones.add(botonesLine, BorderLayout.WEST);
		main.add(botones);
		
		
		JPanel contenedor = new JPanel();
		BorderLayout contenedorLy = new BorderLayout();
		contenedor.setLayout(contenedorLy);
		
		// INFORMACIÓN DEL DISCO SELECCIONADO
		// ----------------------------------------------
		contenedor.add(new DiscoInfo(disco), BorderLayout.NORTH);
		
		contenedor.add(Box.createVerticalStrut(20), BorderLayout.CENTER); // espacio en blanco
		
		
		// LISTA DE CANCIONES DEL DISCO
		// ----------------------------------------------
		contenedor.add(new ListaCanciones(disco), BorderLayout.SOUTH);
		
		
		main.add(contenedor);
		
		this.setIconImage(Utilidades.loadImage("iconos/logo.png"));
		this.pack();
		this.setLocationRelativeTo(null); // centra la ventana
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void notify(Notificacion notificacion) {
		// TODO Auto-generated method stub
		
	}
	
	private class DiscoInfo extends JPanel {
		
		private static final long serialVersionUID = 7708121068748511959L;

		public DiscoInfo(final Disco disco) {
			Color color = new Color(76, 79, 127);
			
			System.out.println(disco.getGenero().toString());
			
			this.setBackground(color);
			this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			FlowLayout discoLayout = new FlowLayout();
			this.setLayout(discoLayout);
			
			// Carátula
			JPanel caratulaP = new JPanel();
			caratulaP.setBackground(color);
			BoxLayout caratulaPLay = new BoxLayout(caratulaP, BoxLayout.Y_AXIS);
			caratulaP.setLayout(caratulaPLay);		
			JLabel caratula = new JLabel(Utilidades.createImage("caratulas/"+disco.getTitulo()+".jpg", 250, 250));
			caratulaP.add(caratula);
			
			JPanel comprarPanel = new JPanel();
			comprarPanel.setBackground(color);
			BoxLayout comprarPanelLy = new BoxLayout(comprarPanel, BoxLayout.Y_AXIS);
			comprarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			comprarPanel.setLayout(comprarPanelLy);
			comprarPanel.add(new JButton("Añadir al carrito", Utilidades.createImage("iconos/compra.png", 32, 32)));
			caratulaP.add(comprarPanel);
			this.add(caratulaP);
			
			// Layout de la información de la derecha
			FlowLayout leftAlignment = new FlowLayout();
			leftAlignment.setAlignment(FlowLayout.LEFT);
			
			// Título y más información
			JPanel discoInfo = new JPanel();
			discoInfo.setBackground(color);
			BoxLayout discoInfoLayout = new BoxLayout(discoInfo, BoxLayout.Y_AXIS);
			discoInfo.setLayout(discoInfoLayout);		
			
			// Título del disco
			JPanel nombreDiscoPanel = new JPanel(leftAlignment);
			nombreDiscoPanel.setBackground(color);
			JLabel nombreDiscoIcon = new JLabel(Utilidades.createImage("iconos/disc-title.png", 32, 32));
			nombreDiscoPanel.add(nombreDiscoIcon);
			JLabel nombreDisco = new JLabel(disco.getTitulo().toUpperCase());
			nombreDisco.setForeground(Color.WHITE);
			nombreDisco.setFont(new Font("sans", Font.BOLD, 35));
			nombreDiscoPanel.add(nombreDisco);
			
			discoInfo.add(nombreDiscoPanel);
			
			// Autor del disco
			JPanel autorPanel = new JPanel(leftAlignment);
			autorPanel.setBackground(color);
			JLabel autorIcon = new JLabel(Utilidades.createImage("iconos/disc-author.png", 18, 18));
			autorPanel.add(autorIcon);
			JLabel autorDisco = new JLabel(disco.getAutor());
			autorDisco.setForeground(Color.WHITE);
			autorPanel.add(autorDisco);
			
			discoInfo.add(autorPanel);
			
			// Género del disco
			JPanel generoPanel = new JPanel(leftAlignment);
			generoPanel.setBackground(color);
			JLabel generoIcon = new JLabel(Utilidades.createImage("iconos/categorias.png", 18, 18));
			generoPanel.add(generoIcon);
			JLabel generoDisco = new JLabel(disco.getGenero().toString());
			generoDisco.setForeground(Color.WHITE);
			generoPanel.add(generoDisco);
			
			discoInfo.add(generoPanel);
			
			// Valoracion del disco
			JPanel valoracionPanel = new JPanel(leftAlignment);
			valoracionPanel.setBackground(color);
			JLabel valoracionIcon = new JLabel(Utilidades.createImage("iconos/valoracion.png", 18, 18));
			valoracionPanel.add(valoracionIcon);
			
			// Creaccion del puntuaje por estrellas
			// ------------------------------------------
			
			// La valoracion cuando se mete un disco en la DB sera de 0
			
			int seleccion = Math.round(disco.getValoracion());
			System.out.println(seleccion);
			System.out.println(disco.getValoracion());
			final StarRater starRater = new StarRater(5, disco.getValoracion(), seleccion);
			starRater.addStarListener(new StarRater.StarListener() {
			    @SuppressWarnings("restriction")
				public void handleSelection(final int selection) {
			    	
			    	//Notificacion de votacion
			    	new JFXPanel();
			    	
			    	Platform.runLater(new Runnable() {

						public void run() {
							TrayNotification tray = new TrayNotification();
							tray.setTitle("Gracias por votar");
							tray.setMessage("Has votado con un "+selection+" el disco "
									+disco.getTitulo()+" de "+disco.getAutor());
							tray.setNotificationType(NotificationType.INFORMATION);
							tray.setAnimationType(AnimationType.FADE);
				            tray.showAndDismiss(Duration.millis(1500));
						}
						
					});
			    	
			    	starRater.setRating(selection);
			    	starRater.setEnabled(false);
			    	
			    	int votantes = disco.getNumVotaciones();
			    	float votaciones = selection + disco.getValoracionTotal();
			    	float total = (votaciones/votantes);

			    	//Guardamos el disco antes de la actualizacion de la valoracion
			    	Disco discoAntiguo = disco;

			    	disco.setValoracion(total);
			    	
			    	
			    	try {
						FactoriaIntegracion.getFactoria().generaDAODisco()
						.actualizarDisco(discoAntiguo, disco);
					} catch (TiendaDatabaseException e) {
						System.out.println("ERROR");
						e.printStackTrace();
					}
			    }
			  });
			
			valoracionPanel.add(starRater);
			discoInfo.add(valoracionPanel);
			
			// Sello del disco
			JPanel selloPanel = new JPanel(leftAlignment);
			selloPanel.setBackground(color);
			JLabel selloDisco = new JLabel(disco.getSello());
			selloDisco.setForeground(Color.WHITE);
			selloPanel.add(selloDisco);
			
			discoInfo.add(selloPanel);
			
			// Fecha de salida del disco
			JPanel fechaPanel = new JPanel(leftAlignment);
			fechaPanel.setBackground(color);
			JLabel fechaDisco = new JLabel(disco.getFechaSalida().toString());
			fechaDisco.setForeground(Color.WHITE);
			fechaPanel.add(fechaDisco);
			
			discoInfo.add(fechaPanel);
			
			discoInfo.add(new JSeparator());
					
			// Precio del disco
			JPanel precioPanel = new JPanel(leftAlignment);
			precioPanel.setBackground(color);
			JLabel precioIcon = new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32));
			precioPanel.add(precioIcon);
			JLabel precioDisco = new JLabel(disco.getPrecioVenta().toString());
			precioDisco.setForeground(Color.WHITE);
			precioDisco.setFont(new Font("sans", Font.BOLD, 15));
			precioPanel.add(precioDisco);
			
			discoInfo.add(precioPanel);
			
			this.add(discoInfo);
		}
		
	}
	
	private class ListaCanciones extends JPanel {

		private static final long serialVersionUID = -3657305710338484372L;

		public ListaCanciones(Disco disco) {
			GridLayout listaPanelLy = new GridLayout(6, 2, 0, 0);
			this.setLayout(listaPanelLy);
			
			TitledBorder listaBorder = new TitledBorder("Lista de canciones: ");
			this.setBorder(listaBorder);
			
			
			Disco encontrado;
			try {
				encontrado = FactoriaIntegracion.getFactoria()
						.generaDAODisco().leerDisco(disco.getTitulo());
				
			List<Cancion> canciones = encontrado.getListaCanciones();
			Iterator<Cancion> it = canciones.iterator();
			
			while(it.hasNext()){
				JPanel cancion = new JPanel();
				FlowLayout cancionLy = new FlowLayout();
				cancionLy.setAlignment(FlowLayout.LEFT);
				cancion.setLayout(cancionLy);
				cancion.add(new JLabel(Utilidades.createImage("iconos/play.png", 10, 10)));
				cancion.add(new JLabel(it.next().toString()));
				this.add(cancion);
			}
			} catch (TiendaDatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private class ModificarDisco extends JDialog {
		
		private static final long serialVersionUID = 4075235213791213667L;

		public ModificarDisco() {
			super(DiscoView.this, "Modificar disco", true);
			
			JPanel contenedor = new JPanel();
			BoxLayout contenedorLy = new BoxLayout(contenedor, BoxLayout.Y_AXIS);
			contenedor.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			contenedor.setLayout(contenedorLy);
			
			JPanel modificar = new JPanel();
			FlowLayout modificarLy = new FlowLayout();
			modificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			modificar.setLayout(modificarLy);
			
			// Formulario izquierda
			JPanel formulario = new JPanel();
			formulario.setBorder(BorderFactory.createTitledBorder("Modificar datos del disco"));
			BoxLayout formularioLy = new BoxLayout(formulario, BoxLayout.Y_AXIS);
			formulario.setLayout(formularioLy);
			
			JPanel titulo = new JPanel(new FlowLayout());
			titulo.add(new JLabel("Título: "));
			titulo.add(new JTextField(30));
			
			formulario.add(titulo);
			
			JPanel autor = new JPanel(new FlowLayout());
			autor.add(new JLabel("Intérprete: "));
			autor.add(new JTextField(15));
			
			formulario.add(autor);
			
			JPanel genero = new JPanel(new FlowLayout());
			genero.add(new JLabel("Género musical: "));
			genero.add(new JComboBox<GeneroDisco>(GeneroDisco.values()));
			
			formulario.add(genero);
			
			JPanel valoracion = new JPanel(new FlowLayout());
			valoracion.add(new JLabel("Valoración del disco: "));
			valoracion.add(new JComboBox<Valoracion>(Valoracion.values()));
			
			formulario.add(valoracion);
			
			JPanel discografica = new JPanel(new FlowLayout());
			discografica.add(new JLabel("Discográfica: "));
			discografica.add(new JTextField(15));
			
			formulario.add(discografica);
			
			JPanel fecha = new JPanel(new FlowLayout());
			fecha.add(new JLabel("Fecha de salida: "));
			fecha.add(new JTextField(10));
			fecha.add(new JLabel("DD/MM/AAAA"));
			
			formulario.add(fecha);
			
			JPanel precio = new JPanel(new FlowLayout());
			precio.add(new JLabel("Precio del disco: "));
			precio.add(new JTextField(5));
			precio.add(new JLabel("€"));
			
			formulario.add(precio);
			
			
			modificar.add(formulario);
			modificar.add(new JLabel(Utilidades.createImage("iconos/editar.png", 200, 200)));
			
			contenedor.add(modificar);
			
			JPanel guardarPan = new JPanel();
			JButton guardar = new JButton("Guardar cambios", Utilidades.createImage("iconos/save.png", 18, 18));
			guardar.setPreferredSize(new Dimension(200, 30));
			guardarPan.add(guardar);
			contenedor.add(guardarPan);
			
			this.setContentPane(contenedor);
			this.pack();
			this.setResizable(false);
			this.setLocationRelativeTo(null); // To center the dialog
			this.setVisible(true);
			
		}
	}

}
