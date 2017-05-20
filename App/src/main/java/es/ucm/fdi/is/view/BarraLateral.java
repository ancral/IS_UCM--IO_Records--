package es.ucm.fdi.is.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.GeneroDisco;

public class BarraLateral extends JPanel {
	
	private static final long serialVersionUID = -7227943628939157168L;
	
	private static BarraLateral barraLateral = null;
	
	private JLabel tituloLb;
	private JLabel autorLb;
	private JLabel valoracionLb;
	private JLabel precioLb;
	private JButton catalogoGeneral;
	
	public static BarraLateral getBarraLateral(CatalogoDiscos catalogo) {
		if (barraLateral == null)
			barraLateral = new BarraLateral(catalogo);
		
		return barraLateral;
	}

	private BarraLateral(CatalogoDiscos catalogo) {
		/* ------------------------------------------------
		 * LISTA DE CATEGORÍAS
		 * ------------------------------------------------ */
		BoxLayout panelDchoLay = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(panelDchoLay);
		this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 40));
		this.setBackground(new Color(76, 79, 127));
		
		// TÍTULO > GÉNEROS MUSICALES
		// ------------------------------------------
		JPanel tituloC = new JPanel(); // metemos el título dentro de un panel
		tituloC.setBackground(null);
		JLabel tituloCat = new JLabel("Géneros musicales");
		tituloCat.setFont(new Font("sans-serif", Font.BOLD, 20));
		tituloCat.setForeground(Color.WHITE);
		tituloCat.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
		tituloC.add(tituloCat);
		// main.add(panelDcho, BorderLayout.EAST);
		this.add(tituloC);
		
		// FILTRAR CATÁLOGO
		JPanel filtrar = new JPanel(new BorderLayout());
		TitledBorder catBorder = new TitledBorder("Filtrar catálogo");
		catBorder.setTitleColor(Color.WHITE);
		filtrar.setBorder(catBorder);
		filtrar.setBackground(null);

		// ICONO Y SELECTOR DE GÉNEROS MUSICALES
		// ------------------------------------------
		JPanel comboIcon = new JPanel(new GridLayout(1, 2));
		FlowLayout comboLyt = new FlowLayout();
		comboIcon.setLayout(comboLyt);
		comboIcon.setBackground(null);

		// BOTON PARA REGRESAR AL CATALOGO GENERAL
		// ------------------------------------------
		JPanel regreso = new JPanel();
		catalogoGeneral = new JButton("Regresar al catalogo general");
		catalogoGeneral.setFocusable(false);
		catalogoGeneral.addMouseListener(new MouseListener() {
			
			Font originalFont = null;

		    @SuppressWarnings("unchecked")
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		        originalFont = catalogoGeneral.getFont();
		        @SuppressWarnings("rawtypes")
				Map atributos = originalFont.getAttributes();
		        atributos.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		        catalogoGeneral.setFont(originalFont.deriveFont(atributos));
		    }

		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	catalogoGeneral.setFont(originalFont);
		    }
			
			@Override
			public void mouseReleased(MouseEvent e) {
				catalogoGeneral.setBackground(null);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<Disco> todosDiscos = new ArrayList<Disco>();
				try {
					//Hacemos una busqueda en la BD de los discos con ese genero
					todosDiscos.addAll(FactoriaIntegracion.getFactoria()
							.generaDAODisco().leerTodos());
				} catch (TiendaDatabaseException e1) {
					e1.printStackTrace();
				}
				
				//Actualizamos el catalogo con esos discos
				catalogo.actualizar(todosDiscos);
			}
			
			
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		
		
		catalogoGeneral.setEnabled(false);
		catalogoGeneral.setFont(new Font("sans-serif", Font.BOLD, 11));
		catalogoGeneral.setRolloverEnabled(true);
		
		regreso.setBackground(new Color(76, 79, 127));
		
		regreso.add(catalogoGeneral);
        filtrar.add(regreso, BorderLayout.NORTH);
	
		
		// COMBO-BOX DE GÉNEROS MUSICALES	
		// ------------------------------------------
		JPanel combobox = new JPanel();
		comboIcon.add(new JLabel(Utilidades.createImage("iconos/categorias.png", 50, 50)));
		JComboBox<GeneroDisco> menuCat = new JComboBox<GeneroDisco>(GeneroDisco.values());
		menuCat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				catalogoGeneral.setBackground(Color.WHITE);
				
				GeneroDisco generoEncontrado = null;
				
				//Buscamos el genero que queremos 
				for(GeneroDisco genero : GeneroDisco.values())
				{
					if(menuCat.getSelectedItem().toString()
							.equalsIgnoreCase(genero.toString()))
					{
						generoEncontrado = genero;
					}
				}
				
				ArrayList<Disco> discosPorGenero = new ArrayList<Disco>();
				try {
					//Hacemos una busqueda en la BD de los discos con ese genero
					discosPorGenero.addAll(FactoriaIntegracion.getFactoria()
							.generaDAODisco().leerPorGenero(generoEncontrado));
				} catch (TiendaDatabaseException e1) {
					e1.printStackTrace();
				}
				
				//Actualizamos el catalogo con esos discos
				catalogo.actualizar(discosPorGenero);
				catalogoGeneral.setEnabled(true);
			}
		});
		comboIcon.add(menuCat);
		combobox.add(comboIcon);
		
		filtrar.add(comboIcon, BorderLayout.SOUTH);
		this.add(filtrar);
		
		
		// TÍTULO > INFORMACIÓN DEL DISCO
		// ------------------------------------------
		JPanel info = new JPanel();
		info.setBackground(null);
		this.add(info);
		JLabel tituloInfo = new JLabel("Información");
		tituloInfo.setFont(new Font("sans-serif", Font.BOLD, 20));
		tituloInfo.setForeground(Color.WHITE);
		tituloInfo.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.WHITE));
		info.add(tituloInfo);
		
		// INFORMACIÓN DEL DISCO
		// ------------------------------------------
		JPanel infoPanel = new JPanel();
		infoPanel.setBackground(null);
		BoxLayout infoLayout = new BoxLayout(infoPanel, BoxLayout.Y_AXIS);
		infoPanel.setLayout(infoLayout);
		TitledBorder infoBorder = new TitledBorder("Acerca del disco");
		infoBorder.setTitleColor(Color.WHITE);
		infoPanel.setBorder(infoBorder);
		
		FlowLayout tituloLy = new FlowLayout();
		FlowLayout autorLy = new FlowLayout();
		FlowLayout precioLy = new FlowLayout();
		FlowLayout valoracionLy = new FlowLayout();
		
		JPanel titulo = new JPanel();
		titulo.setBackground(null);
		
		JPanel autor = new JPanel();
		autor.setBackground(null);
		JPanel precio = new JPanel();
		precio.setBackground(null);
		JPanel valoracion = new JPanel();
		valoracion.setBackground(null);
		
		titulo.setLayout(tituloLy);
		autor.setLayout(autorLy);
		precio.setLayout(precioLy);
		valoracion.setLayout(valoracionLy);
		
		titulo.add(new JLabel(Utilidades.createImage("iconos/disc-title.png", 32, 32)));
		tituloLb = new JLabel("Nombre del disco");
		tituloLb.setForeground(Color.WHITE);
		titulo.add(tituloLb);
		
		autor.add(new JLabel(Utilidades.createImage("iconos/disc-author.png", 32, 32)));
		autorLb = new JLabel("Autor del disco");
		autorLb.setForeground(Color.WHITE);
		autor.add(autorLb);
		
		valoracion.add(new JLabel(Utilidades.createImage("iconos/valoracion.png", 32, 32)));
		valoracionLb = new JLabel("Valoracion X/5");
		valoracionLb.setForeground(Color.WHITE);
		valoracion.add(valoracionLb);
		
		precio.add(new JLabel(Utilidades.createImage("iconos/disc-price.png", 32, 32)));
		precioLb = new JLabel("Precio de venta");
		precioLb.setForeground(Color.WHITE);
		precio.add(precioLb);
		
		infoPanel.add(titulo);
		infoPanel.add(autor);
		infoPanel.add(precio);
		infoPanel.add(valoracion);
		
		this.add(infoPanel);
	}
	
	public void showDiscoInfo(Disco disco) {
		tituloLb.setText(disco.getTitulo());
		autorLb.setText(disco.getAutor());
		valoracionLb.setText(disco.getSello());
		precioLb.setText(disco.getPrecioVenta().toString());
	}

}
