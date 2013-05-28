package tp_supermarket;


public class iniciarCompraConCajaCerrada extends Exception {
	
	private static final long serialVersionUID = 1L;

	public iniciarCompraConCajaCerrada() {
		super("No se puede iniciar la compra: La Caja Esta Cerrada");
	}

}
