package es.ucm.fdi.is.usuario;

import java.sql.Date;

import es.ucm.fdi.is.pedido.Pedido;

public abstract class Usuario {
	
	private String nif;
	private String clave;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	private TipoUsuario tipo;
	private Pedido pedido;
	
	public Usuario(String nif)
	{
		this.nif = nif;
	}
	
	public Usuario(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoUsuario tipo, Pedido pedido) {
		this.nif = nif;
		this.clave = clave;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = (this.tipo != null) ? TipoUsuario.CLIENTE_EMPLEADO : tipo;
		this.pedido = pedido;
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
	
	public Pedido getPedido() {
		return this.pedido;
	}
	
	public void setNif(String nif) {
		this.nif = nif;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}
	
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
}
