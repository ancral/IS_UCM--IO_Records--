package es.ucm.fdi.is.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;

public class CatalogoDiscos extends JScrollPane {

	private static final long serialVersionUID = 3809398699373783819L;
	
	private static CatalogoDiscos catalogoDiscos = null;
	
	private TiendaView tienda;
	private JPanel catalogo;
	
	public static CatalogoDiscos getCatalogoDiscos(TiendaView tienda) {
		if (catalogoDiscos == null)
			catalogoDiscos = new CatalogoDiscos(tienda);
		
		return catalogoDiscos;
	}

	private CatalogoDiscos(TiendaView tienda) {
		this.tienda = tienda;
		
		catalogo = new JPanel();
		catalogo.setBackground(new Color(190, 190, 242));
		this.setViewportView(catalogo);
		this.setPreferredSize(new Dimension(670, 440));
		this.getVerticalScrollBar().setUnitIncrement(16); // aumenta la velocidad de barra de scroll
		
		
		
		ArrayList<Disco> discos = new ArrayList<Disco>();
		try {
			discos.addAll(FactoriaIntegracion.getFactoria().generaDAODisco().leerTodos());
		} catch (TiendaDatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int columnas = discos.size() < 3 ? 1 : 3;
		int filas = discos.size() < 3 ? 1 : discos.size() / columnas;
		GridLayout catalogoLayout = new GridLayout(filas, columnas, 5, 5);
		catalogo.setLayout(catalogoLayout);
		
		actualizar(discos);
		
	}
	
	public void actualizar(ArrayList<Disco> discos) {
		int columnas = discos.size() < 3 ? 1 : 3;
		int filas = discos.size() < 3 ? 1 : discos.size() / columnas;
		Iterator<Disco> discosIt = discos.iterator();
		
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				Disco disco = discosIt.next();
				
				final Caratula car = new Caratula(disco);
				car.addMouseListener(new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						tienda.setVisible(false);
						// hacemos visible la información del disco
						DiscoView.getDiscoView(tienda).setVisible(true); 
					}

					public void mousePressed(MouseEvent e) {}

					public void mouseReleased(MouseEvent e) {}

					public void mouseEntered(MouseEvent e) {
						// Mostramos el titulo de un disco cuando el curso se posa encima
						tienda.showTituloDisco(car.getDisco().getTitulo());
						tienda.showDiscoInfo(car.getDisco());
					}

					public void mouseExited(MouseEvent e) {}
					
				});
				
				catalogo.add(car);
			}
		}
	}
}
