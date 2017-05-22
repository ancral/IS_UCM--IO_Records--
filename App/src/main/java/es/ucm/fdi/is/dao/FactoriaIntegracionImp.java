package es.ucm.fdi.is.dao;

import es.ucm.fdi.is.disco.DAODisco;
import es.ucm.fdi.is.disco.DAODiscoImp;
import es.ucm.fdi.is.pedido.DAOPedido;
import es.ucm.fdi.is.pedido.DAOPedidoImp;
import es.ucm.fdi.is.usuario.DAOUsuario;
import es.ucm.fdi.is.usuario.DAOUsuarioImp;
import es.ucm.fdi.is.venta.DAOVenta;
import es.ucm.fdi.is.venta.DAOVentaImp;

public class FactoriaIntegracionImp extends FactoriaIntegracion {
	
	private static FactoriaIntegracionImp factoriaIntegracion = null;
	
	public static FactoriaIntegracionImp getFactoriaIntegracion() {
		if (factoriaIntegracion == null)
			factoriaIntegracion = new FactoriaIntegracionImp();
		
		return factoriaIntegracion;
	}
	
	private FactoriaIntegracionImp() {}

	@Override
	public DAODisco generaDAODisco() {
		return DAODiscoImp.getDaoDisco();
	}

	@Override
	public DAOPedido generaDAOPedido() {
		return DAOPedidoImp.getDaoPedido();
	}

	@Override
	public DAOVenta generaDAOVenta() {
		return DAOVentaImp.getDaoVenta();
	}

	@Override
	public DAOUsuario generaDAOUsuario() {
		return DAOUsuarioImp.getDAOUsuario();
	}

}
