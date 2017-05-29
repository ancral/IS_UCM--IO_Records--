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
	
	public static Float getNumValoracion(String valoracion) {
		Float val = new Float(0);
		
		switch (valoracion) {
		case "MUY_BUENA":
			val = new Float(5);
			break;
		case "BUENA":
			val = new Float(4);
			break;
		case "MALA":
			val = new Float(3);
			break;
		case "MUY_MALA":
			val = new Float(2);
			break;
		}
		
		return val;
	}
	
	@Override
	public String toString() {
		return this.descripcion;
	}
	
}
