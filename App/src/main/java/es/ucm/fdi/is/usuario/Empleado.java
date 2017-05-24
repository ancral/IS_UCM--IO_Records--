package es.ucm.fdi.is.usuario;

import java.sql.Date;

import es.ucm.fdi.is.pedido.Pedido;

public class Empleado extends Usuario {
	
	private RangoEmpleado rango;
	private Float salario;
	private Date antiguedad;
	
	
	public Empleado(String nif)
	{
		super(nif);
	}
	
	public Empleado(String nif, String clave, String nombre, String direccion, Date fechaNacimiento,
					 RangoEmpleado rango, String cargo, Float salario, Date antiguedad, Pedido pedido) {
		
		super(nif, clave, nombre, direccion, fechaNacimiento, TipoUsuario.EMPLEADO, pedido);
		this.rango = rango;
		this.salario = salario;
		this.antiguedad = antiguedad;
	}
	
	public RangoEmpleado getRango() {
		return this.rango;
	}
	
	public Float getSalario() {
		return this.salario;
	}
	
	public Date getAntiguedad() {
		return this.antiguedad;
	}

}
