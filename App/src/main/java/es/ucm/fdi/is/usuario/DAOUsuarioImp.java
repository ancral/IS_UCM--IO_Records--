package es.ucm.fdi.is.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.pedido.Pedido;

public class DAOUsuarioImp implements DAOUsuario {

	public void crearPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	public void eliminarPedido(Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	public List<Pedido> verPedidosUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addProductoPedido(Disco disco, Pedido pedido) {
		// TODO Auto-generated method stub
		
	}

	public void actualizarPedido(Pedido antiguo, Pedido nuevo) {
		// TODO Auto-generated method stub
		
	}

	public boolean comprobarLogin(String usuario, String clave) throws TiendaDatabaseException {
		boolean ok = false;
		
		try {
			PreparedStatement sql = TiendaDatabase.getConexion().prepareStatement(
									"SELECT * FROM usuarios WHERE nombre = ? AND clave = ?");
			
			sql.setString(1, usuario);
			sql.setString(2, clave);
			
			ResultSet res = sql.executeQuery();
			
			if (res.next())
				ok = true;
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		return ok;
	}

}
