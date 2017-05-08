package es.ucm.fdi.is.venta;

import es.ucm.fdi.is.usuario.Empleado;

public class Venta {
	
	private String identificador;
	private Empleado empleado;
	private Float precioTotal;
	
	public Venta(String identificador, Empleado empleado, Float precioTotal) {
		this.identificador = identificador;
		this.empleado = empleado;
		this.precioTotal = precioTotal;
	}
	
	public String getId() {
		return this.identificador;
	}
	
	public Empleado getEmpleado() {
		return this.empleado;
	}
	
	public Float calcularPrecio() {
		return this.precioTotal;
		// TODO: Implementar
	}

}
