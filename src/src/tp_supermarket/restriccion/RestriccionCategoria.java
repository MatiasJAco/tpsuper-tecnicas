package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public class RestriccionCategoria extends Restriccion {

	String categoria;
	RestriccionCantidad rCant;
	
	public RestriccionCategoria(String cat) {
		super();
		this.categoria=cat;
		this.rCant=new RestriccionCantidad(1);
	}
	
	public RestriccionCategoria(String cat, RestriccionCantidad rc) {
		super();
		this.categoria=cat;
		this.rCant=rc;
	}
	
	public RestriccionCategoria(String cat, int cant) {
		super();
		this.categoria=cat;
		this.rCant=new RestriccionCantidad(cant);
	}

	
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		boolean result= activa;
		if (p.getCategoria().equals(this.categoria) && !activa){			
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
		this.rCant.reset();		
	}

}
