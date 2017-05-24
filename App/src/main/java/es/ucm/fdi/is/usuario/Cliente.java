package es.ucm.fdi.is.usuario;

import java.sql.Date;

import es.ucm.fdi.is.pedido.Pedido;
import es.ucm.fdi.is.pedido.TipoRecogida;

public class Cliente extends Usuario {
	
	private TipoCliente tipo;
	private Pedido pedido;
	
	public Cliente(String nif)
	{
		super(nif);
		this.pedido = new Pedido("0000", this.getNif(), TipoRecogida.TIENDA);
	}
	
	public Cliente(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoCliente tipo) {
		super(nif, clave, nombre, direccion, fechaNacimiento, TipoUsuario.CLIENTE);
		this.tipo = tipo;
	}
	
	public TipoCliente getTipoCliente() {
		return this.tipo;
	}

}
