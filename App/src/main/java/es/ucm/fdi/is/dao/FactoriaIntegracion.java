package es.ucm.fdi.is.dao;

import es.ucm.fdi.is.disco.DAODisco;
import es.ucm.fdi.is.pedido.DAOPedido;
import es.ucm.fdi.is.usuario.DAOUsuario;
import es.ucm.fdi.is.venta.DAOVenta;

public abstract class FactoriaIntegracion {
	
	/* Patrón Singleton en la creación de factorías abstractas */

	private static FactoriaIntegracion factoria;
	
	public static FactoriaIntegracion getFactoria() {
		
		if (factoria == null)
			factoria = new FactoriaIntegracionImp();
		
		return factoria;
	}
	
	public abstract DAODisco generaDAODisco();
	public abstract DAOPedido generaDAOPedido();
	public abstract DAOVenta generaDAOVenta();
	public abstract DAOUsuario generaDAOUsuario();
	
}
