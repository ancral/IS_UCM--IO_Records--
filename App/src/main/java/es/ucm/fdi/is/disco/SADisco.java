package es.ucm.fdi.is.disco;

import java.util.List;

public interface SADisco {
	
	public void crearDisco(Disco disco);
	public boolean existeDisco(Disco disco);
	public void descatalogarDisco(Disco disco);
	public void actualizarDisco(Disco antiguo, Disco nuevo);
	public List<Disco> leerPorGenero(GeneroDisco genero);
	public void crearOferta(Disco disco, OfertaDisco oferta);
	public List<Disco> leerTodos();

}
