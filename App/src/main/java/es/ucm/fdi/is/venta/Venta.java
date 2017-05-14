package es.ucm.fdi.is.venta;

import java.sql.Date;

import es.ucm.fdi.is.usuario.Empleado;

public class Venta {
	
	private String identificador;
	private Empleado empleado;
	private Float precioTotal;
	private String idPedido;
	private Date fechaVenta;
	
	public Venta(String identificador, Empleado empleado, Float precioTotal
			, String idPedido, Date fechaVenta) {
		this.identificador = identificador;
		this.empleado = empleado;
		this.precioTotal = precioTotal;
		this.idPedido = idPedido;
		this.fechaVenta = fechaVenta;
	}
	
	public String getId() {
		return this.identificador;
	}
	
	public Date getFecha()
	{
		return this.fechaVenta;
	}
	
	public String getIdPedido()
	{
		return this.idPedido;
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}
	
	public Float calcularPrecio() {
		return this.precioTotal;
		// TODO: Implementar
	}

}
