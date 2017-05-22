package es.ucm.fdi.is.appservice;

import es.ucm.fdi.is.disco.SADisco;
import es.ucm.fdi.is.pedido.SAPedido;
import es.ucm.fdi.is.usuario.SAUsuario;
import es.ucm.fdi.is.venta.SAVenta;

public abstract class FactoriaSA {
	
	/* Patrón Singleton en la creación de factorías abstractas */
	
	private static FactoriaSA factoria;
	
	
	public static FactoriaSA getFactoria() {
		if (factoria == null)
			factoria = FactoriaSAImp.getFactoriaSA();
		
		return factoria;
	}
	
	
	public abstract SADisco generaSADisco();
	public abstract SAPedido generaSAPedido();
	public abstract SAVenta generaSAVenta();
	public abstract SAUsuario generaSAUsuario();

}
