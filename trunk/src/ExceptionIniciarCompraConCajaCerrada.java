
public class ExceptionIniciarCompraConCajaCerrada extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ExceptionIniciarCompraConCajaCerrada() {
		super("No se puede iniciar la compra: La Caja Esta Cerrada");
	}

}
