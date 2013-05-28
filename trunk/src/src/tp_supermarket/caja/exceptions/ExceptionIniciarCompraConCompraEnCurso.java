package tp_supermarket.caja.exceptions;

public class ExceptionIniciarCompraConCompraEnCurso extends Exception {

	private static final long serialVersionUID = 1L;

	public ExceptionIniciarCompraConCompraEnCurso() {
		super("No se puede iniciar la compra: Hay una compra en curso.");
	}
}
