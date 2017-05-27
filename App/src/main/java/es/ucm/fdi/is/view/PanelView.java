package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.Valoracion;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;
import es.ucm.fdi.is.pedido.TipoRecogida;
import es.ucm.fdi.is.usuario.Cliente;
import es.ucm.fdi.is.usuario.Usuario;

public class PanelView extends JDialog implements TiendaObserver {

	private static final long serialVersionUID = -1880613885872636597L;
	private static TiendaController control = TiendaController.getTiendaController();
	private static PanelView panelView = null;
	private JPanel contenedorPedidos = new JPanel();
	
	public static PanelView getPanelView(JFrame window){
		if (panelView == null)
			panelView = new PanelView(window);
		
		return panelView;
	}

	private PanelView(JFrame window) {
		super(window, "Panel del empleado", true);
		
		control.addObserver(this);
		control.buscarTodosPedidos();
		
		JPanel contenedor = new JPanel();
		contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		BorderLayout contenedorLy = new BorderLayout();
		contenedor.setLayout(contenedorLy);
		
		JTabbedPane tabs = new JTabbedPane();
		
		
		contenedor.add(tabs, BorderLayout.NORTH);
		
		//this.contenedorPedidos = new JPanel();
		GridLayout pedidosLy = new GridLayout(2, 3, 20, 20);
		this.contenedorPedidos.setLayout(pedidosLy);
		
		tabs.addTab("Pedidos pendientes", Utilidades.createImage("iconos/pedidos.png", 32, 32), contenedorPedidos);
		tabs.addTab("Añadir disco", Utilidades.createImage("iconos/categorias.png", 32, 32), new InsertarDisco());
		
		this.setContentPane(contenedor);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // To center the dialog
		
		// Hay que hacerlo visible al instanciar la vista
		// this.setVisible(true);
		
	}
	
	private class Pedidos extends JPanel {
		
		private static final long serialVersionUID = 899820362427560765L;
		private boolean finalizado;
		
		public Pedidos(final Pedido p) {
			this.setBorder(BorderFactory.createMatteBorder(3, 3, 8, 3, Color.DARK_GRAY));
			
			JPanel contenedor = new JPanel();
			contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
			BoxLayout pedidoLy = new BoxLayout(contenedor, BoxLayout.Y_AXIS);
			contenedor.setLayout(pedidoLy);
			
			JPanel titulo = new JPanel();
			JLabel pedidoTitulo = new JLabel("Pedido nº");
			pedidoTitulo.setFont(new Font("sans", Font.BOLD, 16));
			titulo.add(pedidoTitulo);
			contenedor.add(titulo);
			
			JPanel numero = new JPanel();
			JLabel pedidoNum = new JLabel(""+p.getId());
			pedidoNum.setFont(new Font("sans", Font.BOLD, 16));
			numero.add(pedidoNum);
			contenedor.add(numero);
			
			JPanel estadoT = new JPanel();
			JLabel pedidoEstado = new JLabel("Estado:");
			pedidoEstado.setFont(new Font("sans", Font.BOLD, 12));
			estadoT.add(pedidoEstado);
			contenedor.add(estadoT);
			
			
			JPanel estado = new JPanel(new FlowLayout());
			estado.add(new JLabel(Utilidades.createImage("iconos/espera.png", 16, 16)));
			String estadoMensaje = finalizado ? "Finalizado" : "En espera";
			estado.add(new JLabel(estadoMensaje));
			contenedor.add(estado);
			
			JPanel aceptarB = new JPanel();
			final JButton aceptar = new JButton("Aceptar", Utilidades.createImage("iconos/ok.png", 16, 16));
			aceptar.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					aceptar.setText("Aceptado");
					aceptar.setEnabled(false);
					//control.eliminarPedido(p, p.ge);
					setFinal(true);
				}
			});
			aceptarB.add(aceptar);
			contenedor.add(aceptarB);
			
			this.add(contenedor);
		}

		private void setFinal(boolean f) {
			this.finalizado = f;
		}
	}
	
	private class InsertarDisco extends JPanel {

		private static final long serialVersionUID = -4720947553669859763L;

		public InsertarDisco() {
			BoxLayout contenedorLy = new BoxLayout(this, BoxLayout.Y_AXIS);
			this.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
			this.setLayout(contenedorLy);
			
			JPanel modificar = new JPanel();
			FlowLayout modificarLy = new FlowLayout();
			modificar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
			modificar.setLayout(modificarLy);
			
			// Formulario izquierda
			JPanel formulario = new JPanel();
			formulario.setBorder(BorderFactory.createTitledBorder("Añadir disco al catálogo"));
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
			modificar.add(new JLabel(Utilidades.createImage("iconos/disco.png", 200, 200)));
			
			this.add(modificar);
			
			JPanel guardarPan = new JPanel();
			JButton guardar = new JButton("Añadir disco", Utilidades.createImage("iconos/save.png", 18, 18));
			
			/*
			guardar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					Disco nuevoDisco = 
							new Disco(tituloF.getText(), autorF.getText(), (GeneroDisco) generoCB.getSelectedItem(),
									(Valoracion) valoracionCB.getSelectedItem(), discograficaF.getText(),
									(Date) fechaF.getText(), precioF.getText(), "null.jpg");
				}
				
			});*/
			
			guardar.setPreferredSize(new Dimension(200, 30));
			guardarPan.add(guardar);
			this.add(guardarPan);
		}
	}
	
	private void insertar(ArrayList<?> pedidos)
	{
		
		this.contenedorPedidos.removeAll();
		this.contenedorPedidos.repaint();
		
		GridLayout pedidosLy = new GridLayout(2, 3, 20, 20);
		this.contenedorPedidos.setLayout(pedidosLy);
		
		Iterator<?> it = pedidos.iterator();
		
		
		while(it.hasNext()){
			Pedido p = (Pedido) it.next();
			this.contenedorPedidos.add(new Pedidos(p));
		}
	}
	
	
	
	public void notify(Notificacion notificacion) {
		switch (notificacion.getNotificacion()) {
		case CARRITO_FINALIZADO:
		case LEER_TODOSPEDIDOS:
			insertar(notificacion.getDiscosOpedido());
			this.pack();
			break;
		default:
			break;

		}
	}
}
