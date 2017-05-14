package es.ucm.fdi.is.disco;

public enum Valoracion {
	
	MUY_BUENA("Muy buena"),
	BUENA("Buena"),
	REGULAR("Regular"),
	MALA("Mala"),
	MUY_MALA("Muy mala");
	
	private String descripcion;
	
	Valoracion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	Valoracion() {
		this.descripcion = null;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}
	
}
