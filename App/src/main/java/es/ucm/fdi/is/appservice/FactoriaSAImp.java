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
	
	private static FactoriaSAImp factoriaSA = null;
	
	public static FactoriaSAImp getFactoriaSA() {
		if (factoriaSA == null)
			factoriaSA = new FactoriaSAImp();
		
		return factoriaSA;
	}
	
	private FactoriaSAImp() {}

	@Override
	public SADisco generaSADisco() {
		return SADiscoImp.getSADisco();
	}

	@Override
	public SAPedido generaSAPedido() {
		return SAPedidoImp.getSAPedido();
	}

	@Override
	public SAVenta generaSAVenta() {
		return SAVentaImp.getSAVenta();
	}

	@Override
	public SAUsuario generaSAUsuario() {
		return SAUsuarioImp.getSAUsuario();
	}

}
