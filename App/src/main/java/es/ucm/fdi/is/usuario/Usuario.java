package es.ucm.fdi.is.usuario;

import java.sql.Date;

public abstract class Usuario {
	
	private String nif;
	private String clave;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	private TipoUsuario tipo;
	
	public Usuario(String nif)
	{
		this.nif = nif;
	}
	
	public Usuario(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoUsuario tipo) {
		this.nif = nif;
		this.clave = clave;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = (this.tipo != null) ? TipoUsuario.CLIENTE_EMPLEADO : tipo;
	}
	
	public String getNif() {
		return this.nif;
	}
	
	public String getClave() {
		return this.clave;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	
	public String getDireccion() {
		return this.direccion;
	}
	
	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}
	
	public TipoUsuario getTipo() {
		return this.tipo;
	}
}
