package es.ucm.fdi.is.disco;

public enum GeneroDisco {
	
	ROCK("Rock"),
	POP("Pop"),
	INDIE("Indie"),
	ELECTRONICA("Electrónica"),
	CLASICA("Clásica"),
	BANDAS_SONORAS("Bandas sonoras");
	
	private String nombre;
	
	GeneroDisco(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}

}
