package es.ucm.fdi.is.disco;

import java.util.Date;
import java.util.List;

public class Disco {
	
	private String titulo;
	private String autor;
	private Date fechaSalida;
	private String sello;
	private GeneroDisco genero;
	private Integer duracion;
	private Valoracion valoracion;
	private Float precioCompra;
	private Float precioVenta;
	private List<Cancion> listaCanciones;
	private OfertaDisco oferta;
	
	/* Constructora con oferta */
	public Disco(String titulo, String autor, Date fechaSalida, String sello, GeneroDisco genero, Integer duracion,
					Valoracion valoracion, Float precioCompra, Float precioVenta, List<Cancion> listaCanciones, OfertaDisco oferta) {
	
		this.titulo = titulo;
		this.setAutor(autor);
		this.setFechaSalida(fechaSalida);
		this.setSello(sello);
		this.setGenero(genero);
		this.setDuracion(duracion);
		this.setValoracion(valoracion);
		this.setPrecioCompra(precioCompra);
		this.setPrecioVenta(precioVenta);
		this.setListaCanciones(listaCanciones);
		this.setOferta(oferta);
	}
	
	/* Constructora sin oferta */
	public Disco(String titulo, String autor, Date fechaSalida, String sello, GeneroDisco genero, Integer duracion,
			Valoracion valoracion, Float precioCompra, Float precioVenta, List<Cancion> listaCanciones) {

		this.titulo = titulo;
		this.setAutor(autor);
		this.setFechaSalida(fechaSalida);
		this.setSello(sello);
		this.setGenero(genero);
		this.setDuracion(duracion);
		this.setValoracion(valoracion);
		this.setPrecioCompra(precioCompra);
		this.setPrecioVenta(precioVenta);
		this.setListaCanciones(listaCanciones);
		this.setOferta(null);
	}
	
	public Disco(Disco antiguo, OfertaDisco oferta)
	{
		this.setTitulo(antiguo.getTitulo());
		this.setAutor(antiguo.getAutor());
		this.setFechaSalida(antiguo.getFechaSalida());
		this.setSello(antiguo.getSello());
		this.setGenero(antiguo.getGenero());
		this.setDuracion(antiguo.getDuracion());
		this.setValoracion(antiguo.getValoracion());
		this.setPrecioCompra(antiguo.getPrecioCompra());
		this.setPrecioVenta(antiguo.getPrecioVenta());
		this.setListaCanciones(antiguo.getListaCanciones());
		this.setOferta(oferta);
	}
	
	public Disco(String titulo)
	{
		this.setTitulo(titulo);
	}
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getSello() {
		return sello;
	}

	public void setSello(String sello) {
		this.sello = sello;
	}

	public GeneroDisco getGenero() {
		return genero;
	}

	public void setGenero(GeneroDisco genero) {
		this.genero = genero;
	}

	public Integer getDuracion() {
		return duracion;
	}

	public void setDuracion(Integer duracion) {
		this.duracion = duracion;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public Float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(Float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public Float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(Float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public List<Cancion> getListaCanciones() {
		return listaCanciones;
	}

	public void setListaCanciones(List<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}

	public OfertaDisco getOferta() {
		return oferta;
	}

	public void setOferta(OfertaDisco oferta) {
		this.oferta = oferta;
	}
	

}
