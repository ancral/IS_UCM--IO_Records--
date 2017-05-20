package es.ucm.fdi.is.disco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import es.ucm.fdi.is.dao.TiendaDatabase;
import es.ucm.fdi.is.dao.TiendaDatabaseException;

public class DAODiscoImp implements DAODisco {

	public void crearDisco(Disco disco) throws TiendaDatabaseException {
		// TODO Auto-generated method stub
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO Disco (?,?,?,?,?,?,?,?,?,?)");
			PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
					.prepareStatement("INSERT INTO ListaCanciones (?,?)");

			sql.setString(1, disco.getTitulo());
			sql.setString(2, disco.getAutor());
			sql.setDate(3, (Date) disco.getFechaSalida());
			sql.setString(4, disco.getSello());
			sql.setString(5, disco.getGenero().toString());
			sql.setInt(6, disco.getDuracion());
			sql.setString(7, disco.getValoracion().toString());
			sql.setFloat(8, disco.getPrecioCompra());
			sql.setFloat(9, disco.getPrecioVenta());
			sql.setInt(10, disco.getOferta().getPorcentaje());

			for (Cancion c : disco.getListaCanciones()) {
				sqlCanciones.setString(1, disco.getTitulo());
				sqlCanciones.setString(3, c.toString());
			}

			sql.executeQuery();
		} catch (SQLException e) {

		}
	}

	public Disco leerDisco(String titulo) throws TiendaDatabaseException {
		Disco disco = null;
		PreparedStatement sql = null;
		PreparedStatement sqlCanciones = null;
		ResultSet res = null;
		ResultSet resCanciones = null;
		try {
			List<Cancion> canciones = new ArrayList<Cancion>();
			sql = TiendaDatabase.getConexion().prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");
			sqlCanciones = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM ListaCanciones WHERE Titulo = ?");

			sqlCanciones.setString(1, titulo);
			sql.setString(1, titulo);

			res = sql.executeQuery();
			res.close();

			resCanciones = sql.executeQuery();

			// Creando la lista de canciones
			while (resCanciones.next()) {
				canciones.add(new Cancion(resCanciones.getString(1)));
			}
			resCanciones.close();

			// Busqueda del genero del disco
			GeneroDisco genero = null;
			for (GeneroDisco e : GeneroDisco.values()) {
				if (e.toString().equalsIgnoreCase(res.getString(5))) {
					genero = e;
				}
			}

			// Busqueda de la valoracion del disco
			Valoracion valoracion = null;
			for (Valoracion e : Valoracion.values()) {
				if (e.toString().equalsIgnoreCase(res.getString(7))) {
					valoracion = e;
				}
			}

			disco = new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
					Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
					Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10)));

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		return disco;
	}

	public List<Disco> leerTodos() throws TiendaDatabaseException {
		List<Disco> discos = new ArrayList<Disco>();
		try {
			PreparedStatement sql = TiendaDatabase.getConexion().prepareStatement("SELECT * FROM Disco");

			ResultSet res = sql.executeQuery();

			// Leer todos los discos de la tabla
			while (res.next()) {
				List<Cancion> canciones = new ArrayList<Cancion>();

				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM ListaCanciones");

				ResultSet resCanciones = sqlCanciones.executeQuery();

				// Creando la lista de canciones
				while (resCanciones.next()) {
					canciones.add(new Cancion(resCanciones.getString(2)));
				}
				// Busqueda del genero del disco
				GeneroDisco genero = null;
				for (GeneroDisco e : GeneroDisco.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(5))) {
						genero = e;
					}
				}

				// Busqueda de la valoracion del disco
				Valoracion valoracion = null;
				for (Valoracion e : Valoracion.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(7))) {
						valoracion = e;
					}
				}

				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
						Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
						Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10))));
			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return discos;
	}

	// FALTA OPTIMIZARLO (usar funcion borrarDisco)
	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		try {

			PreparedStatement actualizar = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE Disco SET Titulo = ?" + "AND Autor = ?, Fecha = ?, Sello = ? AND "
							+ "Genero = ? , Duracion = ? , Valoracion = ? " + ", PrecioCompra = ? , PrecioVenta = ? , "
							+ "Oferta = ? WHERE Titulo = ?");
			PreparedStatement actualizarCanciones = TiendaDatabase.getConexion()
					.prepareStatement("UPDATE ListaCanciones SET Titulo = ?, Cancion = ?" + " WHERE Titulo = ?");

			actualizar.setString(1, nuevo.getTitulo());
			actualizar.setString(2, nuevo.getAutor());
			actualizar.setDate(3, (Date) nuevo.getFechaSalida());
			actualizar.setString(4, nuevo.getSello());
			actualizar.setString(5, nuevo.getGenero().toString());
			actualizar.setInt(6, nuevo.getDuracion());
			actualizar.setString(7, nuevo.getValoracion().toString());
			actualizar.setFloat(8, nuevo.getPrecioCompra());
			actualizar.setFloat(9, nuevo.getPrecioVenta());
			actualizar.setInt(10, nuevo.getOferta().getPorcentaje());
			actualizar.setString(11, antiguo.getTitulo());

			for (Cancion c : nuevo.getListaCanciones()) {
				actualizarCanciones.setString(1, nuevo.getTitulo());
				actualizarCanciones.setString(2, c.toString());
				actualizarCanciones.setString(3, antiguo.getTitulo());
			}

			actualizar.executeQuery();
			actualizarCanciones.executeQuery();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public void borrarDisco(Disco disco) throws TiendaDatabaseException {
		try {
			PreparedStatement borrar = TiendaDatabase.getConexion()
					.prepareStatement("DELETE FROM Disco WHERE Titulo = ?");

			borrar.setString(1, disco.getTitulo());

			borrar.executeQuery();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public List<Disco> leerPorGenero(GeneroDisco g) throws TiendaDatabaseException {

		List<Disco> discos = new ArrayList<Disco>();

		try {

			PreparedStatement generos = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Disco WHERE Disco.Genero = '" + g.toString() + "'");

			ResultSet res = generos.executeQuery();

			while (res.next()) {
				List<Cancion> canciones = new ArrayList<Cancion>();

				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM ListaCanciones WHERE Titulo = ?");

				sqlCanciones.setString(1, res.getString(1));

				ResultSet resCanciones = sqlCanciones.executeQuery();

				// Creando la lista de canciones
				while (resCanciones.next()) {
					canciones.add(new Cancion(resCanciones.getString(2)));
				}

				// Busqueda del genero del disco
				GeneroDisco genero = null;
				for (GeneroDisco e : GeneroDisco.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(5))) {
						genero = e;
					}
				}

				// Busqueda de la valoracion del disco
				Valoracion valoracion = null;
				for (Valoracion e : Valoracion.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(7))) {
						valoracion = e;
					}
				}

				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
						Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
						Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10))));

			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return discos;
	}

	public boolean existeDisco(Disco disco) throws TiendaDatabaseException {

		boolean existir = false;

		try {

			PreparedStatement existe = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");

			existe.setString(1, disco.getTitulo());

			ResultSet res = existe.executeQuery();

			existir = res.first();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

		return existir;
	}

}
