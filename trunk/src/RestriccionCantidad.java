
public class RestriccionCantidad extends Restriccion{

	int cantidad;
	int contabilizados;
	
	public RestriccionCantidad(int cant) {
		super();
		contabilizados=0;
		cantidad=cant;
	}
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		contabilizados++;
		boolean result = false;
		if (contabilizados==cantidad)
			result=true;
	
		return result;
	}

}
