package es.ucm.fdi.is.venta;

import java.sql.Date;

import es.ucm.fdi.is.usuario.Usuario;

public class Venta {
	
	private int identificador;
	private Usuario empleado;
	private Float precioTotal;
	private int idPedido;
	private Date fechaVenta;
	
	public Venta(int identificador, Usuario empleado, Float precioTotal
			, int idPedido, Date fechaVenta) {
		this.identificador = identificador;
		this.empleado = empleado;
		this.precioTotal = precioTotal;
		this.idPedido = idPedido;
		this.fechaVenta = fechaVenta;
	}
	
	public int getId() {
		return this.identificador;
	}
	
	public Date getFecha()
	{
		return this.fechaVenta;
	}
	
	public int getIdPedido()
	{
		return this.idPedido;
	}
	
	public Usuario getEmpleado() {
		return this.empleado;
	}
	
	public Float getPrecio() {
		return this.precioTotal;
	}

}
