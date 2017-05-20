package es.ucm.fdi.is.disco;

public enum GeneroDisco {
	
	ROCK("Rock"),
	POP("Pop"),
	INDIE("Indie"),
	ELECTRONICA("Electronica"),
	CLASICA("Clasica"),
	BANDAS_SONORAS("Bandas sonoras"),
	RAP("Rap");
	
	private String nombre;
	
	GeneroDisco(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString(){
		return this.nombre;
	}

}
