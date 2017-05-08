package es.ucm.fdi.is.usuario;

import java.util.Date;

public class Cliente extends Usuario {
	
	private TipoCliente tipo;
	
	public Cliente(String nif, String clave, String nombre, String direccion, Date fechaNacimiento, TipoCliente tipo) {
		super(nif, clave, nombre, direccion, fechaNacimiento);
		this.tipo = tipo;
	}
	
	public TipoCliente getTipoCliente() {
		return this.tipo;
	}

}
