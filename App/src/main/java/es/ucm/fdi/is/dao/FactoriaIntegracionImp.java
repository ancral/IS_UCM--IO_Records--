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

	@Override
	public DAODisco generaDAODisco() {
		return new DAODiscoImp();
	}

	@Override
	public DAOPedido generaDAOPedido() {
		return new DAOPedidoImp();
	}

	@Override
	public DAOVenta generaDAOVenta() {
		return new DAOVentaImp();
	}

	@Override
	public DAOUsuario generaDAOUsuario() {
		return new DAOUsuarioImp();
	}

}
