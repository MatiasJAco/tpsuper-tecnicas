package tp_supermarket.restriccion;

import tp_supermarket.producto.Producto;

public class RestriccionNombreProducto extends Restriccion{

	String nombre;
	RestriccionCantidad rCant;
	
	public RestriccionNombreProducto(String nom) {
		super();
		this.nombre=nom;
		this.rCant=new RestriccionCantidad(1);
	}
	
	public RestriccionNombreProducto(String nom, RestriccionCantidad rc) {
		super();
		this.nombre=nom;
		this.rCant=rc;
	}
	
	public RestriccionNombreProducto(String nom, int cant) {
		super();
		this.nombre=nom;
		this.rCant=new RestriccionCantidad(cant);
	}

	
	
	@Override
	public boolean cumpleRestriccion(Producto p) {
		boolean result;
		if (p.getNombre() == this.nombre){
				
				
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

}
