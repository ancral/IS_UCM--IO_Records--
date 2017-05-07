package es.ucm.fdi.is.dao;

public class TiendaDatabaseException extends Exception {

	private static final long serialVersionUID = 6492871758546673602L;

	public TiendaDatabaseException() {
		super("Error en la base de datos.");
	}
	
	public TiendaDatabaseException(String mensaje) {
		super(mensaje);
	}
	
	@Override
	public String toString() {
		return "TiendaDatabaseException: " + this.getMessage();
	}

}
