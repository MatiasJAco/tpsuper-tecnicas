package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public class RestriccionMedioDePago extends Restriccion {

	String medioDePago;
	RestriccionCantidad rCant;
	
	public RestriccionMedioDePago(String med) {
		super();
		this.medioDePago=med;
		this.rCant=new RestriccionCantidad(1);
	}
	
	public RestriccionMedioDePago(String med, RestriccionCantidad rc) {
		super();
		this.medioDePago=med;
		this.rCant=rc;
	}
	
	public RestriccionMedioDePago(String med, int cant) {
		super();
		this.medioDePago=med;
		this.rCant=new RestriccionCantidad(cant);
	}

	
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		boolean result= activa;
		if (p.getMedioDePago() == this.medioDePago && !activa){			
				this.activa=checkCantidad(p);
				result=activa;
				
		}else{
			this.activa=false;
			result=false;
		}
		
		
		return result;
	}

	private boolean checkCantidad(Producto p) {
		
			return this.rCant.cumpleRestriccion(p);
				
	}

	public void setCantidad(RestriccionCantidad rCantidad) {
		this.rCant=rCantidad;		
	}

	@Override
	public void reset() {
//		this.activa=false;
//		this.rCant.reset();		
	}

}
