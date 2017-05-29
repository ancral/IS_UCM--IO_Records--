package es.ucm.fdi.is.venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;
import es.ucm.fdi.is.usuario.Empleado;

public class DAOVentaImp implements DAOVenta {
	private static DAOVentaImp daoVenta = null;
	
	public static DAOVentaImp getDaoVenta() {
		if (daoVenta == null)
			daoVenta = new DAOVentaImp();
		
		return daoVenta;
	}
	
	private DAOVentaImp() {}

	public void crearVenta(Venta venta) throws TiendaDatabaseException {
		try(PreparedStatement crearVenta = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO Venta VALUES (?,?,?,?,?)");) {
			
			
			crearVenta.setInt(1, venta.getId());
			crearVenta.setDate(2, venta.getFecha());
			crearVenta.setInt(3, venta.getIdPedido());
			crearVenta.setString(4, venta.getEmpleado().getNif());
			crearVenta.setFloat(5, venta.getPrecio());
			
			crearVenta.executeUpdate();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
	}

	public void actualizarVenta(Venta antigua, Venta nueva) throws TiendaDatabaseException {
		try(PreparedStatement actualizarVenta = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Venta SET idVenta = ?"
							+ " ,Fecha = ? ,idPedido = ? ,NIFEmpleado = ? ,"
							+ "PrecioTotal = ? WHERE idVenta = ?");) {
			
			
			actualizarVenta.setInt(1, nueva.getId());
			actualizarVenta.setDate(2, nueva.getFecha());
			actualizarVenta.setInt(3, nueva.getIdPedido());
			actualizarVenta.setString(4, nueva.getEmpleado().getNif());
			actualizarVenta.setFloat(5, nueva.getPrecio());
			
			actualizarVenta.setInt(6, antigua.getId());
			
			actualizarVenta.executeQuery();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public void borrarVenta(Venta venta) throws TiendaDatabaseException {
		try(PreparedStatement borrarVenta = TiendaDatabase.getConexion()
					.prepareStatement("DELETE FROM Venta WHERE idVenta = ?");) {
			
			
			borrarVenta.setInt(1, venta.getId());
			
			borrarVenta.executeQuery();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	@SuppressWarnings("null")
	public List<Venta> verVentas() throws TiendaDatabaseException {
		List<Venta> verVentas = null;
		
		try(PreparedStatement verVenta = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Venta");) {
			

			
			ResultSet res = verVenta.executeQuery();
			
			while(res.next()){
				verVentas.add(new Venta(res.getInt(1), new Empleado(res.getString(4)),
						res.getFloat(5), res.getInt(3), res.getDate(2)));	
			}
			res.close();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return verVentas;
	}

}
