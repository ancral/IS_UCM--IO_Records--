package es.ucm.fdi.is.disco;

public class Cancion {
	
	private String titulo;
	private Integer duracion;
	
	public Cancion(String titulo, Integer duracion) {
		this.titulo = titulo;
		this.duracion = duracion;
	}
	
	public Cancion(String titulo) {
		this.titulo = titulo;
		this.duracion = 0;
	}
	
	public String getTitulo() {
		return this.titulo;
	}
	
	public Integer getDuracion() {
		return this.duracion;
	}

	public String toString()
	{
		return titulo;
	}
}
