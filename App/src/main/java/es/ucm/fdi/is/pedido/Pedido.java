package es.ucm.fdi.is.pedido;

import java.util.List;

import es.ucm.fdi.is.disco.Disco;

public class Pedido {
	
	private String identificador;
	private List<Disco> discos;
	private TipoRecogida recogida;
	
	public Pedido(String identificador, List<Disco> discos, TipoRecogida recogida) {
		this.identificador = identificador;
		this.discos = discos;
		this.recogida = recogida;
	}
	
	public String getId() {
		return this.identificador;
	}
	
	public List<Disco> getDiscos() {
		return this.discos;
	}
	
	public TipoRecogida getTipoRecogida() {
		return this.recogida;
	}
	
	public Float precioTotal() {
		return null;
		// TODO: Implementar
	}

}
