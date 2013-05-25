
public class cerrarCajaConCajaCerrada extends Exception {
	private static final long serialVersionUID = 1L;

	public cerrarCajaConCajaCerrada() {
		super("No se puede cerrar la caja: La Caja YA Esta Cerrada");
	}

}
