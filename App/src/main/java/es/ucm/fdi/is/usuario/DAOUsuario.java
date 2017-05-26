package es.ucm.fdi.is.usuario;


import es.ucm.fdi.is.dao.TiendaDatabaseException;

public interface DAOUsuario {
	
	public boolean comprobarLogin(String usuario, String clave, Usuario usuarioSesion) throws TiendaDatabaseException;
	public void crearUsuario(Usuario usuario, Empleado empleado, Cliente cliente) throws TiendaDatabaseException;
	public void eliminarUsuario(Usuario usuario, Empleado empleado, Cliente cliente) throws TiendaDatabaseException;
	public void actualizarUsuario(Usuario antiguo, Usuario nuevo,
			Empleado empleadoNuevo,
			Cliente clienteNuevo) throws TiendaDatabaseException;
	public void nuevoIdPedidoUsuario(Usuario usuario) throws TiendaDatabaseException;

}
