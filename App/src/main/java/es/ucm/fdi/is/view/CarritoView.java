package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.*;

public class CarritoView extends JDialog {

	private static final long serialVersionUID = -105457093080251712L;
	
	private static CarritoView carritoView = null;
	
	public static CarritoView getCarritoView(JFrame window) {
		if (carritoView == null)
			carritoView = new CarritoView(window);
		
		return carritoView;
	}
	
	private CarritoView(JFrame window) {
		super(window, "Carrito de la compra", true);
		
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
		JPanel listaDiscos = new JPanel();
		BoxLayout listaDiscosLy = new BoxLayout(listaDiscos, BoxLayout.Y_AXIS);
		listaDiscos.setLayout(listaDiscosLy);
		
		listaDiscos.add(new DiscoInfo());
		listaDiscos.add(Box.createVerticalStrut(5)); // espacio en blanco
		listaDiscos.add(new DiscoInfo());
		listaDiscos.add(Box.createVerticalStrut(5)); // espacio en blanco
		listaDiscos.add(new DiscoInfo());
		
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
		JLabel numArticulos = new JLabel("Número de artículos: 2");
		numArticulos.setFont(new Font("sans", Font.BOLD, 20));
		datos.add(numArticulos);
		JLabel precio = new JLabel("Precio total: 27,50€");
		precio.setFont(new Font("sans", Font.BOLD, 20));
		datos.add(precio);
		
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
	
	private class DiscoInfo extends JPanel {
		
		private static final long serialVersionUID = -3664881150439954949L;

		public DiscoInfo() {
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
			JLabel caratula = new JLabel(Utilidades.createImage("caratulas/cover.jpg", 150, 150));
			caratulaP.add(caratula);
			
			JPanel comprarPanel = new JPanel();
			comprarPanel.setBackground(color);
			BoxLayout comprarPanelLy = new BoxLayout(comprarPanel, BoxLayout.Y_AXIS);
			comprarPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
			comprarPanel.setLayout(comprarPanelLy);
			comprarPanel.add(new JButton("Quitar", Utilidades.createImage("iconos/borrar.png", 18, 18)));
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
			JLabel nombreDisco = new JLabel("Este es el nombre de un disco muy largo");
			nombreDisco.setForeground(Color.BLACK);
			// nombreDisco.setFont(new Font("sans", Font.BOLD, 15));
			nombreDiscoPanel.add(nombreDisco);
			
			discoInfo.add(nombreDiscoPanel);
			
			// Autor del disco
			JPanel autorPanel = new JPanel(leftAlignment);
			autorPanel.setBackground(color);
			JLabel autorIcon = new JLabel(Utilidades.createImage("iconos/disc-author.png", 32, 32));
			autorPanel.add(autorIcon);
			JLabel autorDisco = new JLabel("Autor del disco");
			autorDisco.setForeground(Color.BLACK);
			autorPanel.add(autorDisco);
			
			discoInfo.add(autorPanel);
			
			discoInfo.add(new JSeparator());
					
			// Precio del disco
			JPanel precioPanel = new JPanel(leftAlignment);
			precioPanel.setBackground(color);
			JLabel precioIcon = new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32));
			precioPanel.add(precioIcon);
			JLabel precioDisco = new JLabel("Precio del disco");
			precioDisco.setForeground(Color.BLACK);
			precioDisco.setFont(new Font("sans", Font.BOLD, 15));
			precioPanel.add(precioDisco);
			
			discoInfo.add(precioPanel);
			
			this.add(discoInfo);
		}
	}

}
