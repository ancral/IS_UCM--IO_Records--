package es.ucm.fdi.is.mvc;

public interface TiendaObservable {
	
	void addObverser(TiendaObserver observer);
	void removeObserver(TiendaObserver observer);
	
}
