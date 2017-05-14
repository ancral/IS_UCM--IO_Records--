package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class DiscoView extends JFrame implements TiendaObserver {

	private static final long serialVersionUID = -5306391966978836217L;
	
	private static DiscoView discoView = null;
	
	/**
	 * Referencia a la vista del catálogo para poder hacerlo visible al
	 * accionar el botón de regresar
	 */
	private TiendaView tiendaView;

	private DiscoView(TiendaView view) {
		super("I/O Records > Ventana de disco");
		this.tiendaView = view;
		initGUI();
	}
	
	public static DiscoView getDiscoView(TiendaView view) {
		if (discoView == null)
			discoView = new DiscoView(view);
		
		return discoView;
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
		JPanel disco = new JPanel();
		disco.setBackground(new Color(76, 79, 127));
		disco.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		FlowLayout discoLayout = new FlowLayout();
		disco.setLayout(discoLayout);
		
		// Carátula
		JPanel caratulaP = new JPanel();
		caratulaP.setBackground(new Color(76, 79, 127));
		BoxLayout caratulaPLay = new BoxLayout(caratulaP, BoxLayout.Y_AXIS);
		caratulaP.setLayout(caratulaPLay);		
		JLabel caratula = new JLabel(Utilidades.createImage("caratulas/cover.jpg", 250, 250));
		caratulaP.add(caratula);
		
		JPanel comprarPanel = new JPanel();
		comprarPanel.setBackground(new Color(76, 79, 127));
		BoxLayout comprarPanelLy = new BoxLayout(comprarPanel, BoxLayout.Y_AXIS);
		comprarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		comprarPanel.setLayout(comprarPanelLy);
		comprarPanel.add(new JButton("Añadir al carrito", Utilidades.createImage("iconos/compra.png", 32, 32)));
		caratulaP.add(comprarPanel);
		disco.add(caratulaP);
		
		// Layout de la información de la derecha
		FlowLayout leftAlignment = new FlowLayout();
		leftAlignment.setAlignment(FlowLayout.LEFT);
		
		// Título y más información
		JPanel discoInfo = new JPanel();
		discoInfo.setBackground(new Color(76, 79, 127));
		BoxLayout discoInfoLayout = new BoxLayout(discoInfo, BoxLayout.Y_AXIS);
		discoInfo.setLayout(discoInfoLayout);		
		
		// Título del disco
		JPanel nombreDiscoPanel = new JPanel(leftAlignment);
		nombreDiscoPanel.setBackground(new Color(76, 79, 127));
		JLabel nombreDiscoIcon = new JLabel(Utilidades.createImage("iconos/disc-title.png", 32, 32));
		nombreDiscoPanel.add(nombreDiscoIcon);
		JLabel nombreDisco = new JLabel("Nombre del disco");
		nombreDisco.setForeground(Color.WHITE);
		nombreDisco.setFont(new Font("sans", Font.BOLD, 35));
		nombreDiscoPanel.add(nombreDisco);
		
		discoInfo.add(nombreDiscoPanel);
		
		// Autor del disco
		JPanel autorPanel = new JPanel(leftAlignment);
		autorPanel.setBackground(new Color(76, 79, 127));
		JLabel autorIcon = new JLabel(Utilidades.createImage("iconos/disc-author.png", 18, 18));
		autorPanel.add(autorIcon);
		JLabel autorDisco = new JLabel("Autor del disco");
		autorDisco.setForeground(Color.WHITE);
		autorPanel.add(autorDisco);
		
		discoInfo.add(autorPanel);
		
		// Género del disco
		JPanel generoPanel = new JPanel(leftAlignment);
		generoPanel.setBackground(new Color(76, 79, 127));
		JLabel generoIcon = new JLabel(Utilidades.createImage("iconos/categorias.png", 18, 18));
		generoPanel.add(generoIcon);
		JLabel generoDisco = new JLabel("Género del disco");
		generoDisco.setForeground(Color.WHITE);
		generoPanel.add(generoDisco);
		
		discoInfo.add(generoPanel);
		
		// Género del disco
		JPanel valoracionPanel = new JPanel(leftAlignment);
		valoracionPanel.setBackground(new Color(76, 79, 127));
		JLabel valoracionIcon = new JLabel(Utilidades.createImage("iconos/valoracion.png", 18, 18));
		valoracionPanel.add(valoracionIcon);
		JLabel valoracionDisco = new JLabel("Valoración del disco");
		valoracionDisco.setForeground(Color.WHITE);
		valoracionPanel.add(valoracionDisco);
		
		discoInfo.add(valoracionPanel);
		
		// Sello del disco
		JPanel selloPanel = new JPanel(leftAlignment);
		selloPanel.setBackground(new Color(76, 79, 127));
		JLabel selloDisco = new JLabel("Discográfica del disco");
		selloDisco.setForeground(Color.WHITE);
		selloPanel.add(selloDisco);
		
		discoInfo.add(selloPanel);
		
		// Fecha de salida del disco
		JPanel fechaPanel = new JPanel(leftAlignment);
		fechaPanel.setBackground(new Color(76, 79, 127));
		JLabel fechaDisco = new JLabel("Fecha de salida del disco");
		fechaDisco.setForeground(Color.WHITE);
		fechaPanel.add(fechaDisco);
		
		discoInfo.add(fechaPanel);
		
		discoInfo.add(new JSeparator());
				
		// Precio del disco
		JPanel precioPanel = new JPanel(leftAlignment);
		precioPanel.setBackground(new Color(76, 79, 127));
		JLabel precioIcon = new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32));
		precioPanel.add(precioIcon);
		JLabel precioDisco = new JLabel("Precio del disco");
		precioDisco.setForeground(Color.WHITE);
		precioDisco.setFont(new Font("sans", Font.BOLD, 15));
		precioPanel.add(precioDisco);
		
		discoInfo.add(precioPanel);
		
		disco.add(discoInfo);
		contenedor.add(disco, BorderLayout.NORTH);
		
		contenedor.add(Box.createVerticalStrut(20), BorderLayout.CENTER); // espacio en blanco
		
		
		// LISTA DE CANCIONES DEL DISCO
		// ----------------------------------------------
		JPanel listaPanel = new JPanel();
		GridLayout listaPanelLy = new GridLayout(6, 2, 0, 0);
		listaPanel.setLayout(listaPanelLy);
		
		TitledBorder listaBorder = new TitledBorder("Lista de canciones (duración)");
		listaPanel.setBorder(listaBorder);
		
		for (int i = 0; i < 12; i++) {
			JPanel cancion = new JPanel();
			FlowLayout cancionLy = new FlowLayout();
			cancionLy.setAlignment(FlowLayout.LEFT);
			cancion.setLayout(cancionLy);
			cancion.add(new JLabel(Utilidades.createImage("iconos/play.png", 10, 10)));
			cancion.add(new JLabel(i + ". Título de la canción"));
			listaPanel.add(cancion);
		}
		
		contenedor.add(listaPanel, BorderLayout.SOUTH);
		
		
		main.add(contenedor);
		
		this.pack();
		this.setLocationRelativeTo(null); // centra la ventana
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public void notify(Notificacion notificacion) {
		// TODO Auto-generated method stub
		
	}

}
