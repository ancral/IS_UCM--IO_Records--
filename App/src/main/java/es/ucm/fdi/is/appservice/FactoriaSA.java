package es.ucm.fdi.is.appservice;

import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.pedido.SAPedido;
import es.ucm.fdi.is.usuario.SAUsuario;
import es.ucm.fdi.is.venta.SAVenta;

public abstract class FactoriaSA {
	
	private static FactoriaSA factoria;
	
	
	public static FactoriaSA getFactoria() {
		if (factoria == null)
			factoria = new FactoriaSAImp();
		
		return factoria;
	}
	
	public abstract SADisco generaSADisco();
	public abstract SAPedido generaSAPedido();
	public abstract SAVenta generaSAVenta();
	public abstract SAUsuario generaSAUsuario();

}
