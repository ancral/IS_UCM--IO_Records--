package es.ucm.fdi.is.pedido;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.disco.Disco;

public class Pedido {
	
	private int identificador;
	private List<Disco> discos;
	private String idCliente;
	private TipoRecogida recogida;
	
	public Pedido(int identificador, String idCliente, TipoRecogida recogida) {
		this(identificador, new ArrayList<Disco>(), idCliente, recogida);
	}
	
	public Pedido(int identificador, List<Disco> discos, String idCliente, TipoRecogida recogida) {
		this.identificador = identificador;
		this.discos = discos;
		this.idCliente = idCliente;
		this.recogida = recogida;
	}
	
	public void meterDisco(Disco disco) {
		this.discos.add(disco);
	}
	
	public void quitarDisco(Disco disco) {
		this.discos.remove(disco);
	}
	
	public int getId() {
		return this.identificador;
	}
	
	public List<Disco> getDiscos() {
		return this.discos;
	}
	
	public boolean esVacio() {
		return discos.isEmpty();
	}
	
	public String getCliente()
	{
		return idCliente;
	}
	
	public TipoRecogida getTipoRecogida() {
		return this.recogida;
	}
	
	public Float precioTotal() {
		return null;
		// TODO: Implementar
	}

}
