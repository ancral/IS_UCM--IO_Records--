package es.ucm.fdi.is.usuario;

public enum TipoUsuario {
	CLIENTE("Cliente"),
	EMPLEADO("Empleado"),
	CLIENTE_EMPLEADO("Cliente-Empleado");
	
	private String tipo;
	
	TipoUsuario(String tipo) {
		this.tipo = tipo;
	}
	
	public String toString() {
		return this.tipo;
	}
}
