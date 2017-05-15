package es.ucm.fdi.is.venta;

import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabaseException;

public interface DAOVenta {
	
	public void crearVenta(Venta venta) throws TiendaDatabaseException;
	public void actualizarVenta(Venta antigua, Venta nueva) throws TiendaDatabaseException;
	public void borrarVenta(Venta venta) throws TiendaDatabaseException;
	public List<Venta> verVentas() throws TiendaDatabaseException;

}
