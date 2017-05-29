package es.ucm.fdi.is.venta;

import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObservable;
import es.ucm.fdi.is.pedido.Pedido;

public interface SAVenta extends TiendaObservable {
	
	public void aceptarVentaPedido(Venta ven, Pedido ped) throws TiendaDatabaseException;
	public void generarFactura(Venta venta);
	public void verBeneficio(Venta venta);
	public void notifyAll(Notificacion notificacion);

}
