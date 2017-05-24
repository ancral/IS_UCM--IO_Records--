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
	private Float valoracion;
	private Float precioCompra;
	private Float precioVenta;
	private List<Cancion> listaCanciones;
	private OfertaDisco oferta;
	private int numVotaciones;
	private Float valoracionesTotal = new Float(0);
	private String caratula;
	
	/* Constructora con oferta */
	public Disco(String titulo, String autor, Date fechaSalida, String sello, GeneroDisco genero, Integer duracion,
			Float valoracion, Float precioCompra, Float precioVenta, List<Cancion> listaCanciones, OfertaDisco oferta, String caratula
			, int numVotaciones) {

		this(titulo, autor, fechaSalida, sello, genero, duracion, valoracion, precioCompra, precioVenta, listaCanciones, caratula);
		this.oferta = oferta;
		this.numVotaciones = numVotaciones;
	}
	
	
	/* Constructora copia*/
	public Disco(Disco copia)
	{
		this.titulo = copia.getTitulo();
		this.autor = copia.getAutor();
		this.fechaSalida = copia.getFechaSalida();
		this.sello = copia.getSello();
		this.genero = copia.getGenero();
		this.duracion = copia.getDuracion();
		this.valoracion = copia.getValoracion();
		this.precioCompra = copia.getPrecioCompra();
		this.precioVenta = copia.getPrecioVenta();
		this.listaCanciones = copia.getListaCanciones();
		this.oferta = copia.getOferta();
		this.numVotaciones = copia.getNumVotaciones();
		this.valoracionesTotal = copia.getValoracionTotal();
		this.caratula = copia.getCaratula();
	}
	
	/* Constructora sin oferta */
	public Disco(String titulo, String autor, Date fechaSalida, String sello, GeneroDisco genero, Integer duracion,
			Float valoracion, Float precioCompra, Float precioVenta, List<Cancion> listaCanciones, String caratula) {

		this.titulo = titulo;
		this.valoracionesTotal = valoracion;
		this.valoracion = valoracion;
		this.numVotaciones = 0;
		this.setAutor(autor);
		this.setFechaSalida(fechaSalida);
		this.setSello(sello);
		this.setGenero(genero);
		this.setDuracion(duracion);
		this.setPrecioCompra(precioCompra);
		this.setPrecioVenta(precioVenta);
		this.setListaCanciones(listaCanciones);
		this.setOferta(null);
		this.caratula = caratula;

	}
	
	public Disco(Disco antiguo, OfertaDisco oferta)
	{
		this.setTitulo(antiguo.getTitulo());
		this.valoracionesTotal = valoracion;
		this.valoracion = antiguo.getValoracion();
		this.numVotaciones = antiguo.getNumVotaciones();
		this.setAutor(antiguo.getAutor());
		this.setFechaSalida(antiguo.getFechaSalida());
		this.setSello(antiguo.getSello());
		this.setGenero(antiguo.getGenero());
		this.setDuracion(antiguo.getDuracion());
		this.setPrecioCompra(antiguo.getPrecioCompra());
		this.setPrecioVenta(antiguo.getPrecioVenta());
		this.setListaCanciones(antiguo.getListaCanciones());
		this.setOferta(oferta);

	}

	public Disco(Disco antiguo, Float valoracion) {
		this.valoracionesTotal = valoracion;
		this.numVotaciones = antiguo.getNumVotaciones();
		this.valoracion = valoracion;
		this.setTitulo(antiguo.getTitulo());
		this.setAutor(antiguo.getAutor());
		this.setFechaSalida(antiguo.getFechaSalida());
		this.setSello(antiguo.getSello());
		this.setGenero(antiguo.getGenero());
		this.setDuracion(antiguo.getDuracion());
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

	public Float getValoracion() {
		return valoracion;
	}

	public Float getValoracionTotal()
	{
		return valoracionesTotal;
	}
	
	public void setValoracion(Float val) {
		this.valoracion = val;
		this.valoracionesTotal += val;
	}
	
	public int getNumVotaciones()
	{
		return numVotaciones;
	}
	
	public void setNumVotaciones(int x)
	{
		this.numVotaciones = this.numVotaciones + x;
		System.out.println(numVotaciones);
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
	
	public String getCaratula() {
		return this.caratula;
	}
	

}
