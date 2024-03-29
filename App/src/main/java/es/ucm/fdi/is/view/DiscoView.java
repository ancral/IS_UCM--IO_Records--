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

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.disco.Cancion;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.OfertaDisco;
import es.ucm.fdi.is.disco.Valoracion;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

@SuppressWarnings("restriction")
public class DiscoView extends JFrame implements TiendaObserver {

	private static final long serialVersionUID = -5306391966978836217L;
	
	/**
	 * Referencia a la vista del catálogo para poder hacerlo visible al
	 * accionar el botón de regresar
	 */
	private TiendaView tiendaView;
	private Disco disco;
	
	/* Información del disco */
	private JLabel nombreDisco;
	private JLabel autorDisco;
	private JLabel generoDisco;
	private JLabel selloDisco;
	private JLabel fechaDisco;
	private JLabel precioDisco;
	
	private static DiscoController discoController = DiscoController.getDiscoController();
	
	public DiscoView(TiendaView view, CatalogoDiscos catalogo, Disco disco) {
		super("I/O Records > Ventana de disco");
		this.tiendaView = view;
		this.disco = disco;
		discoController.addObserver(this);
		discoController.addObserver(catalogo);

		initGUI();
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
		this.setJMenuBar(MenuSuperior.getMenuSuperior(this));
		
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
				discoController.removeObserver(DiscoView.this);
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
				if (DiscoView.this.tiendaView.usuarioSesion == null) {
					LoginView.getLoginView().setVisible(true);
				}
				else if (DiscoView.this.tiendaView.usuarioSesion.isEmpleado()){
					new ModificarDisco();
				}
				else {
					mostrarMensajeError();
				}
			}
			
		});
		
		botonesLine.add(modificar);
		
		// BOTÓN PARA DESCATALOGAR DISCO
		// ----------------------------------------------
		JButton descatalogar = new JButton(Utilidades.createImage("iconos/borrar.png", 32, 32));
		descatalogar.setToolTipText("Descatalogar disco del catálogo");
		
		descatalogar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (DiscoView.this.tiendaView.usuarioSesion == null) {
					LoginView.getLoginView().setVisible(true);
				}
				
				else if (DiscoView.this.tiendaView.usuarioSesion.isEmpleado()){
					String message = "¿Está seguro de que quiere descatalogar el disco?";
					String title = "Descatalogar disco";
					
					/* ¿Seguro que quieres descatalogar? */
					int delete = 
							JOptionPane.showConfirmDialog(
									null, message, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (delete == JOptionPane.OK_OPTION) {
						discoController.descatalogarDisco(DiscoView.this.disco);
					}
					
				}
				else {
					// El usuario no tiene privilegios suficientes
					mostrarMensajeError();
				}
			}
			
		});
		
		botonesLine.add(descatalogar);
		
		// BOTÓN PARA PONER OFERTA
		// ----------------------------------------------
		JButton oferta = new JButton(Utilidades.createImage("iconos/oferta.png", 32, 32));
		oferta.setToolTipText("Establecer una oferta en el disco");
		
		oferta.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (DiscoView.this.tiendaView.usuarioSesion == null) {
					LoginView.getLoginView().setVisible(true);
				}
				else if (DiscoView.this.tiendaView.usuarioSesion.isEmpleado()){
					String ofertaIn = 
							JOptionPane.showInputDialog(DiscoView.this, 
									"Introduce el porcentaje de descuento para este disco", "Añadir oferta a disco",
									JOptionPane.QUESTION_MESSAGE);
						
					try {
						OfertaDisco oferta = new OfertaDisco(Integer.parseInt(ofertaIn));
						discoController.aplicarOferta(disco, oferta);
						disco.setPrecioVenta(oferta.precioNuevo(disco));
					} catch (NumberFormatException ex) {
						JOptionPane.showMessageDialog(DiscoView.this, "¡Porcentaje de descuento incorrecto! Ejemplo: 20.5"
								, "Error al aplicar oferta", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					mostrarMensajeError();
				}
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

	public void notify(final Notificacion notificacion) {
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				handleEvent(notificacion);
			}
			
		});
		
	}
		
	
	public void handleEvent(Notificacion notificacion) {
		switch (notificacion.getNotificacion()) {
		case DISCO_ACTUALIZADO:
		case NUEVO_PRECIO_DISCO:
			DiscoView.this.nombreDisco.setText(disco.getTitulo());
			DiscoView.this.autorDisco.setText(disco.getAutor());
			DiscoView.this.generoDisco.setText(disco.getGenero().toString());
			DiscoView.this.selloDisco.setText(disco.getSello());
			DiscoView.this.precioDisco.setText(Float.toString(disco.getPrecioVenta()));
			// DiscoView.this.fechaDisco.setText(disco.getFechaSalida());
			break;
			
		case ANYADIR_CARRITO:
			JOptionPane.showMessageDialog(DiscoView.this, "¡El disco que has seleccionado se ha añadido al carrito!"
					, "Añadido al carrito", JOptionPane.INFORMATION_MESSAGE);
			break;
			
		case ERROR_DISCO_YA_CARRITO:
			JOptionPane.showMessageDialog(DiscoView.this, "El disco que has seleccionado ya está en el carrito"
					, "Error al añadir", JOptionPane.ERROR_MESSAGE);
			
			/*
			 * NO FUNCIONA BIEN SI LANZAMOS LA NOTIFICACIÓN MAS DE 1 VEZ
			new JFXPanel();
			Platform.runLater(new Runnable() {
				public void run() {
					TrayNotification tray = new TrayNotification();
					tray.setTitle("Error al añadir");
					tray.setMessage("El disco que has seleccionado ya está en el carrito");
					tray.setNotificationType(NotificationType.ERROR);
					tray.setAnimationType(AnimationType.FADE);
		            tray.showAndDismiss(Duration.millis(1500));
				}				
			});
			*/
			
			break;
			
		case DISCO_BORRADO:
			this.setVisible(false);
			this.tiendaView.setVisible(true);
			break;
			
			default:
				break;
		}
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
			JLabel caratula = new JLabel(Utilidades.createImage("caratulas/" +
			disco.getCaratula(), 250, 250));
			caratulaP.add(caratula);
			
			JPanel comprarPanel = new JPanel();
			comprarPanel.setBackground(color);
			BoxLayout comprarPanelLy = new BoxLayout(comprarPanel, BoxLayout.Y_AXIS);
			comprarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			comprarPanel.setLayout(comprarPanelLy);
			
			JButton carrito = new JButton("Añadir al carrito", Utilidades.createImage("iconos/compra.png", 32, 32));
			carrito.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					if (DiscoView.this.tiendaView.usuarioSesion == null)
						LoginView.getLoginView().setVisible(true);
					else
						discoController.anyadirAlPedido(tiendaView.usuarioSesion.getPedido(), disco, tiendaView.usuarioSesion);
				}
				
			});
			
			comprarPanel.add(carrito);
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
			DiscoView.this.nombreDisco = new JLabel(disco.getTitulo());
			nombreDisco.setForeground(Color.WHITE);
			nombreDisco.setFont(new Font("sans", Font.BOLD, 35));
			nombreDiscoPanel.add(nombreDisco);
			
			discoInfo.add(nombreDiscoPanel);
			
			// Autor del disco
			JPanel autorPanel = new JPanel(leftAlignment);
			autorPanel.setBackground(color);
			JLabel autorIcon = new JLabel(Utilidades.createImage("iconos/disc-author.png", 18, 18));
			autorPanel.add(autorIcon);
			DiscoView.this.autorDisco = new JLabel(disco.getAutor());
			autorDisco.setForeground(Color.WHITE);
			autorPanel.add(autorDisco);
			
			discoInfo.add(autorPanel);
			
			// Género del disco
			JPanel generoPanel = new JPanel(leftAlignment);
			generoPanel.setBackground(color);
			JLabel generoIcon = new JLabel(Utilidades.createImage("iconos/categorias.png", 18, 18));
			generoPanel.add(generoIcon);
			DiscoView.this.generoDisco = new JLabel(disco.getGenero().toString());
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
			
			final StarRater starRater = new StarRater(5, disco.getValoracion(), seleccion);
			starRater.addStarListener(new StarRater.StarListener() {
			    public void handleSelection(final int selection) {
			    	
			    	//Notificacion de votacion
			    	new JFXPanel();
			    	
			    	Platform.runLater(new Runnable() {

						public void run() {
							TrayNotification tray = new TrayNotification();
							tray.setTitle("Gracias por votar");
							tray.setMessage("Has votado con un " + selection + " el disco "
									+ disco.getTitulo()+" de " + disco.getAutor());
							tray.setNotificationType(NotificationType.INFORMATION);
							tray.setAnimationType(AnimationType.FADE);
				            tray.showAndDismiss(Duration.millis(1500));
						}
						
					});
			    	
			    	starRater.setRating(selection);
			    	starRater.setEnabled(false);
			    	
			    	Disco discoAntiguo = new Disco(disco);
			    	
			    	disco.setNumVotaciones(1);
			    	int votantes = disco.getNumVotaciones();
			    	float votaciones = selection + disco.getValoracion();
			    	float total = (votaciones/votantes);
			    	
			    	//Guardamos el disco antes de la actualizacion de la valoracion
			    	
			    	
			    	disco.setValoracion(total);
			    	
			    	
			    	discoController.actualizarDisco(discoAntiguo, disco);
			    	// TODO: No estoy muy seguro de que vaya haciendo la media de las valoraciones
			    }
			  });
			
			valoracionPanel.add(starRater);
			discoInfo.add(valoracionPanel);
			
			// Sello del disco
			JPanel selloPanel = new JPanel(leftAlignment);
			selloPanel.setBackground(color);
			JLabel selloIcon = new JLabel(Utilidades.createImage("iconos/disco.png", 18, 18));
			selloPanel.add(selloIcon);
			DiscoView.this.selloDisco = new JLabel(disco.getSello());
			selloDisco.setForeground(Color.WHITE);
			selloPanel.add(selloDisco);
			
			discoInfo.add(selloPanel);
			
			// Fecha de salida del disco
			JPanel fechaPanel = new JPanel(leftAlignment);
			fechaPanel.setBackground(color);
			JLabel fechaIcon = new JLabel(Utilidades.createImage("iconos/calendario.png", 18, 18));
			fechaPanel.add(fechaIcon);
			DiscoView.this.fechaDisco = new JLabel(disco.getFechaSalida().toString());
			fechaDisco.setForeground(Color.WHITE);
			fechaPanel.add(fechaDisco);
			
			discoInfo.add(fechaPanel);
			
			discoInfo.add(new JSeparator());
					
			// Precio del disco
			JPanel precioPanel = new JPanel(leftAlignment);
			precioPanel.setBackground(color);
			JLabel precioIcon = new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32));
			precioPanel.add(precioIcon);
			DiscoView.this.precioDisco = new JLabel(disco.getPrecioVenta().toString());
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

			Iterator<Cancion> canciones = disco.getListaCanciones().iterator();
			
			while(canciones.hasNext()){
				JPanel cancion = new JPanel();
				FlowLayout cancionLy = new FlowLayout();
				cancionLy.setAlignment(FlowLayout.LEFT);
				cancion.setLayout(cancionLy);
				cancion.add(new JLabel(Utilidades.createImage("iconos/play.png", 10, 10)));
				cancion.add(new JLabel(canciones.next().getTitulo()));
				this.add(cancion);
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
			final JTextField tituloF = new JTextField(30);
			titulo.add(tituloF);
			
			formulario.add(titulo);
			
			JPanel autor = new JPanel(new FlowLayout());
			autor.add(new JLabel("Intérprete: "));
			final JTextField autorF = new JTextField(15);
			autor.add(autorF);
			
			formulario.add(autor);
			
			JPanel genero = new JPanel(new FlowLayout());
			genero.add(new JLabel("Género musical: "));
			final JComboBox<GeneroDisco> generoCB = new JComboBox<GeneroDisco>(GeneroDisco.values());
			genero.add(generoCB);
			
			formulario.add(genero);
			
			JPanel valoracion = new JPanel(new FlowLayout());
			valoracion.add(new JLabel("Valoración del disco: "));
			final JComboBox<Valoracion> valoracionCB = new JComboBox<Valoracion>(Valoracion.values());  
			valoracion.add(valoracionCB);
			
			formulario.add(valoracion);
			
			JPanel discografica = new JPanel(new FlowLayout());
			discografica.add(new JLabel("Discográfica: "));
			final JTextField discograficaF = new JTextField(15);
			discografica.add(discograficaF);
			
			formulario.add(discografica);
			
			JPanel fecha = new JPanel(new FlowLayout());
			fecha.add(new JLabel("Fecha de salida: "));
			final JTextField fechaF = new JTextField(10);
			fecha.add(fechaF);
			fecha.add(new JLabel("DD/MM/AAAA"));
			
			formulario.add(fecha);
			
			JPanel precio = new JPanel(new FlowLayout());
			precio.add(new JLabel("Precio del disco: "));
			final JTextField precioF = new JTextField(5);
			precio.add(precioF);
			precio.add(new JLabel("€"));
			
			formulario.add(precio);
			
			
			modificar.add(formulario);
			modificar.add(new JLabel(Utilidades.createImage("iconos/editar.png", 200, 200)));
			
			contenedor.add(modificar);
			
			JPanel guardarPan = new JPanel();
			JButton guardar = new JButton("Guardar cambios", Utilidades.createImage("iconos/save.png", 18, 18));
			
			guardar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					final Disco discoAct =
							new Disco(disco.getTitulo(), disco.getAutor(), disco.getFechaSalida(), disco.getSello(),
									  disco.getGenero(), disco.getDuracion(), disco.getValoracion(), disco.getPrecioCompra(),
									  disco.getPrecioVenta(), disco.getListaCanciones(),
									  disco.getOferta(), disco.getCaratula(), disco.getNumVotaciones());
					
					
					/* Inicializamos el disco más cómodamente con los setters. 
					 * Solamente modificamos los campos en los que se haya escrito */
					
					discoAct.setTitulo(tituloF.getText().isEmpty() ? disco.getTitulo() : tituloF.getText());
					discoAct.setAutor(autorF.getText().isEmpty() ? disco.getAutor() : autorF.getText());
					discoAct.setGenero((GeneroDisco) generoCB.getSelectedItem());
					// discoAct.setValoracion(new Float(5));
					discoAct.setSello(discograficaF.getText().isEmpty() ? disco.getSello() : discograficaF.getText());
					
					// int dia = Integer.parseInt(fechaF.getText().substring(0, 1));
					// int mes = Integer.parseInt(fechaF.getText().substring(3, 4));
					// int anyo = Integer.parseInt(fechaF.getText().substring(6, 9));
					
					/*
					Date date = new Date(System.currentTimeMillis());
					discoAct.setFechaSalida(date);
					discoAct.setPrecioVenta(new Float(precioF.getText()));
					*/
					
					// Atributo con el nuevo disco
					final Disco discoAnterior = DiscoView.this.disco;
					DiscoView.this.disco = discoAct;
					
					// Actualizamos el disco en la BD
					Thread actualizar = new Thread(new Runnable() {

						public void run() {
							discoController.actualizarDisco(discoAnterior, discoAct);
						}
						
					});
					
					actualizar.start();
					

					// Cerramos el formulario al pulsar el botÃ³n
					ModificarDisco.this.dispose();
				}
				
			});
			
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
	
	private void mostrarMensajeError() {
		JOptionPane.showMessageDialog(DiscoView.this, "Con tu cuenta de cliente no puedes hacer uso de esta función"
				, "Privilegios insuficientes", JOptionPane.ERROR_MESSAGE);
	}

}
