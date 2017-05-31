package es.ucm.fdi.is.disco;

public class OfertaDisco {
	
	private String descripcion;
	private float porcentajeDescuento;
	
	public OfertaDisco(String descripcion, float porcentajeDescuento) {
		this.descripcion = descripcion;
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public OfertaDisco(float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
		this.descripcion = null;
	}
	
	public Float precioNuevo(Disco disco) {
		return disco.getPrecioVenta() * (1 - this.porcentajeDescuento / 100);
	}
	
	public String getDescripcion() {
		return this.descripcion;
	}
	
	public float getPorcentaje()
	{
		return this.porcentajeDescuento;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
