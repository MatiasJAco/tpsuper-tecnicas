package tp_supermarket.caja;


public class cerrarCajaConCompraEnCurso extends Exception {

	private static final long serialVersionUID = 1L;

	public cerrarCajaConCompraEnCurso() {
		super("No se puede cerrar la caja: Hay una compra en curso.");
	}

}
