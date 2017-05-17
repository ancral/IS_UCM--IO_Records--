package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.*;

import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.Valoracion;


public class PanelView extends JDialog {

	private static final long serialVersionUID = -1880613885872636597L;
	
	private static PanelView panelView = null;
	
	public static PanelView getPanelView(JFrame window) {
		if (panelView == null)
			panelView = new PanelView(window);
		
		return panelView;
	}

	private PanelView(JFrame window) {
		super(window, "Panel del empleado", true);
		
		JPanel contenedor = new JPanel();
		contenedor.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		BorderLayout contenedorLy = new BorderLayout();
		contenedor.setLayout(contenedorLy);
		
		JTabbedPane tabs = new JTabbedPane();
		
		
		contenedor.add(tabs, BorderLayout.NORTH);
		
		JPanel contenedorPedidos = new JPanel();
		GridLayout pedidosLy = new GridLayout(2, 3, 10, 10);
		contenedorPedidos.setLayout(pedidosLy);
		
		// GRID DE PEDIDOS
		
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 3; j++) {
				contenedorPedidos.add(new Pedido());
			}
		}
		
		tabs.addTab("Pedidos pendientes", Utilidades.createImage("iconos/pedidos.png", 32, 32), contenedorPedidos);
		tabs.addTab("Añadir disco", Utilidades.createImage("iconos/categorias.png", 32, 32), new InsertarDisco());
		
		
		this.setContentPane(contenedor);
		this.pack();
		this.setResizable(false);
		this.setLocationRelativeTo(null); // To center the dialog
		
		// Hay que hacerlo visible al instanciar la vista
		// this.setVisible(true);
		
	}
	
	private class Pedido extends JPanel {
		
		private static final long serialVersionUID = 899820362427560765L;

		public Pedido() {
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
			JLabel pedidoNum = new JLabel("XXXXXXXX");
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
			estado.add(new JLabel("En espera"));
			contenedor.add(estado);
			
			JPanel aceptarB = new JPanel();
			JButton aceptar = new JButton("Aceptar", Utilidades.createImage("iconos/ok.png", 16, 16));
			aceptarB.add(aceptar);
			contenedor.add(aceptarB);
			
			this.add(contenedor);
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
			modificar.add(new JLabel(Utilidades.createImage("iconos/disco.png", 200, 200)));
			
			this.add(modificar);
			
			JPanel guardarPan = new JPanel();
			JButton guardar = new JButton("Añadir disco", Utilidades.createImage("iconos/save.png", 18, 18));
			guardar.setPreferredSize(new Dimension(200, 30));
			guardarPan.add(guardar);
			this.add(guardarPan);
		}
	}
}
