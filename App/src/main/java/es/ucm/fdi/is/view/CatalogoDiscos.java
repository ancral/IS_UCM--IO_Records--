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

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class CatalogoDiscos extends JScrollPane implements TiendaObserver {

	private static final long serialVersionUID = 3809398699373783819L;

	private static CatalogoDiscos catalogoDiscos = null;

	private static TiendaController control = TiendaController.getTiendaController();
	private static DiscoController discoControl = DiscoController.getDiscoController();
	
	private TiendaView tienda;
	private JPanel catalogo;

	private int tamanyoCatalogo;
	private static int VACIO = 0;

	public static CatalogoDiscos getCatalogoDiscos(TiendaView tienda) {
		if (catalogoDiscos == null)
			catalogoDiscos = new CatalogoDiscos(tienda);

		return catalogoDiscos;
	}

	private CatalogoDiscos(TiendaView tienda) {
		this.tienda = tienda;
		control.addObserver(this);
		discoControl.addObserver(this);

		catalogo = new JPanel();
		tamanyoCatalogo = 0;

		catalogo.setBackground(new Color(190, 190, 242));
		this.setViewportView(catalogo);
		this.setPreferredSize(new Dimension(700, 440));
		this.getVerticalScrollBar().setUnitIncrement(16); // aumenta la velocidad de barra de scroll

		control.leerTodoCatalogo();

	}

	public void actualizar(ArrayList<?> arrayList) {

		//Si tenemos discos en el catalogo, tenemos que borrarlo, para poder actualizarlo
		if(tamanyoCatalogo!=VACIO) borrar();

		anyadir(arrayList);
	}

	private void borrar()
	{
		tamanyoCatalogo = 0;
		catalogo.removeAll();
		catalogo.repaint();
		catalogo.revalidate();
	}

	private void anyadir(ArrayList<?> arrayList)
	{

		//Calculamos el ajuste del layout y a su vez el reajuste del tamaño de las caratulas
		int columnas = (arrayList.size() < 3) ? 2 : (arrayList.size()%3==0) ? (arrayList.size() / 3) 
				: (arrayList.size() / 3)+1;

		int filas = (columnas < 3) ? 1 :(arrayList.size()%3==0) ? (arrayList.size() / columnas) 
				: (arrayList.size() / columnas)+1;

		GridLayout catalogoLayout = new GridLayout(filas, columnas, 5, 5);
		catalogo.setLayout(catalogoLayout);

		Iterator<?> discosIt = arrayList.iterator();

		//Tenemos que poner (&& discosIt.hasNext()), para que ponga la fila restante que faltase
		for (int i = 0; i < filas && discosIt.hasNext(); i++) {
			for (int j = 0; j < columnas && discosIt.hasNext(); j++) {

				final Disco disco = (Disco) discosIt.next();

				final Caratula car = new Caratula(disco, filas, columnas);
				car.addMouseListener(new MouseListener() {

					public void mouseClicked(MouseEvent e) {
						tienda.setVisible(false);
						// hacemos visible la información del disco

						System.out.println(disco.getTitulo().toString());
						new DiscoView(tienda, CatalogoDiscos.this, disco).setVisible(true); 
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

				tamanyoCatalogo++;
				
				// Solamente mostramos los discos que esten EN STOCK
				if (disco.getDescatalogado() != Disco.DESCATALOGADO) {
					catalogo.add(car);
				}
			}
		}
	}

	public void notify(Notificacion notificacion) {
		switch (notificacion.getNotificacion()) {

		case LEER_TODOS:
		case LEER_POR_GENERO:
			actualizar(notificacion.getDiscosOpedido());
			break;
			
		case DISCO_ACTUALIZADO:
		case DISCO_CREADO:
		case DISCO_BORRADO:
			TiendaController.getTiendaController().leerTodoCatalogo();
			break;

		default:
			break;

		}
	}

}
