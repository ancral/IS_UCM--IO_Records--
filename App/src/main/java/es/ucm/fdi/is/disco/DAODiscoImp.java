package es.ucm.fdi.is.disco;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					.prepareStatement("INSERT INTO ListaCanciones (?,?,?)");

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
				sqlCanciones.setString(2, disco.getAutor());
				sqlCanciones.setString(3, c.toString());
			}

			sql.executeQuery();
		} catch (SQLException e) {

		}
	}

	@SuppressWarnings("null")
	public Disco leerDisco(String titulo) throws TiendaDatabaseException {
		Disco disco = null;
		try {
			List<Cancion> canciones = null;
		PreparedStatement sql = TiendaDatabase.getConexion()
				.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");
		PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
				.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");
		
			sqlCanciones.setString(1, titulo);
			sql.setString(1, titulo);
			
			ResultSet res = sql.executeQuery();
			ResultSet resCanciones = sql.executeQuery();
			
			//Creando la lista de canciones
			while(resCanciones.next())
			{
				canciones.add(new Cancion(resCanciones.getString(0)));
			}
			
			//Busqueda del genero del disco
			GeneroDisco genero = null;
			for(GeneroDisco e : GeneroDisco.values())
			{
				if(e.toString().equalsIgnoreCase(res.getString(5)))
				{
					genero = e;
				}
			}
			
			//Busqueda de la valoracion del disco
			Valoracion valoracion = null;
			for(Valoracion e : Valoracion.values())
			{
				if(e.toString().equalsIgnoreCase(res.getString(7)))
				{
					valoracion = e;
				}
			}
			
			disco = new Disco(res.getString(1), res.getString(2), res.getDate(3),
					res.getString(4), genero, Integer.valueOf(res.getInt(6)), valoracion, 
					Float.valueOf(res.getFloat(8)), Float.valueOf(res.getFloat(9)),canciones,
					new OfertaDisco(res.getInt(10)));
			
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		
		return disco;
	}

	@SuppressWarnings("null")
	public List<Disco> leerTodos() throws TiendaDatabaseException {
		List<Disco> discos = null;
		try {
			PreparedStatement sql = TiendaDatabase.getConexion()
					.prepareStatement("SELECT * FROM Disco");
			
			
			
			ResultSet res = sql.executeQuery();
			
			//Leer todos los discos de la tabla
			while(res.next())
			{
				List<Cancion> canciones = null;
				
				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");
				
				sqlCanciones.setString(1,res.getString(1));
				
				ResultSet resCanciones = sql.executeQuery();
				
				//Creando la lista de canciones
				while(resCanciones.next())
				{
					canciones.add(new Cancion(resCanciones.getString(0)));
				}
				
				//Busqueda del genero del disco
				GeneroDisco genero = null;
				for(GeneroDisco e : GeneroDisco.values())
				{
					if(e.toString().equalsIgnoreCase(res.getString(5)))
					{
						genero = e;
					}
				}
				
				//Busqueda de la valoracion del disco
				Valoracion valoracion = null;
				for(Valoracion e : Valoracion.values())
				{
					if(e.toString().equalsIgnoreCase(res.getString(7)))
					{
						valoracion = e;
					}
				}
				
				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3),
						res.getString(4), genero, Integer.valueOf(res.getInt(6)), valoracion, 
						Float.valueOf(res.getFloat(8)), Float.valueOf(res.getFloat(9)),canciones,
						new OfertaDisco(res.getInt(10))));
			}
		} catch (SQLException e) {
			throw new TiendaDatabaseException(e.getMessage());
		}
		return discos;
	}

	public void actualizarDisco(Disco antiguo, Disco nuevo) throws TiendaDatabaseException {
		try {
            
			PreparedStatement actualizar = TiendaDatabase.getConexion()
                .prepareStatement("UPDATE Disco SET Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
							+ "Oferta = ? WHERE Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
                                  + "Oferta = ?");
            PreparedStatement actualizarCanciones = TiendaDatabase.getConexion()
                .prepareStatement("UPDATE ListaCanciones SET Titulo = ?, Autor = ?, Cancion = ?"
                    +" WHERE Titulo = ? AND Autor = ?");

            
            
			actualizar.setString(1, nuevo.getTitulo());
			actualizar.setString(2, nuevo.getAutor());
			actualizar.setDate(3, (Date) nuevo.getFechaSalida());
			actualizar.setString(4, nuevo.getSello());
			actualizar.setString(5, nuevo.getGenero().toString());
			actualizar.setInt(6, nuevo.getDuracion());
			actulizar.setString(7, nuevo.getValoracion().toString());
			actualizar.setFloat(8, nuevo.getPrecioCompra());
			actualizar.setFloat(9, nuevo.getPrecioVenta());
			actualizar.setInt(10, nuevo.getOferta().getPorcentaje());
			actualizar.setString(11, antiguo.getTitulo());
			actualizar.setString(12, antiguo.getAutor());
			actualizar.setDate(13, (Date) antiguo.getFechaSalida());
			actualizar.setString(14, antiguo.getSello());
			actualizar.setString(15, antiguo.getGenero().toString());
			actualizar.setInt(16, antiguo.getDuracion());
			actualizar.setString(17, antiguo.getValoracion().toString());
			actualizar.setFloat(18, antiguo.getPrecioCompra());
			actualizar.setFloat(19, antiguo.getPrecioVenta());
			actualizar.setInt(20, antiguo.getOferta().getPorcentaje());


            for (Cancion c : nuevo.getListaCanciones()) {
				actualizarCanciones.setString(1, nuevo.getTitulo());
				actualizarCanciones.setString(2, nuevo.getAutor());
				actualizarCanciones.setString(3, c.toString());
                actualizarCanciones.setString(4, antiguo.getTitulo());
                actualizarCanciones.setString(5, antiguo.getAutor());
			}

            
			actualizar.executeQuery();
            actualizarCanciones.executeQuery();
            
		} catch (SQLException e) {
            throw new TiendaDatabaseException(e.getMessage());
        }
	}

	public void borrarDisco(Disco disco) {
		
        PreparedStatement borrar = TiendaDatabase.getConexion()
                .prepareStatement("DELETE FROM Disco WHERE Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
							+ "Oferta = ? WHERE Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
                                  + "Oferta = ?");
        borrar.executeQuery();
	}

	public List<Disco> leerPorGenero(GeneroDisco genero) throws TiendaDatabaseException {

        List<Disco> discos = null;
        try
        { 
        PreparedStatement generos = TiendaDatabase.getConexion()
            .prepareStatement("SELECT * FROM Disco WHERE Genero = "+genero.toString());

        ResultSet res = generos.executeQuery();

        while(res.next())
        {
           List<Cancion> canciones = null;
				
				PreparedStatement sqlCanciones = TiendaDatabase.getConexion()
						.prepareStatement("SELECT * FROM Disco WHERE Titulo = ?");
				
				sqlCanciones.setString(1,res.getString(1));
				
				ResultSet resCanciones = sql.executeQuery();
				
				//Creando la lista de canciones
				while(resCanciones.next())
				{
					canciones.add(new Cancion(resCanciones.getString(0)));
				}
				
				//Busqueda del genero del disco
				GeneroDisco genero = null;
				for(GeneroDisco e : GeneroDisco.values())
				{
					if(e.toString().equalsIgnoreCase(res.getString(5)))
					{
						genero = e;
					}
				}
				
				//Busqueda de la valoracion del disco
				Valoracion valoracion = null;
				for(Valoracion e : Valoracion.values())
				{
					if(e.toString().equalsIgnoreCase(res.getString(7)))
					{
						valoracion = e;
					}
				}
				
				discos.add(new Disco(res.getString(1), res.getString(2), res.getDate(3),
						res.getString(4), genero, Integer.valueOf(res.getInt(6)), valoracion, 
						Float.valueOf(res.getFloat(8)), Float.valueOf(res.getFloat(9)),canciones,
						new OfertaDisco(res.getInt(10))));
            
        }
        } catch (SQLException e) {
            throw new TiendaDatabaseException(e.getMessage());
        }
		return discos;
	}

	public boolean existeDisco(Disco disco) throws TiendaDatabaseException{

        boolean existir = false;
        try{
        
        PreparedStatement existe = TiendaDatabase.getConexion().
                .prepareStatement("SELECT * FROM Disco WHERE Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
							+ "Oferta = ? WHERE Titulo = ?"
							+ "AND Autor = ? AND Fecha = ? AND Sello = ? AND "
							+ "Genero = ? AND Duracion = ? AND Valoracion = ? "
							+ "AND PrecioCompra = ? AND PrecioVenta = ? AND "
                                  + "Oferta = ?");
        ResultSet res = existe.executeQuery();
        
        existir = res.first();
        
        }catch (SQLException e)
        {
            throw new TiendaDatabaseException(e.getMessage());
        }
                    
		return existir;
	}

}
