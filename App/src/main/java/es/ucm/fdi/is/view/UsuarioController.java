package es.ucm.fdi.is.view;

import es.ucm.fdi.is.appservice.FactoriaSA;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.mvc.TiendaObserver;
import es.ucm.fdi.is.usuario.SAUsuario;
import es.ucm.fdi.is.usuario.Usuario;

public class UsuarioController {
	
	private static UsuarioController usuarioController = null;
	
	private static SAUsuario usuarioSA = FactoriaSA.getFactoria().generaSAUsuario();
	
	public static UsuarioController getDiscoController() {
		
		if (usuarioController == null)
			usuarioController = new UsuarioController();
		
		return usuarioController;
	}
	
	private UsuarioController() {}
	
	public void crearUsuario(Usuario usuario) {
		try {
			usuarioSA.darseAlta(usuario);
		} catch (TiendaDatabaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addObserver(TiendaObserver observer) {
		usuarioSA.addObverser(observer);
	}
	
	public void removeObserver(TiendaObserver observer) {
		usuarioSA.removeObserver(observer);
	}
}
