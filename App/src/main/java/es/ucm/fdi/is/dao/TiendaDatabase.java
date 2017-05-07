package es.ucm.fdi.is.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TiendaDatabase {
	private static Connection conexion = null;
	private static Statement statement = null; 
	
	private TiendaDatabase() { }
	
	public static Connection getConexion() throws TiendaDatabaseException {
		
		if (conexion == null) {
			
			try {
				Class.forName("org.sqlite.JDBC");
	            // parámetros de la base de datos
	            String url = "jdbc:sqlite:usuarios.db";
	            // creación de la conexión con la base de datos
	            conexion = DriverManager.getConnection(url);
	            
	            System.out.println("Connection to SQLite has been established.");
	            
			} catch (ClassNotFoundException e1) {
				throw new TiendaDatabaseException("No se ha encontrado la librería de SQLite");
			} catch (SQLException e2) {
				throw new TiendaDatabaseException("Ha sido imposible conectar con la base de datos");
			}
		}
		
		return conexion;
	}
	
	public static void iniciar() throws TiendaDatabaseException {
		getConexion();
		
		if (statement == null) {
			
			try {
				statement = conexion.createStatement();
				statement.setQueryTimeout(30);  // set timeout to 30 sec.
			} catch (SQLException e) {
				throw new TiendaDatabaseException(e.getMessage());
			}
			
		}
	}
	
	public static void cerrar() throws TiendaDatabaseException {
		if (conexion != null) {
			try {
				conexion.close();
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
