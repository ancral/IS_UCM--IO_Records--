package es.ucm.fdi.is.venta;

import java.util.List;

public interface DAOVenta {
	
	public void crearVenta(Venta venta);
	public void actualizarVenta(Venta antigua, Venta nueva);
	public void borrarVenta(Venta venta);
	public List<Venta> verVentas();

}
