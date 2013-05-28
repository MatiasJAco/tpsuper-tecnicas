package tp_supermarket.caja.exceptions;


public class ExceptionCerrarCajaConCompraEnCurso extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionCerrarCajaConCompraEnCurso() {
		super("No se puede cerrar la caja: Hay una compra en curso.");
	}

}
