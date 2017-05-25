package es.ucm.fdi.is.pedido;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.ucm.fdi.is.dao.FactoriaIntegracion;
import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;

import es.ucm.fdi.is.disco.DAODisco;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.usuario.Usuario;

public class DAOPedidoImp implements DAOPedido {
	
	private static DAOPedidoImp daoPedido = null;
	
	public static DAOPedidoImp getDaoPedido() {
		if (daoPedido == null)
			daoPedido = new DAOPedidoImp();
		
		return daoPedido;
	}
	
	private DAOPedidoImp() {}

	public void crearPedido(Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement sql = TiendaDatabase.getConexion().prepareStatement("INSERT INTO Pedido VALUES (?,?,?,?)");

			sql.setInt(1, pedido.getId());
			sql.setString(2, pedido.getCliente());
			sql.setString(3, pedido.getTipoRecogida().toString());
			sql.setInt(4, pedido.getFinalizado());

			sql.executeUpdate();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}
	
	public void meterDisco(Pedido pedido, Disco disco) throws TiendaDatabaseException {
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO DiscosPedido VALUES (?, ?)");
			
			sql.setInt(1, pedido.getId());
			sql.setString(2, disco.getTitulo());
			
			sql.executeUpdate();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}
	
	public boolean existeDisco(Pedido pedido, Disco disco) throws TiendaDatabaseException {
		boolean existe = false;
		
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("SELECT tituloDisco FROM DiscosPedido"
							+ " JOIN Pedido ON DiscosPedido.idPedido = ? "
							+ "WHERE DiscosPedido.tituloDisco = ?");
			
			sql.setInt(1, pedido.getId());
			sql.setString(2, disco.getTitulo());
			
			ResultSet res = sql.executeQuery();
			
			existe = res.next();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		return existe;
	}
	
	public void eliminarDiscoPedido(Pedido pedido, Disco disco) throws TiendaDatabaseException {
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("DELETE FROM DiscosPedido WHERE idPedido = ? AND tituloDisco = ?");
			
			sql.setInt(1, pedido.getId());
			sql.setString(2, disco.getTitulo());
			
			sql.executeUpdate();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}
	

	public void eliminarPedido(Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement borrarDiscosPedido = TiendaDatabase.getConexion()
					.prepareStatement("DELETE FROM DiscosPedido WHERE idPedido = ?");
			
			borrarDiscosPedido.setInt(1, pedido.getId());
			
			borrarDiscosPedido.executeUpdate();
			
			PreparedStatement borrar = TiendaDatabase.getConexion().prepareStatement(
					"DELETE FROM Pedido WHERE idPedido = ?");
			
			borrar.setInt(1, pedido.getId());

			borrar.executeUpdate();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	@SuppressWarnings("null")
	private List<Disco> busqueda_discos(String pedido) throws TiendaDatabaseException {
		List<Disco> discos = null;

		try {
			PreparedStatement cliente = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Pedido WHERE idPedido = " + pedido);

			ResultSet res = cliente.executeQuery();
			DAODisco base = FactoriaIntegracion.getFactoria().generaDAODisco();

			while (res.next()) {
				discos.add(base.leerDisco(res.getString(3)));
			}

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return discos;
	}

	@SuppressWarnings("null")
	public List<Pedido> verPedidosUsuario(Usuario usuario) throws TiendaDatabaseException {
		List<Pedido> pedido = null;

		try {
			PreparedStatement cliente = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Pedido WHERE NIFCliente = " + usuario.getNif());

			ResultSet res = cliente.executeQuery();

			while (res.next()) {

				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");

				sqlCanciones.setString(1, res.getString(1));

				List<Disco> discos = busqueda_discos(res.getString(1));

				// Busqueda del tipo de pedido
				TipoRecogida tipo = null;
				for (TipoRecogida e : TipoRecogida.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(4))) {
						tipo = e;
					}
				}

				pedido.add(new Pedido(res.getInt(1), discos, res.getString(3), tipo));

			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return pedido;
	}
	
	public void finalizarPedido(Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Pedido SET Finalizado = 1 WHERE idPedido = ?");
			
			sql.setInt(1, pedido.getId());
			
			sql.executeUpdate();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		
	}

	/************************************
	 *  	  / NO SE UTILIZA /
	 ************************************/
	public void addProductoPedido(Disco disco, Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement producto = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO Pedido VALUES (?,?,?,?)");

			producto.setInt(1, pedido.getId());
			producto.setString(2, pedido.getCliente());
			producto.setString(3, disco.getTitulo());
			producto.setString(4, pedido.getTipoRecogida().toString());

			producto.executeUpdate();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

	}

	public void actualizarPedido(Pedido antiguo, Pedido nuevo) throws TiendaDatabaseException {

		try {
			PreparedStatement actualizar = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Pedido SET idPedido = ?" + ",NIFCliente = ? "
							+ ",tituloDisco = ? ,Tipo = ?"
							+ "	WHERE idPedido = ?");

			for (Disco disco : nuevo.getDiscos()) {
				actualizar.setInt(1, nuevo.getId());
				actualizar.setString(2, nuevo.getCliente());
				actualizar.setString(3, disco.toString());
				actualizar.setString(4, nuevo.getTipoRecogida().toString());
				
				actualizar.setInt(5, antiguo.getId());
				
				actualizar.executeQuery();
			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

}
