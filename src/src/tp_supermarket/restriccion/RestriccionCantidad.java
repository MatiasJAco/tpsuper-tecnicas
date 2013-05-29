package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public class RestriccionCantidad extends Restriccion{

	private final int cantidad;
	private int contabilizados;
	
	public RestriccionCantidad(int cant) {
		super();
		contabilizados=0;
		cantidad=cant;
	}
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		contabilizados++;
		boolean result = false;
		if (contabilizados==cantidad){
			result=true;
			activa=true;
		}
	
		return result;
	}

	@Override
	public void reset() {
		this.activa=false;
		this.contabilizados=0;
		
	}
	
	public int getCantidad(){
		return this.cantidad;
	}

}
