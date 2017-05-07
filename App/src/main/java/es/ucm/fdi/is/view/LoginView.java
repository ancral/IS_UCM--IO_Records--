package es.ucm.fdi.is.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import es.ucm.fdi.is.mvc.TiendaObserver;

public class LoginView extends JFrame implements TiendaObserver {
	
	private static final long serialVersionUID = 147811163718718526L;
	private JTextField usuario;
	private JPasswordField clave;
	private LoginController control;

	public LoginView(LoginController control) {
		super("I/O Records > Control de accesos");
		
		this.control = control;
		control.addObserver(this);
		
		JPanel main = new JPanel();
		this.setContentPane(main);
		main.setBorder(new EmptyBorder(30, 30, 30, 30));
		
		// Establecemos un BorderLayout a la ventana
		BoxLayout layout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		main.setLayout(layout);
		
		// Añadimos el logotipo
		JPanel logotipo = new JPanel();
		main.add(logotipo);
		logotipo.add(new JLabel(new ImageIcon(Utilidades.loadImage("logo.png"))));
		
		// Formulario de acceso
		JPanel info = new JPanel();
		main.add(info);
		JLabel texto = new JLabel("Bienvenido a la tienda. Por favor, identifíquese para continuar");
		texto.setFont(texto.getFont().deriveFont(new Float(16)));
		info.add(texto);
		
		// Formulario de acceso
		JPanel formulario = new JPanel();
		main.add(formulario);
		formulario.add(new JLabel("Usuario: "));
		this.usuario = new JTextField(15);
		formulario.add(usuario);
		formulario.add(new JLabel("Contraseña: "));
		this.clave = new JPasswordField(15);
		formulario.add(clave);
		formulario.setBorder(new EmptyBorder(0, 0, 20, 0));
		
		// Botón acceder
		JPanel acceder = new JPanel();
		main.add(acceder);
		JButton botonAcceder = new JButton("Acceder a la tienda");
		botonAcceder.addActionListener(new AccederListener());
		
		acceder.add(botonAcceder);
		
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private class AccederListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			control.iniciarSesion(usuario.getText(), clave.getPassword());
		}
		
	}

	public void notify(String mensaje) {
		System.out.println(mensaje);		
	}

}
