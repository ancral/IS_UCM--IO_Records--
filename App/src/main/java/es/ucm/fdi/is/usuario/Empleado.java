package es.ucm.fdi.is.usuario;

import java.util.Date;

public class Empleado extends Usuario {
	
	private RangoEmpleado rango;
	private String cargo;
	private Float salario;
	private Date antiguedad;
	
	public Empleado(String nif, String clave, String nombre, String direccion, Date fechaNacimiento,
					 RangoEmpleado rango, String cargo, Float salario, Date antiguedad) {
		
		super(nif, clave, nombre, direccion, fechaNacimiento);
		this.rango = rango;
		this.cargo = cargo;
		this.salario = salario;
		this.antiguedad = antiguedad;
	}
	
	public RangoEmpleado getRango() {
		return this.rango;
	}
	
	public String getCargo() {
		return this.cargo;
	}
	
	public Float getSalario() {
		return this.salario;
	}
	
	public Date getAntiguedad() {
		return this.antiguedad;
	}

}
