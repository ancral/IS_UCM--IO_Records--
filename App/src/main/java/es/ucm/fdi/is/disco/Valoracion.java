package es.ucm.fdi.is.disco;

public enum Valoracion {
	
	MUY_BUENA,
	BUENA,
	REGULAR,
	MALA,
	MUY_MALA;
	
	private String descripcion;
	
	Valoracion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	Valoracion() {
		this.descripcion = null;
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	
}
