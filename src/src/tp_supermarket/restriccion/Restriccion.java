package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public abstract class Restriccion {

	protected boolean activa;
	
	public Restriccion() {
	}
	
	public abstract boolean cumpleRestriccion(Producto p);

	public boolean isActiva() {
		return activa;
	}

	public abstract void reset();
	
	
	
	
}
