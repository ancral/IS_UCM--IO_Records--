package es.ucm.fdi.is.usuario;

import java.sql.Date;

import es.ucm.fdi.is.pedido.Pedido;


public class Cliente extends Usuario {
	
	private TipoCliente tipo;
	
	public Cliente(String nif) {
		super(nif);
	}
	
	public Cliente(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoCliente tipo, Pedido pedido) {
		super(nif, clave, nombre, direccion, fechaNacimiento, TipoUsuario.CLIENTE, pedido);
		this.tipo = tipo;
	}
	
	public TipoCliente getTipoCliente() {
		return this.tipo;
	}

}
