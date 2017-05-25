 package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;

public class PedidosView extends JDialog implements TiendaObserver {
	
	private static final long serialVersionUID = -4213333647897143783L;
	
	private static PedidosView pedidosView = null;
	private static TiendaController tiendaController = TiendaController.getTiendaController();
	
	private JPanel contenedorPedidos;
	private GridLayout pedidosLy;
	
	public static PedidosView getPedidosView(JFrame window) {
		if (pedidosView == null)
			pedidosView = new PedidosView(window);
		
		return pedidosView;
	}

	private PedidosView(JFrame window) {
		super(window, "Lista de pedidos", true);
		
		tiendaController.addObserver(this);
		
		JPanel contenedor = new JPanel();
		BorderLayout contenedorLy = new BorderLayout();
		contenedor.setLayout(contenedorLy);
		contenedor.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
		
		JPanel titulo = new JPanel();
		FlowLayout tituloLy = new FlowLayout();
		titulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
		titulo.setLayout(tituloLy);	
		
		JLabel tituloLb = new JLabel("Tu lista de pedidos");
		tituloLb.setFont(new Font("sans", Font.BOLD, 30));
		
		titulo.add(new JLabel(Utilidades.createImage("iconos/pedidos.png", 42, 42)));
		titulo.add(tituloLb);
		
		contenedor.add(titulo, BorderLayout.NORTH);
		
		
		contenedorPedidos = new JPanel();
		pedidosLy = new GridLayout(2, 3, 20, 20);
		contenedorPedidos.setLayout(pedidosLy);
		
		contenedor.add(contenedorPedidos, BorderLayout.CENTER);
		
		
		this.setContentPane(contenedor);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // To center the dialog
		
		// Hay que hacerlo visible al instanciar la vista
		// this.setVisible(true);
	}
	
	private class PedidoInfo extends JPanel {
		
		private static final long serialVersionUID = -2054428498926191259L;

		public PedidoInfo(Pedido pedido) {
			this.setBorder(BorderFactory.createMatteBorder(3, 3, 8, 3, Color.DARK_GRAY));
			
			JPanel contenedor = new JPanel();
			contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			BoxLayout pedidoLy = new BoxLayout(contenedor, BoxLayout.Y_AXIS);
			contenedor.setLayout(pedidoLy);
			
			JPanel titulo = new JPanel();
			JLabel pedidoTitulo = new JLabel("Pedido nº");
			pedidoTitulo.setFont(new Font("sans", Font.BOLD, 20));
			titulo.add(pedidoTitulo);
			contenedor.add(titulo);
			
			JPanel numero = new JPanel();
			JLabel pedidoNum = new JLabel(Integer.toString(pedido.getId()));
			pedidoNum.setFont(new Font("sans", Font.BOLD, 20));
			numero.add(pedidoNum);
			contenedor.add(numero);
			
			JPanel estadoT = new JPanel();
			JLabel pedidoEstado = new JLabel("Estado:");
			pedidoEstado.setFont(new Font("sans", Font.BOLD, 12));
			estadoT.add(pedidoEstado);
			contenedor.add(estadoT);
			
			JPanel estado = new JPanel(new FlowLayout());
			estado.add(new JLabel(Utilidades.createImage("iconos/espera.png", 16, 16)));
			estado.add(new JLabel("En espera"));
			contenedor.add(estado);
			
			JPanel cancelarB = new JPanel();
			JButton cancelar = new JButton("Cancelar", Utilidades.createImage("iconos/cancelar.png", 16, 16));
			cancelarB.add(cancelar);
			contenedor.add(cancelarB);
			
			this.add(contenedor);
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
		case CARRITO_FINALIZADO:
			System.out.println("Carrito finalizado");

			this.pedidosLy = new GridLayout(2, 3, 20, 20);
			this.contenedorPedidos.setLayout(pedidosLy);
			this.contenedorPedidos.add(new PedidoInfo(notificacion.getUsuario().getPedido()));
			this.contenedorPedidos.revalidate();
			this.contenedorPedidos.repaint();
			break;
			
			default:
			break;
		}
		
	}

}
