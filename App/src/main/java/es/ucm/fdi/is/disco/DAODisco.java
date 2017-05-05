package es.ucm.fdi.is.disco;

import java.util.List;

public interface DAODisco {

	public void crearDisco(Disco disco);
	public Disco leerDisco(String titulo);
	public List<Disco> leerTodos();
	public void actualizarDisco(Disco antiguo, Disco nuevo);
	public void borrarDisco(Disco disco);
	public List<Disco> leerPorGenero(GeneroDisco genero);
	public boolean existeDisco(Disco disco);
}
