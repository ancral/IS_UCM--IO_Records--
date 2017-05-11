package es.ucm.fdi.is.pedido;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.ucm.fdi.is.disco.DAODiscoImp;
import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.disco.Cancion;
import es.ucm.fdi.is.disco.DAODisco;
import es.ucm.fdi.is.disco.Disco;
import es.ucm.fdi.is.disco.GeneroDisco;
import es.ucm.fdi.is.disco.OfertaDisco;
import es.ucm.fdi.is.disco.Valoracion;
import es.ucm.fdi.is.usuario.Usuario;

public class DAOPedidoImp implements DAOPedido {

	public void crearPedido(Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement sql = TiendaDatabase.getConexion().prepareStatement("INSERT INTO Pedido (?,?,?,?)");

			for (Disco disco : pedido.getDiscos()) {
				sql.setString(1, pedido.getId());
				sql.setString(2, pedido.getCliente());
				sql.setString(3, disco.toString());
				sql.setString(4, pedido.getTipoRecogida().toString());
			}

			sql.executeQuery();
		} catch (SQLException e) {

		}
	}

	public void eliminarPedido(Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement borrar = TiendaDatabase.getConexion().prepareStatement(
					"DELETE FROM Pedido WHERE idPedido = ?" + "AND NIFCliente = ? AND tituloDisco = ? AND Tipo = ?");

			for (Disco disco : pedido.getDiscos()) {
				borrar.setString(1, pedido.getId());
				borrar.setString(2, pedido.getCliente());
				borrar.setString(3, disco.toString());
				borrar.setString(4, pedido.getTipoRecogida().toString());
			}

			borrar.executeQuery();
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
			DAODisco base = new DAODiscoImp();

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

				pedido.add(new Pedido(res.getString(1), discos, res.getString(3), tipo));

			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return pedido;
	}

	public void addProductoPedido(Disco disco, Pedido pedido) throws TiendaDatabaseException {
		try {
			PreparedStatement producto = TiendaDatabase.getConexion().prepareStatement("INSERT INTO Pedido (?,?,?,?)");

			producto.setString(1, pedido.getId());
			producto.setString(2, pedido.getCliente());
			producto.setString(3, disco.toString());
			producto.setString(4, pedido.getTipoRecogida().toString());

			producto.executeQuery();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

	}

	public void actualizarPedido(Pedido antiguo, Pedido nuevo) throws TiendaDatabaseException {

		try {
			PreparedStatement actualizar = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Pedido SET idPedido = ?" + ",NIFCliente = ? ,tituloDisco = ? ,Tipo = ?"
							+ "	WHERE idPedido = ? AND");

			this.eliminarPedido(antiguo);

			for (Disco disco : nuevo.getDiscos()) {
				actualizar.setString(1, nuevo.getId());
				actualizar.setString(2, nuevo.getCliente());
				actualizar.setString(3, disco.toString());
				actualizar.setString(4, nuevo.getTipoRecogida().toString());

				actualizar.executeQuery();
			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

}
