
public abstract class Restriccion {

	boolean activa;
	
	public Restriccion() {
	}
	
	public abstract boolean cumpleRestriccion(Producto p);

	public boolean isActiva() {
		return activa;
	}
	
	
	
	
}
