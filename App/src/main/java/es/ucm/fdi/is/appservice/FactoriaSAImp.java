package es.ucm.fdi.is.appservice;

import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.disco.SADiscoImp;
import es.ucm.fdi.is.pedido.SAPedido;
import es.ucm.fdi.is.pedido.SAPedidoImp;
import es.ucm.fdi.is.usuario.SAUsuario;
import es.ucm.fdi.is.usuario.SAUsuarioImp;
import es.ucm.fdi.is.venta.SAVenta;
import es.ucm.fdi.is.venta.SAVentaImp;

public class FactoriaSAImp extends FactoriaSA {

	@Override
	public SADisco generaSADisco() {
		return new SADiscoImp();
	}

	@Override
	public SAPedido generaSAPedido() {
		return new SAPedidoImp();
	}

	@Override
	public SAVenta generaSAVenta() {
		return new SAVentaImp();
	}

	@Override
	public SAUsuario generaSAUsuario() {
		return new SAUsuarioImp();
	}

}
