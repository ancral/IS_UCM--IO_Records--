package es.ucm.fdi.is.venta;

import es.ucm.fdi.is.mvc.TiendaObservable;

public interface SAVenta extends TiendaObservable {
	
	public void generarFactura(Venta venta);
	public void verBeneficio(Venta venta);

}
