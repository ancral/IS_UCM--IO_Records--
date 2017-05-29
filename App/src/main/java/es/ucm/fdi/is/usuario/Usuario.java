package es.ucm.fdi.is.usuario;

import java.sql.Date;
import java.util.ArrayList;

import es.ucm.fdi.is.pedido.Pedido;

public abstract class Usuario {
	
	private String nif;
	private String clave;
	private String nombre;
	private String direccion;
	private Date fechaNacimiento;
	private TipoUsuario tipo;
	private ArrayList<Pedido> pedidos;
	private Pedido pedidoActual;
	
	public Usuario(String nif)
	{
		this.nif = nif;
	}
	
	public Usuario(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoUsuario tipo, ArrayList<Pedido> pedidos, Pedido pedidoActual) {
		this.nif = nif;
		this.clave = clave;
		this.nombre = nombre;
		this.direccion = direccion;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.pedidos = pedidos;
		this.pedidoActual = pedidoActual;
	}
	
	public boolean isEmpleado() {
		return this.tipo == TipoUsuario.EMPLEADO;
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
		return this.pedidoActual;
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
		this.pedidoActual = pedido;
	}
	
	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	
}
