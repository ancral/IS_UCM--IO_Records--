package es.ucm.fdi.is.pedido;

public enum TipoRecogida {
	
	TIENDA,
	DOMICILIO;
	
	private String observaciones;
	
	TipoRecogida(String observaciones) {
		this.observaciones = observaciones;
	}
	
	TipoRecogida() {
		this.observaciones = "Ninguna observación";
	}
	
	public String getObservaciones() {
		return this.observaciones;
	}

}
