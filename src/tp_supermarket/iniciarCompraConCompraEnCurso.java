package tp_supermarket;

public class iniciarCompraConCompraEnCurso extends Exception {

	private static final long serialVersionUID = 1L;

	public iniciarCompraConCompraEnCurso() {
		super("No se puede iniciar la compra: Hay una compra en curso.");
	}
}
