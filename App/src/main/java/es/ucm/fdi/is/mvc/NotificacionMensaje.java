package es.ucm.fdi.is.mvc;

public enum NotificacionMensaje {
	
	SESION_INICIADA("Ha iniciado sesión correctamente"),
	ERROR_SESION("No se ha podido iniciar sesión"),
	DISCO_CREADO_EXISTE("El disco ya se encuentra en el catálogo"),
	DISCO_CREADO("El disco se ha creado correctamente"),
	DISCO_BORRADO("El disco ha sido descatalogado"),
	DISCO_ACTUALIZADO("Se han cambiado los datos del disco"),
	LEER_TODOS,
	LEER_TODOSPEDIDOS,
	CARRITO_FINALIZADO_PANEL,
	BUSCAR_DISCO_ENCONTRADO,
	BUSCAR_DISCO_NO_ENCONTRADO,
	LEER_POR_GENERO,
	DISCO_VALORADO,
	ANYADIR_CARRITO,
	ERROR_DISCO_YA_CARRITO,
	BORRADO_CARRITO,
	CARRITO_FINALIZADO,
	NUEVO_ID_PEDIDO,
	PEDIDO_ELIMINADO,
	NUEVA_VENTA,
	NUEVO_PRECIO_DISCO;
	
	private String mensaje;
	
	NotificacionMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	NotificacionMensaje() {
		this.mensaje = null;
	}
	
	public String getMensaje() {
		return this.mensaje;
	}
}
