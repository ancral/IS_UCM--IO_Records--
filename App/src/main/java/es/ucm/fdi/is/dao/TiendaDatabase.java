package es.ucm.fdi.is.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class TiendaDatabase {
	private static Connection login = null;
	private static Connection tienda = null;
	private static Statement statement = null;

	private TiendaDatabase() {
	}

	public static Connection getConexionLogin() throws TiendaDatabaseException {

		if (login == null) {

			try {
				Class.forName("org.sqlite.JDBC");
	            // parámetros de la base de datos
	            String url = "jdbc:sqlite::resource:databases/usuarios.db";
	            // creación de la conexión con la base de datos
	            login = DriverManager.getConnection(url);
	            
	            System.out.println("La conexión con la base de datos de USUARIOS ha sido establecida.");

			} catch (ClassNotFoundException e1) {
				throw new TiendaDatabaseException("No se ha encontrado la librería de SQLite");
			} catch (SQLException e2) {
				throw new TiendaDatabaseException("Ha sido imposible conectar con la base de datos");
			}
		}

		return login;
	}

	public static Connection getConexion() throws TiendaDatabaseException {
		
		if (tienda == null) {

		try {
				Class.forName("org.sqlite.JDBC");
				
				tienda = DriverManager.getConnection("jdbc:sqlite::resource:databases/compras.db");
				
				System.out.println("La conexión con la base de datos de TIENDA ha sido establecida.");
				
			} catch (ClassNotFoundException e1) {
				throw new TiendaDatabaseException("No se ha encontrado la librería de SQLite");
			} catch (SQLException e2) {
				throw new TiendaDatabaseException("Ha sido imposible conectar con la base de datos");
			}
		}

		return tienda;
	}

	public static void iniciar() throws TiendaDatabaseException {
		getConexionLogin();
		getConexion();

		if (statement == null) {

			try {
				statement = tienda.createStatement();
				statement.setQueryTimeout(30); // set timeout to 30 sec.
			} catch (SQLException e) {
				throw new TiendaDatabaseException(e.getMessage());
			}

		}
	}

	public static void cerrar() throws TiendaDatabaseException {
		if (login != null) {
			try {
				login.close();
			} catch (SQLException e) {
				throw new TiendaDatabaseException(e.getMessage());
			}
		}
	}

	public static void ejecutarModificacion(String sql) throws TiendaDatabaseException {
		try {
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

	public static ResultSet ejecutarQuery(String sql) throws TiendaDatabaseException {
		ResultSet res = null;

		try {
			res = statement.executeQuery(sql);
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}

		return res;
	}

	public static String getValorColumna(ResultSet res, String columnName) throws TiendaDatabaseException {
		try {
			return res.getString(columnName);
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
	}

}
