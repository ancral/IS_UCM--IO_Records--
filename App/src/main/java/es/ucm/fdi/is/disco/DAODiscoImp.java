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

	private static DAODiscoImp daoDisco = null;

	public static DAODiscoImp getDaoDisco() {
		if (daoDisco == null)
			daoDisco = new DAODiscoImp();

		return daoDisco;
	}

	private DAODiscoImp() {
	}

	public void crearDisco(Disco disco) throws TiendaDatabaseException {
		
		try (PreparedStatement sql = TiendaDatabase.getConexion()
				.prepareStatement("INSERT OR IGNORE INTO Disco VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)");
				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("INSERT OR IGNORE INTO ListaCanciones VALUES (?,?)");) {

			sql.setString(1, disco.getTitulo());
			sql.setString(2, disco.getAutor());
			sql.setDate(3, disco.getFechaSalida());
			sql.setString(4, disco.getSello());
			sql.setString(5, disco.getGenero().toString());
			sql.setInt(6, disco.getDuracion());
			sql.setString(7, disco.getValoracion().toString());
			sql.setFloat(8, disco.getPrecioCompra());
			sql.setFloat(9, disco.getPrecioVenta());
			sql.setInt(10, disco.getOferta().getPorcentaje());
			sql.setString(11, disco.getCaratula());
			sql.setInt(12, disco.getNumVotaciones());
			sql.setInt(13, disco.getDescatalogado());

			for (Cancion c : disco.getListaCanciones()) {
				sqlCanciones.setString(1, disco.getTitulo());
				sqlCanciones.setString(2, c.toString());
				sqlCanciones.executeUpdate();
			}

			sql.executeUpdate();
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public Disco leerDisco(String titulo) throws TiendaDatabaseException {
		Disco disco = null;
		ResultSet res = null;
		ResultSet resCanciones = null;

		try (PreparedStatement sql = TiendaDatabase.getConexion()
				.prepareStatement("SELECT * FROM Disco WHERE " + "LOWER(Titulo) = ?");
				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM ListaCanciones WHERE LOWER(Titulo) = ?");) {

			List<Cancion> canciones = new ArrayList<Cancion>();

			sqlCanciones.setString(1, titulo.toLowerCase());
			sql.setString(1, titulo.toLowerCase());

			resCanciones = sqlCanciones.executeQuery();

			// Creando la lista de canciones
			while (resCanciones.next()) {
				canciones.add(new Cancion(resCanciones.getString(2)));
			}
			resCanciones.close();

			res = sql.executeQuery();
			// Comprobamos si tiene alguna fila
			if (res.next()) {

				// Busqueda del genero del disco
				GeneroDisco genero = null;
				for (GeneroDisco e : GeneroDisco.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(5))) {
						genero = e;
					}
				}

				// Busqueda de la valoracion del disco
				Float valoracion = res.getFloat(7);

				disco = new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
						Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
						Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10)), res.getString(11),
						res.getInt(12), res.getInt(13));
			}
			res.close();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

		return disco;
	}

	public List<Disco> leerTodos() throws TiendaDatabaseException {
		List<Disco> discos = new ArrayList<Disco>();
		try (PreparedStatement sql = TiendaDatabase.getConexion().prepareStatement("SELECT * FROM Disco");) {

			ResultSet res = sql.executeQuery();

			// Leer todos los discos de la tabla
			while (res.next()) {

				List<Cancion> canciones = new ArrayList<Cancion>();

				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM ListaCanciones WHERE LOWER(Titulo) = ?");
				sqlCanciones.setString(1, res.getString(1).toLowerCase());

				ResultSet resCanciones = sqlCanciones.executeQuery();

				// Creando la lista de canciones
				while (resCanciones.next()) {
					canciones.add(new Cancion(resCanciones.getString(2)));
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
				Float valoracion = res.getFloat(7);

				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
						Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
						Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10)), res.getString(11),
						res.getInt(12), res.getInt(13)));
			}
			res.close();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return discos;
	}

	// FALTA OPTIMIZARLO (usar funcion borrarDisco)
	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		try (PreparedStatement actualizar = TiendaDatabase.getConexion()
				.prepareStatement("UPDATE Disco SET Titulo = ?" + ", Autor = ?, Fecha = ?, Sello = ?,  "
						+ "Genero = ? , Duracion = ? , Valoracion = ? " + ", PrecioCompra = ? , PrecioVenta = ? , "
						+ "Oferta = ? " + ", Caratula = ? , Votantes = ?, Descatalogado = ? " + "WHERE LOWER(Titulo) = ?");) {

			/*
			 * NO SE SI ES NECESARIO ACTUALIZAR LAS CANCIONES
			 */

			/*
			 * PreparedStatement actualizarCanciones =
			 * TiendaDatabase.getConexion()
			 * .prepareStatement("UPDATE OR IGNORE ListaCanciones SET Titulo = ?, Cancion = ?"
			 * + " WHERE LOWER(Titulo) = ?");
			 */

			actualizar.setString(1, nuevo.getTitulo());
			actualizar.setString(2, nuevo.getAutor());
			actualizar.setDate(3, (Date) nuevo.getFechaSalida());
			actualizar.setString(4, nuevo.getSello());
			actualizar.setString(5, nuevo.getGenero().toString());
			actualizar.setInt(6, nuevo.getDuracion());
			actualizar.setFloat(7, nuevo.getValoracion());
			actualizar.setFloat(8, nuevo.getPrecioCompra());
			actualizar.setFloat(9, nuevo.getPrecioVenta());
			actualizar.setInt(10, nuevo.getOferta().getPorcentaje());
			actualizar.setString(11, antiguo.getCaratula());
			actualizar.setInt(12, nuevo.getNumVotaciones());
			actualizar.setInt(13, nuevo.getDescatalogado());
			actualizar.setString(14, antiguo.getTitulo().toLowerCase());

			actualizar.executeUpdate();

			/*
			 * for (Cancion c : nuevo.getListaCanciones()) {
			 * actualizarCanciones.setString(1, nuevo.getTitulo());
			 * actualizarCanciones.setString(2, c.toString());
			 * actualizarCanciones.setString(3,
			 * antiguo.getTitulo().toLowerCase()); }
			 * 
			 * 
			 * actualizarCanciones.executeUpdate();
			 */

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public void borrarDisco(Disco disco) throws TiendaDatabaseException {
		try (PreparedStatement borrar = TiendaDatabase.getConexion()
				.prepareStatement("DELETE FROM Disco WHERE Titulo = ?");) {

			borrar.setString(1, disco.getTitulo().toLowerCase());

			borrar.executeUpdate();

		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public List<Disco> leerPorGenero(GeneroDisco g) throws TiendaDatabaseException {

		List<Disco> discos = new ArrayList<Disco>();

		try (PreparedStatement generos = TiendaDatabase.getConexion()
				.prepareStatement("SELECT * FROM Disco WHERE Disco.Genero = '" + g.toString() + "'");) {

			ResultSet res = generos.executeQuery();

			while (res.next()) {
				List<Cancion> canciones = new ArrayList<Cancion>();

				try (PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM ListaCanciones WHERE Titulo = ?");) {
					sqlCanciones.setString(1, res.getString(1));

					ResultSet resCanciones = sqlCanciones.executeQuery();

					// Creando la lista de canciones
					while (resCanciones.next()) {
						canciones.add(new Cancion(resCanciones.getString(2)));

					}

					resCanciones.close();
				}

				// Busqueda del genero del disco
				GeneroDisco genero = null;
				for (GeneroDisco e : GeneroDisco.values()) {
					if (e.toString().equalsIgnoreCase(res.getString(5))) {
						genero = e;
					}
				}

				// Busqueda de la valoracion del disco
				Float valoracion = res.getFloat(7);

				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3), res.getString(4), genero,
						Integer.valueOf(res.getInt(6)), valoracion, Float.valueOf(res.getFloat(8)),
						Float.valueOf(res.getFloat(9)), canciones, new OfertaDisco(res.getInt(10)), res.getString(11),
						res.getInt(12), res.getInt(13)));

			}
			res.close();
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

			if (res.next()) {
				existir = true;
			}
			
			res.close();
			existe.close();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		return existir;
	}
	
	public void descatalogarDisco(Disco disco) throws TiendaDatabaseException {
		try (PreparedStatement sql = TiendaDatabase.getConexion()
				.prepareStatement("UPDATE Disco SET Descatalogado = 1 "
						+ "WHERE Titulo = ?");) {
			
			sql.setString(1, disco.getTitulo());
			
			sql.executeUpdate();
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

}
