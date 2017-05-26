package es.ucm.fdi.is.usuario;

import java.sql.Date;
import java.util.ArrayList;

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
					 RangoEmpleado rango, String cargo, Float salario, Date antiguedad, ArrayList<Pedido> pedidos, Pedido pedidoActual) {
		
		super(nif, clave, nombre, direccion, fechaNacimiento, TipoUsuario.EMPLEADO, pedidos, pedidoActual);
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
