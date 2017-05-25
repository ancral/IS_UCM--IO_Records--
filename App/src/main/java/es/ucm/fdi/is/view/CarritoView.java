package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.*;

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;

public class CarritoView extends JDialog implements TiendaObserver {

	private static final long serialVersionUID = -105457093080251712L;
	
	private static CarritoView carritoView = null;
	private static DiscoController discoController = DiscoController.getDiscoController();
	
	private TiendaView tiendaView;
	private JPanel listaDiscos;
	private Pedido pedido;
	private int numArticulos;
	private float precioCarrito;
	private JLabel numArticulosLb;
	private JLabel precioCarritoLb;
	
	public static CarritoView getCarritoView(TiendaView tiendaView, Pedido pedido) {
		if (carritoView == null)
			carritoView = new CarritoView(tiendaView, pedido);
		
		return carritoView;
	}
	
	private CarritoView(TiendaView tiendaView, Pedido pedido) {
		super(tiendaView, "Carrito de la compra", true);
		
		discoController.addObserver(this);
		
		this.tiendaView = tiendaView;
		this.pedido = pedido;
		this.numArticulos = 0;
		this.precioCarrito = 0;
		
		JPanel contenedor = new JPanel();
		BorderLayout contenedorLy = new BorderLayout();
		contenedor.setLayout(contenedorLy);
		contenedor.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		JPanel titulo = new JPanel();
		FlowLayout tituloLy = new FlowLayout();
		titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titulo.setLayout(tituloLy);	
		
		JLabel tituloLb = new JLabel("Tu lista de la compra");
		tituloLb.setFont(new Font("sans", Font.BOLD, 30));
		
		titulo.add(new JLabel(Utilidades.createImage("iconos/compra.png", 42, 42)));
		titulo.add(tituloLb);
		
		contenedor.add(titulo, BorderLayout.NORTH);
		
		JScrollPane listaScroll = new JScrollPane();
		listaDiscos = new JPanel();
		BoxLayout listaDiscosLy = new BoxLayout(listaDiscos, BoxLayout.Y_AXIS);
		listaDiscos.setLayout(listaDiscosLy);
		
		Iterator<Disco> pedidos = pedido.getDiscos().iterator();
		
		while (pedidos.hasNext()) {
			Disco ped = pedidos.next();
			
			this.numArticulos += 1;
			this.precioCarrito += ped.getPrecioVenta();
			
			listaDiscos.add(new DiscoInfo(ped));
			listaDiscos.add(Box.createVerticalStrut(5)); // espacio en blanco
		}
		
		listaScroll.setViewportView(listaDiscos);
		listaScroll.setPreferredSize(new Dimension(600, 300));
		listaScroll.getVerticalScrollBar().setUnitIncrement(16); // aumenta la velocidad de barra de scroll
		
		contenedor.add(listaScroll, BorderLayout.CENTER);
		
		JPanel inferior = new JPanel();
		BorderLayout inferiorLy = new BorderLayout();
		inferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		inferior.setLayout(inferiorLy);
		
		inferior.add(new JSeparator(), BorderLayout.NORTH);
		
		JPanel datos = new JPanel();
		BoxLayout datosLy = new BoxLayout(datos, BoxLayout.Y_AXIS);
		datos.setLayout(datosLy);
		this.numArticulosLb = new JLabel("Número de artículos: " + this.numArticulos);
		this.numArticulosLb.setFont(new Font("sans", Font.BOLD, 20));
		datos.add(this.numArticulosLb);
		this.precioCarritoLb = new JLabel("Precio total: " + this.precioCarrito + "€");
		this.precioCarritoLb.setFont(new Font("sans", Font.BOLD, 20));
		datos.add(this.precioCarritoLb);
		
		datos.add(Box.createVerticalStrut(5)); // espacio en blanco
		
		datos.add(new JButton("Hacer pedido", Utilidades.createImage("iconos/pedidos.png", 32, 32)));
		
		inferior.add(datos, BorderLayout.SOUTH);
		
		
		contenedor.add(inferior, BorderLayout.SOUTH);
		
		this.setContentPane(contenedor);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // To center the dialog
		
		// Hay que hacerlo visible al instanciar la vista
		// this.setVisible(true);
	}
	
	public void refrescarCarrito(Pedido pedido) {
		listaDiscos.removeAll();
		listaDiscos.repaint();
		
		Iterator<Disco> pedidos = pedido.getDiscos().iterator();
		
		/* 
		 * Reiniciamos el número de artículos y el precio total
		 * (se vuelve a recorrer toda la lista de pedidos)
		 */
		
		CarritoView.this.numArticulos = 0;
		CarritoView.this.precioCarrito = 0;
	
		while (pedidos.hasNext()) {
			Disco ped = pedidos.next();
			
			/*
			 * Nuevos valores se actualizan al recorrer el contenido del pedido
			 */
			
			CarritoView.this.numArticulos += 1;
			CarritoView.this.precioCarrito += ped.getPrecioVenta();
		
			listaDiscos.add(new DiscoInfo(ped));
			listaDiscos.add(Box.createVerticalStrut(5)); // espacio en blanco
		}
		
		CarritoView.this.numArticulosLb.setText("Número de artículos: " + this.numArticulos);
		CarritoView.this.precioCarritoLb.setText("Precio total: " + Utilidades.round(this.precioCarrito, 2) + "€");
	}
	
	private class DiscoInfo extends JPanel {
		
		private static final long serialVersionUID = -3664881150439954949L;

		public DiscoInfo(final Disco disco) {
			Color color = new Color(250, 130, 130);
			
			this.setBackground(color);
			this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			FlowLayout discoLayout = new FlowLayout();
			this.setLayout(discoLayout);
			
			// Carátula
			JPanel caratulaP = new JPanel();
			caratulaP.setBackground(color);
			BoxLayout caratulaPLay = new BoxLayout(caratulaP, BoxLayout.Y_AXIS);
			caratulaP.setLayout(caratulaPLay);		
			JLabel caratula = new JLabel(Utilidades.createImage("caratulas/" + disco.getCaratula(), 150, 150));
			caratulaP.add(caratula);
			
			JPanel comprarPanel = new JPanel();
			comprarPanel.setBackground(color);
			BoxLayout comprarPanelLy = new BoxLayout(comprarPanel, BoxLayout.Y_AXIS);
			comprarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			comprarPanel.setLayout(comprarPanelLy);
			
			JButton borrar = new JButton("Quitar", Utilidades.createImage("iconos/borrar.png", 18, 18));
			
			borrar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					discoController.eliminarDiscoPedido(CarritoView.this.pedido, disco, CarritoView.this.tiendaView.usuarioSesion);
				}
				
			});
			
			comprarPanel.add(borrar);
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
			JLabel nombreDisco = new JLabel(disco.getTitulo());
			nombreDisco.setForeground(Color.BLACK);
			// nombreDisco.setFont(new Font("sans", Font.BOLD, 15));
			nombreDiscoPanel.add(nombreDisco);
			
			discoInfo.add(nombreDiscoPanel);
			
			// Autor del disco
			JPanel autorPanel = new JPanel(leftAlignment);
			autorPanel.setBackground(color);
			JLabel autorIcon = new JLabel(Utilidades.createImage("iconos/disc-author.png", 32, 32));
			autorPanel.add(autorIcon);
			JLabel autorDisco = new JLabel(disco.getAutor());
			autorDisco.setForeground(Color.BLACK);
			autorPanel.add(autorDisco);
			
			discoInfo.add(autorPanel);
			
			discoInfo.add(new JSeparator());
					
			// Precio del disco
			JPanel precioPanel = new JPanel(leftAlignment);
			precioPanel.setBackground(color);
			JLabel precioIcon = new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32));
			precioPanel.add(precioIcon);
			JLabel precioDisco = new JLabel(disco.getPrecioVenta().toString());
			precioDisco.setForeground(Color.BLACK);
			precioDisco.setFont(new Font("sans", Font.BOLD, 15));
			precioPanel.add(precioDisco);
			
			discoInfo.add(precioPanel);
			
			this.add(discoInfo);
		}
	}

	public void notify(final Notificacion notificacion) {
		
		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				handleNotify(notificacion);
			}
			
		});
		
	}
	
	public void handleNotify(Notificacion notificacion) {
		
		switch (notificacion.getNotificacion()) {
		case ANYADIR_CARRITO:
			refrescarCarrito(notificacion.getUsuario().getPedido());
			break;
			
		case BORRADO_CARRITO:
			refrescarCarrito(notificacion.getUsuario().getPedido());
			System.out.println("Borrado");
			break;
		
			default:
				break;
		}
		
	}

}
