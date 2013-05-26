package tp_supermarket.caja.exceptions;


public class ExceptionCerrarCajaConCajaCerrada extends Exception {
	private static final long serialVersionUID = 1L;

	public ExceptionCerrarCajaConCajaCerrada() {
		super("No se puede cerrar la caja: La Caja YA Esta Cerrada");
	}

}
