package es.ucm.fdi.is.disco;

public class OfertaDisco {
	
	private String descripcion;
	private int porcentajeDescuento;
	
	public OfertaDisco(String descripcion, int porcentajeDescuento) {
		this.descripcion = descripcion;
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public OfertaDisco(int porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
		this.descripcion = null;
	}
	
	public Float precioNuevo(Disco disco) {
		return disco.getPrecioVenta() * (1 - this.porcentajeDescuento);
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public void setDescuento(int descuento) {
		this.porcentajeDescuento = descuento;
	}

}
