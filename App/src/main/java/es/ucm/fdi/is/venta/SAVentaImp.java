package es.ucm.fdi.is.venta;

import java.util.ArrayList;

import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;

public class SAVentaImp implements SAVenta {
	
	private DAOVenta dao;
	private ArrayList<TiendaObserver> observers;
	
	private static SAVentaImp saVenta = null;
	
	public static SAVentaImp getSAVenta() {
		if (saVenta == null)
			saVenta = new SAVentaImp();
		
		return saVenta;
	}
	
	private SAVentaImp() {}

	public void generarFactura(Venta venta) {
		// TODO: Implementar
	}

	public void verBeneficio(Venta venta) {
		// TODO: Implementar
	}

	public void addObverser(TiendaObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observers.add(observer);
	}

	public void notifyAll(Notificacion notificacion) {
		// TODO Auto-generated method stub
		
	}

}
