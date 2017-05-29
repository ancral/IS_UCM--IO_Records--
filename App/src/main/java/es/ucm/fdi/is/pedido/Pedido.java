package es.ucm.fdi.is.pedido;

import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.view.Utilidades;

public class Pedido {
	
	private int identificador;
	private List<Disco> discos;
	private String idCliente;
	private TipoRecogida recogida;
	private int finalizado;
	
	public Pedido(int identificador, String idCliente, TipoRecogida recogida) {
		this(identificador, new ArrayList<Disco>(), idCliente, recogida);
	}
	
	public Pedido(int identificador, List<Disco> discos, String idCliente, TipoRecogida recogida) {
		this.identificador = identificador;
		this.discos = discos;
		this.idCliente = idCliente;
		this.recogida = recogida;
		this.finalizado = 0;
	}
	
	public Pedido(int identificador, List<Disco> discos, String idCliente, TipoRecogida recogida, int finalizado) {
		this(identificador, discos, idCliente, recogida);
		this.finalizado = finalizado;
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
	
	public int getFinalizado() {
		return this.finalizado;
	}
	
	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}
	
	public Float precioTotal() {
		Float precio = new Float(0);
		
		for (Disco disco : this.discos) {
			precio += disco.getPrecioVenta();
		}
		
		return Utilidades.round(precio, 2); // Redondeamos a dos decimales
	}

}
