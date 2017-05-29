package es.ucm.fdi.is.venta;

import java.util.ArrayList;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.Notificacion;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.pedido.Pedido;

public class SAVentaImp implements SAVenta {
	
	private static DAOVenta dao = FactoriaIntegracion.getFactoria().generaDAOVenta();
	
	private ArrayList<TiendaObserver> observers;
	
	private static SAVentaImp saVenta = null;
	
	public static SAVentaImp getSAVenta() {
		if (saVenta == null)
			saVenta = new SAVentaImp();
		
		return saVenta;
	}
	
	private SAVentaImp() {}
	
	@Override
	public void aceptarVentaPedido(Venta ven, Pedido ped) throws TiendaDatabaseException {
		FactoriaIntegracion.getFactoria().generaDAOPedido().eliminarPedido(ped);
		dao.crearVenta(ven);
		FactoriaSA.getFactoria().generaSAPedido().verTodosPedidosParaVentas();
	}

	public void generarFactura(Venta venta) {
		// TODO: Implementar
	}

	public void verBeneficio(Venta venta) {
		// TODO: Implementar
	}

	public void addObverser(TiendaObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(TiendaObserver observer) {
		observers.add(observer);
	}

	public void notifyAll(Notificacion notificacion) {
		// TODO Auto-generated method stub
		
	}


}
