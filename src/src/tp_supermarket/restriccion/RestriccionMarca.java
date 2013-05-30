package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public class RestriccionMarca extends Restriccion{

	String marca;
	RestriccionCantidad rCant;
	
	public RestriccionMarca(String mar) {
		super();
		this.marca=mar;
		this.rCant=new RestriccionCantidad(1);
	}
	
	public RestriccionMarca(String mar, RestriccionCantidad rc) {
		super();
		this.marca=mar;
		this.rCant=rc;
	}
	
	public RestriccionMarca(String mar, int cant) {
		super();
		this.marca=mar;
		this.rCant=new RestriccionCantidad(cant);
	}

	
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		boolean result;
		if (p.getMarca().equals(this.marca)){
				this.activa=checkCantidad(p);
				result = this.activa;
		}else
			result = false;
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
		this.activa=false;
		this.rCant.reset();
		
	}
	
	public String getMarca(){
		return this.marca;
	}
	
	public int getCantidad() {
		return this.rCant.getCantidad();
	}

}
