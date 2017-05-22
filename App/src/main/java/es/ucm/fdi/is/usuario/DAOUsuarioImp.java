package es.ucm.fdi.is.usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;

public class DAOUsuarioImp implements DAOUsuario {
	
	private static DAOUsuarioImp daoUsuario = null;
	
	public static DAOUsuarioImp getDAOUsuario() {
		if (daoUsuario == null)
			daoUsuario = new DAOUsuarioImp();
		
		return daoUsuario;
	}
	
	private DAOUsuarioImp() {}

	public boolean comprobarLogin(String usuario, String clave) throws TiendaDatabaseException {
		boolean ok = false;
		
		
		try {
			PreparedStatement sql = TiendaDatabase.getConexionLogin()
					.prepareStatement("SELECT * FROM usuarios WHERE nombre = ? AND clave = ?");

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

	public void crearUsuario(Usuario usuario, Empleado empleado, Cliente cliente) throws TiendaDatabaseException {
		try {
			PreparedStatement crearUsuario = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO Usuario (?,?,?,?,?)");
			crearUsuario.setString(1, usuario.getNif());
			crearUsuario.setString(2, usuario.getClave());
			crearUsuario.setString(3, usuario.getNombre());
			crearUsuario.setString(4, usuario.getDireccion());
			crearUsuario.setDate(5, usuario.getFechaNacimiento());

			crearUsuario.executeQuery();
			if (empleado != null) {
				PreparedStatement crearEmpleado = TiendaDatabase.getConexion()
						.prepareStatement("INSERT INTO Empleado (?,?,?,?)");
				crearEmpleado.setString(1, usuario.getNif());
				crearEmpleado.setString(2, empleado.getRango().toString());
				crearEmpleado.setFloat(3, empleado.getSalario());
				crearEmpleado.setDate(4, empleado.getAntiguedad());

				crearEmpleado.executeQuery();
			} else if (cliente != null) {
				PreparedStatement creaCliente = TiendaDatabase.getConexion()
						.prepareStatement("INSERT INTO Empleado (?,?)");
				creaCliente.setString(1, usuario.getNif());
				creaCliente.setString(2, cliente.getTipoCliente().toString());

				creaCliente.executeQuery();
			}

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

	}

	public void eliminarUsuario(Usuario usuario, Empleado empleado, Cliente cliente) throws TiendaDatabaseException {
		try {
			PreparedStatement eliminarUsuario = TiendaDatabase.getConexion()
					.prepareStatement("DELETE FROM Usuario WHERE NIF = ?"
							+ " AND Clave = ? AND Nombre = ? AND Direccion = ?" + " AND FechaNac = ?");
			
			eliminarUsuario.setString(1, usuario.getNif());
			eliminarUsuario.setString(2, usuario.getClave());
			eliminarUsuario.setString(3, usuario.getNombre());
			eliminarUsuario.setString(4, usuario.getDireccion());
			eliminarUsuario.setDate(5, usuario.getFechaNacimiento());

			eliminarUsuario.executeQuery();
			
			if (empleado != null) {
				
				PreparedStatement eliminarEmpleado = TiendaDatabase.getConexion().prepareStatement(
						"DELETE FROM Empleado WHERE NIF = ?");
				
				eliminarEmpleado.setString(1, usuario.getNif());;

				eliminarEmpleado.executeQuery();
				
			} else if (cliente != null) {
				
				PreparedStatement eliminarCliente = TiendaDatabase.getConexion()
						.prepareStatement("DELETE FROM Cliente WHERE NIF = ? " + "AND Tipo = ?");
				
				eliminarCliente.setString(1, usuario.getNif());

				eliminarCliente.executeQuery();
			}

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

	}

	public void actualizarUsuario(Usuario antiguo, Usuario nuevo,
			Empleado empleadoNuevo,
			Cliente clienteNuevo) throws TiendaDatabaseException {
		try {
			PreparedStatement actualizarUsuario = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Usuario SET NIF = ?" + " ,Clave = ? ,Nombre = ? ,Direccion = ?"
							+ " ,FechaNac = ?" + " WHERE NIF = ?");
			
			actualizarUsuario.setString(1, nuevo.getNif());
			actualizarUsuario.setString(2, nuevo.getClave());
			actualizarUsuario.setString(3, nuevo.getNombre());
			actualizarUsuario.setString(4, nuevo.getDireccion());
			actualizarUsuario.setDate(5, nuevo.getFechaNacimiento());
			
			actualizarUsuario.setString(6, antiguo.getNif());

			actualizarUsuario.executeQuery();
			
			if(empleadoNuevo != null)
			{
				PreparedStatement actualizarEmpleado = TiendaDatabase.getConexion()
						.prepareStatement("UPDATE Empleado SET NIF = ?" + " ,Rango = ? ,Salario = ?"
								+ " ,Antiguedad = ?" + " WHERE NIF = ?");
				
				actualizarEmpleado.setString(1, nuevo.getNif());
				actualizarEmpleado.setString(2, empleadoNuevo.getRango().toString());
				actualizarEmpleado.setFloat(3, empleadoNuevo.getSalario());
				actualizarEmpleado.setDate(4, empleadoNuevo.getAntiguedad());
				
				actualizarEmpleado.setString(5, antiguo.getNif());

				actualizarEmpleado.executeQuery();
				
			}else if(clienteNuevo != null)
			{
				PreparedStatement actualizarCliente = TiendaDatabase.getConexion()
						.prepareStatement("UPDATE Cliente SET NIF = ?"
								+ " ,Tipo = ?" + " WHERE NIF = ?");
				
				actualizarCliente.setString(1, nuevo.getNif());
				actualizarCliente.setString(2, clienteNuevo.getTipoCliente().toString());
				
				
				actualizarCliente.setString(3, antiguo.getNif());
				

				actualizarCliente.executeQuery();
			}
			
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

}
